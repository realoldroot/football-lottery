package com.artemis.lottery.service.impl;

import com.artemis.lottery.domain.ChoiceTeam;
import com.artemis.lottery.domain.ExpensesRecord;
import com.artemis.lottery.domain.PubIntegrals;
import com.artemis.lottery.repository.ChoiceTeamRepository;
import com.artemis.lottery.service.ChoiceTeamService;
import com.artemis.lottery.service.ExpensesRecordService;
import com.artemis.lottery.service.PubIntegralsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 选择团队
 *
 * @author zhengenshen
 * @date 2018-05-08 14:15
 */
@Service
public class ChoiceTeamServiceImpl extends AbstractBaseService<ChoiceTeamRepository, ChoiceTeam> implements ChoiceTeamService {

    @Autowired
    private PubIntegralsService pubIntegralsService;

    @Autowired
    private ExpensesRecordService expensesRecordService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private String scoreKey = "SCORE";

    /**
     * 重写保存方法
     *
     * @param choiceTeam
     */
    @Override
    public void save(ChoiceTeam choiceTeam) throws Exception {

        PubIntegrals pi = pubIntegralsService.findByBcUser(choiceTeam.getUsername());
        int totalScore = count(pi);

        //账户还有积分
        if (totalScore >= choiceTeam.getScore()) {

            Integer balance = pi.getPresentExp() - choiceTeam.getScore();
            pi.setGameCredits(balance);

            r.save(choiceTeam);

            ExpensesRecord record = buildRecord(choiceTeam, balance);
            //保存记录
            expensesRecordService.save(record);
            //更新用户积分
            pubIntegralsService.save(pi);
            //更新缓存积分
            updateScore(choiceTeam.getScore());

        } else {
            throw new Exception("余额不足请充值");
        }


    }

    private Integer count(PubIntegrals pi) {
        return pi.getGameCredits();
        // return pi.getGameCredits() + pi.getPresentExp();
    }

    private ExpensesRecord buildRecord(ChoiceTeam choiceTeam, Integer balance) throws Exception {
        ExpensesRecord record = new ExpensesRecord();
        record.setId(Long.parseLong(System.currentTimeMillis() + choiceTeam.getUsername()));
        record.setExpenditure(choiceTeam.getScore());
        record.setExpenditure(balance);
        record.setNo(choiceTeam.getNo());
        return record;
    }

    /**
     * 更新缓存中积分
     *
     * @param score 积分
     */
    private void updateScore(int score) {
        String s = redisTemplate.opsForValue().get(scoreKey);
        if (StringUtils.isNotEmpty(s)) {
            int i = Integer.parseInt(s);
            i += score;
            redisTemplate.opsForValue().set(scoreKey, i + "");
        } else {
            int i = Integer.parseInt(s);
            redisTemplate.opsForValue().set(scoreKey, i + "");
        }
    }
}

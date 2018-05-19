package com.artemis.lottery.event;

import com.artemis.lottery.common.RandomTools;
import com.artemis.lottery.domain.ChoiceTeam;
import com.artemis.lottery.domain.Controller;
import com.artemis.lottery.domain.FootballTeam;
import com.artemis.lottery.domain.Response;
import com.artemis.lottery.repository.ChoiceTeamRepository;
import com.artemis.lottery.repository.FootballTeamRepository;
import com.artemis.lottery.service.BonusPool;
import com.artemis.lottery.service.ControllerService;
import com.artemis.lottery.service.PubIntegralsService;
import com.artemis.lottery.socket.OnlineManage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 抽奖事件监听器
 *
 * @author zhengenshen
 * @date 2018-05-08 18:27
 */
@Slf4j
@Component
public class LotteryListener {

    @Autowired
    private ChoiceTeamRepository choiceTeamRepository;

    @Autowired
    private FootballTeamRepository footballTeamRepository;

    @Autowired
    private BonusPool bonusPool;

    @Autowired
    private ControllerService controllerService;

    @Autowired
    private PubIntegralsService pubIntegralsService;

    /**
     * 根据中奖信息 查询谁中奖了
     * 根据中奖的人数来随机生成虚拟中奖人数和中奖注数
     * 然后开奖，
     */
    @Async
    @EventListener
    public void lottery(LotteryEvent event) {
        List<String> totalUsers = new ArrayList<>();
        List<String> randomUsers;
        List<String> realUsers = null;

        FootballTeam winTeam = event.getFootballTeam();
        winTeam.setStatus(1);

        List<ChoiceTeam> winners = choiceTeamRepository.findByNoAndTeamNameAndPlayerNumbers(winTeam.getId(), winTeam.getWinnerTeam(), winTeam.getWinners());

        Controller controller = controllerService.find();

        //真的有中奖人
        if (winners != null && winners.size() > 0) {
            log.debug("中奖的人有 {}", winners);
            //中奖人手机号
            realUsers = winners.stream().map(ChoiceTeam::getUsername).collect(Collectors.toList());
            totalUsers.addAll(realUsers);

            //计算中奖人一共压了多少注
            int bet = winners.stream().map(ChoiceTeam::getPet).reduce((a, b) -> a + b).orElse(0);
            winTeam.setBet(bet);

            //中奖人设置状态为中奖。
            winners.forEach(w -> w.setStatus(1));

        }

        //虚拟中奖人，根据后端设置的虚拟中奖人数
        // randomUsers = RandomTools.phoneNumber(controller.getUserCount());
        randomUsers = RandomTools.phoneNumber();
        totalUsers.addAll(randomUsers);

        //中奖用户和虚拟用户都加进来
        winTeam.setUsers(totalUsers);

        //被数 = 中奖用户 倍数 + 虚拟用户数
        winTeam.setBet(winTeam.getBet() + randomUsers.size());


        //拿到缓存中奖金池的金额 （实际销售金额）
        BigDecimal realAmount = new BigDecimal(bonusPool.get());
        BigDecimal settingAmount = new BigDecimal(controller.getSettingAmount());
        BigDecimal totalAmount = new BigDecimal(realAmount.add(settingAmount).floatValue());

        winTeam.setRealAmount(realAmount.floatValue());
        winTeam.setSettingAmount(controller.getSettingAmount());
        winTeam.setTotalAmount(realAmount.add(settingAmount).floatValue());

        //中奖人数
        int userCount = winTeam.getUsers().size();

        int scale = 2;

        //有人中奖
        if (realUsers != null && realUsers.size() > 0) {
            BigDecimal half = new BigDecimal(0.5);

            // 虚拟注数=(宣称奖池-实际销售额*0.5)/(实际销售额*0.5/实际中奖注数)
            // int bet = (int) ((controller.getSettingAmount() - realAmount * 0.5) / (realAmount * 0.5 / userCount));
            BigDecimal bet = settingAmount.subtract(realAmount.multiply(half))
                    .divide(realAmount.multiply(half).divide(new BigDecimal(userCount), scale, BigDecimal.ROUND_DOWN), scale, BigDecimal.ROUND_DOWN);
            // winTeam.setBet(winTeam.getBet() + bet);
            winTeam.setBet(winTeam.getBet() + bet.intValue());
            //单注金额 = 实际销售额 / 总共下注数
            BigDecimal singleAmount = totalAmount.divide(bet, scale, BigDecimal.ROUND_DOWN);

            winTeam.setSingleAmount(singleAmount.floatValue());

            // 本次开奖发放奖金 =  单注金额 * 中奖人数
            BigDecimal lastAmount = singleAmount.multiply(new BigDecimal(realUsers.size()));

            // 当前奖金池剩余金额 = 当前奖金池金额 - 发放的奖金
            BigDecimal balance = realAmount.subtract(lastAmount);
            //更新缓存中奖金
            bonusPool.set(balance.floatValue());
            //更新用户奖金
            pubIntegralsService.updateAmount(singleAmount.floatValue(), realUsers);
        } else if (winTeam.getBet() > 0) {
            BigDecimal singleAmount = totalAmount.divide(new BigDecimal(winTeam.getBet()), scale, BigDecimal.ROUND_DOWN);
            winTeam.setSingleAmount(singleAmount.floatValue());
        }

        // 保存开奖信息
        footballTeamRepository.save(winTeam);

        //广播
        OnlineManage.broadcast(new Response(winTeam));

    }
}

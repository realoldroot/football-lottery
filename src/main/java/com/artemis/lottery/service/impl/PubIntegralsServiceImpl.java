package com.artemis.lottery.service.impl;

import com.artemis.lottery.domain.ChoiceTeam;
import com.artemis.lottery.domain.PubIntegrals;
import com.artemis.lottery.domain.RespUser;
import com.artemis.lottery.repository.ChoiceTeamRepository;
import com.artemis.lottery.repository.PubIntegralsRepository;
import com.artemis.lottery.service.BcUsersService;
import com.artemis.lottery.service.PubIntegralsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author zhengenshen
 * @date 2018-05-16 18:32
 */

@Service
public class PubIntegralsServiceImpl extends AbstractBaseService<PubIntegralsRepository, PubIntegrals> implements PubIntegralsService {

    @Autowired
    private BcUsersService bcUsersService;

    @Autowired
    private ChoiceTeamRepository choiceTeamRepository;

    @Override
    public PubIntegrals findByBcUser(String username) {
        return r.findByBcUser(username).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public RespUser userInfo(String username) {
        PubIntegrals pi = r.findByBcUser(username).orElseThrow(EntityNotFoundException::new);
        RespUser r = new RespUser();
        r.setUsername(username);
        r.setGameCredits(pi.getGameCredits());
        r.setPresentExp(pi.getPresentExp());

        Page<ChoiceTeam> page = choiceTeamRepository.findByUsername(username, PageRequest.of(0, 40, Sort.Direction.DESC, "no"));
        r.setRecord(page.getContent());
        return r;
    }

    @Override
    public void updateAmount(float amount, List<String> username) {

        r.updateAmount(amount, username);

    }
}

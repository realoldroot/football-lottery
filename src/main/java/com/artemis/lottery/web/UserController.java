package com.artemis.lottery.web;

import com.artemis.lottery.domain.PubIntegrals;
import com.artemis.lottery.domain.QueryParams;
import com.artemis.lottery.domain.RespUser;
import com.artemis.lottery.service.PubIntegralsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhengenshen
 * @date 2018-05-18 10:51
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private PubIntegralsService pubIntegralsService;

    /**
     * 用户信息
     *
     * @param params 用户手机号
     * @return 用户信息
     */
    @PostMapping("/info")
    public RespUser user(@RequestBody QueryParams params) {

        PubIntegrals pi = pubIntegralsService.findByBcUser(params.getUsername());
        RespUser r = new RespUser();
        r.setUsername(params.getUsername());
        r.setGameCredits(pi.getGameCredits());
        r.setPresentExp(pi.getPresentExp());
        return r;
    }

}

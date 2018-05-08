package com.artemis.lottery.web;

import com.artemis.lottery.domain.ChoiceTeam;
import com.artemis.lottery.domain.FootballTeam;
import com.artemis.lottery.service.ChoiceTeamService;
import com.artemis.lottery.service.FootballTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 准备阶段接口
 *
 * @author zhengenshen
 * @date 2018-05-08 10:23
 */

@RestController
public class ReadyController {

    @Autowired
    private ChoiceTeamService choiceTeamService;

    @Autowired
    private FootballTeamService footballTeamService;

    /**
     * 初始化接口，为客户端提供 22个球员和两个队色
     */
    @GetMapping("/initialization")
    public void init() {
        footballTeamService.initFootballTeam();
    }

    @GetMapping("/currentTeam")
    public FootballTeam currentTeam() {
        return footballTeamService.findFootballTeam();
    }


    @PostMapping("/bet")
    public void bet(@RequestBody ChoiceTeam team) {
        choiceTeamService.save(team);
    }
}

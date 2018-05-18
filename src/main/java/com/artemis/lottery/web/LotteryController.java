package com.artemis.lottery.web;

import com.artemis.lottery.domain.ChoiceTeam;
import com.artemis.lottery.domain.FootballTeam;
import com.artemis.lottery.domain.QueryParams;
import com.artemis.lottery.service.ChoiceTeamService;
import com.artemis.lottery.service.FootballTeamService;
import com.artemis.lottery.service.LotteryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 获奖
 *
 * @author zhengenshen
 * @date 2018-05-15 16:00
 */
@Slf4j
@RestController
@RequestMapping("/lottery")
public class LotteryController {


    @Autowired
    private LotteryService lotteryService;

    @Autowired
    private ChoiceTeamService choiceTeamService;

    @Autowired
    private FootballTeamService footballTeamService;


    @ApiOperation("查询当前开奖信息")
    @GetMapping("/current")
    public FootballTeam current() {
        return footballTeamService.findCurrentTeam();
    }

    @ApiOperation("查询下一期开奖信息")
    @GetMapping("/next")
    public FootballTeam next() {
        return footballTeamService.findNextTeam();
    }


    @ApiOperation("下注")
    @PostMapping("/save")
    public void bet(@RequestBody ChoiceTeam team) throws Exception {
        choiceTeamService.save(team);
    }

    @ApiOperation("根据条件查询下注信息")
    @PostMapping("/query")
    public List<ChoiceTeam> query(@RequestBody QueryParams params) {
        return lotteryService.query(params.getNo(), params.getUsername());
    }

    @GetMapping("/query")
    public Page<FootballTeam> query() {
        return lotteryService.query();
    }


}

package com.artemis.lottery;

import com.artemis.lottery.domain.ChoiceTeam;
import com.artemis.lottery.repository.ChoiceTeamRepository;
import com.artemis.lottery.schedule.ScheduleTask;
import com.artemis.lottery.service.ChoiceTeamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zhengenshen
 * @date 2018-05-09 15:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ChoiceTeamTest {


    @Autowired
    private ChoiceTeamService choiceTeamService;


    @Test
    public void save() {

        ChoiceTeam team = new ChoiceTeam();
        team.setId(System.currentTimeMillis());
        team.setUsername("18310860399");
        team.setTeamName("red");
        team.setPlayerNumbers(Stream.of("b_10", "b_11", "r_12").collect(Collectors.toSet()));
        team.setScore(10);
        choiceTeamService.save(team);
    }


    @Autowired
    private ChoiceTeamRepository repository;

    @Autowired
    private ScheduleTask scheduleTask;

    @Test
    public void kaijiang() {
        scheduleTask.lottery();
    }


}

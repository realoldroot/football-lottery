package com.artemis.lottery;

import com.artemis.lottery.domain.ChoiceTeam;
import com.artemis.lottery.domain.FootballTeam;
import com.artemis.lottery.repository.FootballTeamRepository;
import com.artemis.lottery.service.BuildData;
import com.artemis.lottery.service.ChoiceTeamService;
import com.artemis.lottery.service.FootballTeamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FootballLotteryApplicationTests {

    @Test
    public void contextLoads() {
    }


    @Autowired
    private FootballTeamService footballTeamService;

    @Test
    public void save() {

        FootballTeam footballTeam = BuildData.buildTeam();

        repository.save(footballTeam);
    }

    @Autowired
    private FootballTeamRepository repository;

    @Test
    public void findAll() {

        List<FootballTeam> all = repository.findAll();
        all.forEach(System.out::println);
    }

    @Autowired
    private ChoiceTeamService choiceTeamService;

    @Test
    public void saveChoice() {
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        ChoiceTeam team = new ChoiceTeam();
        team.setId(1L);
        team.setTeamName("red");
        team.setUsername("123123213");
        team.setPlayerNumbers(set);
        choiceTeamService.save(team);
    }

    @Test
    public void findTeam() {
        FootballTeam footballTeam = footballTeamService.findFootballTeam();
        System.out.println(footballTeam);
    }

}

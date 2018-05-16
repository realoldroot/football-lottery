package com.artemis.lottery.service.impl;

import com.artemis.lottery.domain.Game;
import com.artemis.lottery.repository.GameRepository;
import com.artemis.lottery.service.GameService;
import org.springframework.stereotype.Service;

/**
 * @author zhengenshen
 * @date 2018-05-16 18:31
 */

@Service
public class GameServiceImpl extends AbstractBaseService<GameRepository, Game> implements GameService {
}

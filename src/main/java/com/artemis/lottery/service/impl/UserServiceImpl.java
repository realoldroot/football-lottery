package com.artemis.lottery.service.impl;

import com.artemis.lottery.domain.User;
import com.artemis.lottery.repository.UserRepository;
import com.artemis.lottery.service.UserService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

/**
 * 用户
 *
 * @author zhengenshen
 * @date 2018-05-11 14:14
 */

@Service
public class UserServiceImpl extends AbstractBaseService<UserRepository, User> implements UserService {

    @Override
    public User login(User user) {
        Optional<User> result = r.findByUsernameAndPassword(user.getUsername(), user.getPassword());

        return result.orElseThrow(() -> new EntityNotFoundException("用户不存在"));
    }
}

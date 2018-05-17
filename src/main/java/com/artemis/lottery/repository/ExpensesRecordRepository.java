package com.artemis.lottery.repository;

import com.artemis.lottery.domain.ExpensesRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author zhengenshen
 * @date 2018-05-17 17:34
 */

public interface ExpensesRecordRepository extends MongoRepository<ExpensesRecord, Long> {
}

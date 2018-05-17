package com.artemis.lottery.service.impl;

import com.artemis.lottery.domain.ExpensesRecord;
import com.artemis.lottery.repository.ExpensesRecordRepository;
import com.artemis.lottery.service.ExpensesRecordService;
import org.springframework.stereotype.Service;

/**
 * @author zhengenshen
 * @date 2018-05-17 17:35
 */

@Service
public class ExpensesRecordServiceImpl extends AbstractBaseService<ExpensesRecordRepository, ExpensesRecord>
        implements ExpensesRecordService {
}

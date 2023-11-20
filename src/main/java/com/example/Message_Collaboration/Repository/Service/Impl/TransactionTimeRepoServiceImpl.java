package com.example.Message_Collaboration.Repository.Service.Impl;

import com.example.Message_Collaboration.Entity.TransactionTime;
import com.example.Message_Collaboration.Repository.Service.TransactionTimeRepoService;
import com.example.Message_Collaboration.Repository.TransactionTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionTimeRepoServiceImpl implements TransactionTimeRepoService {

    @Autowired
    private TransactionTimeRepository transactionTimeRepository;

    @Override
    public TransactionTime save(TransactionTime transactionTime) {
        return transactionTimeRepository.save(transactionTime);
    }
}

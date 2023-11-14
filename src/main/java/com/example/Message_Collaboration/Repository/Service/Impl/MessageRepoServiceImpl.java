package com.example.Message_Collaboration.Repository.Service.Impl;

import com.example.Message_Collaboration.Entity.Message;
import com.example.Message_Collaboration.Repository.MessageRepository;
import com.example.Message_Collaboration.Repository.Service.MessageRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageRepoServiceImpl implements MessageRepoService {

    @Autowired
    private MessageRepository messageRepository;
    @Override
    public Optional<Message> findByProductId1AndProductId2(Long productId1, Long productId2) {
        return messageRepository.findByProductId1AndProductId2(productId1, productId2);
    }

    @Override
    public Message save(Message message) {
       return messageRepository.save(message);
    }

    @Override
    public Optional<List<Message>> findByProductId1AndTransactionAndTransactionStatusAndTransactionType(Long productId, String transaction, String transactionStatus, String transactionType) {
        return messageRepository.findByProductId1AndTransactionAndTransactionStatusAndTransactionType(productId, transaction, transactionStatus, transactionType);
    }

    @Override
    public Optional<List<Message>> findByProductId2AndTransactionAndTransactionStatusAndTransactionType(Long productId, String transaction, String transactionStatus, String transactionType) {
        return messageRepository.findByProductId2AndTransactionAndTransactionStatusAndTransactionType(productId, transaction, transactionStatus, transactionType);

    }
}

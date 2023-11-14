package com.example.Message_Collaboration.Repository.Service;

import com.example.Message_Collaboration.Entity.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MessageRepoService {
  Optional<Message>findByProductId1AndProductId2(Long productId1, Long productId2);

  Message save(Message message);

    Optional<List<Message>> findByProductId1AndTransactionAndTransactionStatusAndTransactionType(Long productId, String transaction, String transactionStatus , String transactionType);

  Optional<List<Message>> findByProductId2AndTransactionAndTransactionStatusAndTransactionType(Long productId, String transaction, String transactionStatus, String transactionType);
}

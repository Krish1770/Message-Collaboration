package com.example.Message_Collaboration.Repository;


import com.example.Message_Collaboration.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    Optional<Message> findByProductId1AndProductId2(Long productId1, Long productId2);

    Optional<List<Message>> findByProductId1AndTransactionAndTransactionStatusAndTransactionType(Long productId, String transaction, String transactionStatus, String transactionType);

    Optional<List<Message>> findByProductId2AndTransactionAndTransactionStatusAndTransactionType(Long productId, String transaction, String transactionStatus, String transactionType);
}

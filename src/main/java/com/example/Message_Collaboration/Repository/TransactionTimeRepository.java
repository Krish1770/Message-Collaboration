package com.example.Message_Collaboration.Repository;


import com.example.Message_Collaboration.Entity.Message;
import com.example.Message_Collaboration.Entity.TransactionTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionTimeRepository extends JpaRepository<TransactionTime, Message> {
}

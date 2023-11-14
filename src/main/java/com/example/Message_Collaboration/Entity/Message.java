package com.example.Message_Collaboration.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Message")
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
    private Long messageId;

    @Column(name = "productId1")
    private Long productId1;

    @Column(name = "productId2")
    private Long productId2;

    @Column(name = "transactionType")
    private  String transactionType;

    @Column(name = "transactionStatus")
    private String transactionStatus;

    @Column(name = "transaction")
  private String transaction;

}

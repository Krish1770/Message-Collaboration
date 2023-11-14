package com.example.Message_Collaboration.Entity;

import jakarta.persistence.*;
import jakarta.security.auth.message.MessageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TransactionTime {

//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long transactionTimeId;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long transactionTimeId;

    @OneToOne(targetEntity = Message.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "messageId",referencedColumnName = "messageId")
   private Message messageId;

    @Column(name = "primaryRequestTime")
    private Date primaryRequestTime;

    @Column(name = "secondaryRequestTime")
    private Date secondaryRequestTime;

    @Column(name = "primaryResponseTime")
    private Date primaryResponseTime;

    @Column(name = "secondaryResponseTime")
    private Date secondaryResponseTime;
}

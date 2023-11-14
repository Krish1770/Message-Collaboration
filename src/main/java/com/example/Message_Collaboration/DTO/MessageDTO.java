package com.example.Message_Collaboration.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class MessageDTO {

    private Long productId1;

    private Long productId2;

    private String transaction;
    private String transactionType;
    private String transactionStatus;


}

package com.example.Message_Collaboration.Service;


import com.example.Message_Collaboration.DTO.MessageDTO;
import com.example.Message_Collaboration.DTO.ResponseDTO;
import com.example.Message_Collaboration.Entity.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface MessageService {
    Optional< Message> findByProductId1AndProductId2(Long productId1, Long productId2);


    ResponseEntity<ResponseDTO> addCollabRequest(MessageDTO messageDTO);

    ResponseEntity<ResponseDTO> getCollaborators(Long productId);
}

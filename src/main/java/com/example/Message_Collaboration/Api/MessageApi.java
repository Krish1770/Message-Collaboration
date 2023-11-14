package com.example.Message_Collaboration.Api;

import com.example.Message_Collaboration.DTO.MessageDTO;
import com.example.Message_Collaboration.DTO.ResponseDTO;
import com.example.Message_Collaboration.Entity.Message;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/Message")
public interface MessageApi {

    @PostMapping("/collabrequest")
            ResponseEntity<ResponseDTO>  addMessage(@RequestBody MessageDTO messageDTO);

    @GetMapping("getCollaborators/{productId}")
          ResponseEntity<ResponseDTO>  getCollaborators(@PathVariable Long productId);

}

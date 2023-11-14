package com.example.Message_Collaboration.Controller;

import com.example.Message_Collaboration.Api.MessageApi;
import com.example.Message_Collaboration.DTO.MessageDTO;
import com.example.Message_Collaboration.DTO.ResponseDTO;
import com.example.Message_Collaboration.Entity.Message;
import com.example.Message_Collaboration.Service.MessageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MessageController implements MessageApi {

    @Autowired
  private ModelMapper modelMapper;
    @Autowired
    private MessageService messageService;
    @Override
    public ResponseEntity<ResponseDTO> addMessage(MessageDTO messageDTO)
    {

    return messageService.addCollabRequest(messageDTO);
  }

  @Override
  public ResponseEntity<ResponseDTO> getCollaborators(Long productId) {
    return messageService.getCollaborators(productId);
  }
}

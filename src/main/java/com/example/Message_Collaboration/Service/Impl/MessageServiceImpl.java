package com.example.Message_Collaboration.Service.Impl;


import com.example.Message_Collaboration.DTO.MessageDTO;
import com.example.Message_Collaboration.DTO.ResponseDTO;
import com.example.Message_Collaboration.Entity.Message;
import com.example.Message_Collaboration.Repository.Service.MessageRepoService;
import com.example.Message_Collaboration.Repository.Service.TransactionTimeRepoService;
import com.example.Message_Collaboration.Service.MessageService;
import com.example.Message_Collaboration.utils.ApplicationMessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.AsyncServerResponse;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    private final static Log LOG = LogFactory.getLog(MessageServiceImpl.class);
    @Autowired
    private ModelMapper  modelMapper;
    @Autowired
    private MessageRepoService messageRepoService;

    @Autowired
    TransactionTimeRepoService transactionTimeRepoService;
    @Override
    public  Optional<Message> findByProductId1AndProductId2(Long productId1, Long productId2) {



        Optional<Message> message=messageRepoService.findByProductId1AndProductId2(productId1,productId2);

        if(message.isEmpty())
        {
            message=messageRepoService.findByProductId1AndProductId2(productId2,productId1);
        }

        return  message;

    }





    @Override
    public ResponseEntity<ResponseDTO> addCollabRequest(MessageDTO messageDTO) {


       Optional<Message> message=findByProductId1AndProductId2(messageDTO.getProductId1(),messageDTO.getProductId2());

        if(message.isPresent())
        {
            LOG.info("fdfrhrthrtru");
                 if(messageDTO.getTransactionType().equals("secondary"))
                 {
                     if(message.get().getTransactionType().equals("primary") && message.get().getTransactionStatus().equals("requested"))
                     {
                         message.get().setTransactionType("secondary");
                     }

                     else if(message.get().getTransactionType().equals("primary") &&( message.get().getTransactionStatus().equals("rejected")
                     || message.get().getTransactionStatus().equals("accepted"))) {

                         String request=message.get().getTransactionStatus();

                         if(request.equals(ApplicationMessageConstants.REJECTED))
                          message.get().setTransactionStatus("rejected");
                         else
                         {
                             message.get().setTransactionStatus("requested");
                             message.get().setTransactionType("secondary");
                         }
                         messageRepoService.save(message.get());
                     }

                     else if(message.get().getTransactionType().equals("secondary"))
                     {
                         if(messageDTO.getTransactionStatus().equals("accepted"))
                         {
                             message.get().setTransactionStatus("requested");
                             message.get().setTransaction("response");
                             message.get().setTransactionType("primary");
                         }

                         if(messageDTO.getTransactionStatus().equals("rejected"))
                         {
                             message.get().setTransactionStatus("rejected");
                         }
                         messageRepoService.save(message.get());
                     }
                     else
                     {
                         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(HttpStatus.BAD_REQUEST,"invalid request",""));
                     }

                 }


                 else
                 {
                     String request=message.get().getTransactionStatus();

                     if(request.equals("rejected"))
                         message.get().setTransactionStatus("rejected");
                     else
                     {
                         message.get().setTransactionStatus("requested");
                         message.get().setTransactionType("secondary");
                     }
                     messageRepoService.save(message.get());

                     return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,"request updated",""));

                 }
        }
        else
        {
            if( messageDTO.getTransactionType().equals("primary"))
            {
                if(messageDTO.getTransaction().equals("request") ) {
                    Message message1 = Message.builder()
                            .productId1(messageDTO.getProductId1())
                            .productId2(messageDTO.getProductId2())
                            .transactionStatus(messageDTO.getTransactionStatus())
                            .transactionType(messageDTO.getTransactionType()).build();
                    messageRepoService.save(message1);

                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK, "new request created", ""));
                }
                else if(messageDTO.getTransaction().equals("accepted") || messageDTO.getTransaction().equals("rejected") )
                {
                    String request=message.get().getTransactionStatus();
                    System.out.println("fdgfh");
                    if(request.equals("rejected"))
                        message.get().setTransactionStatus("rejected");
                    else
                    {
                        message.get().setTransactionStatus("requested");
                        message.get().setTransactionType("secondary");
                    }
                    messageRepoService.save(message.get());

                    return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,"updated",""));
                }
            }
            else
            {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(HttpStatus.BAD_REQUEST,"invalid request",""));

            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDTO(HttpStatus.BAD_REQUEST,"invalid23 request",""));

    }

    @Override
    public ResponseEntity<ResponseDTO> getCollaborators(Long productId) {

         //if he is the message requester
        List<Message> messageList= messageRepoService.findByProductId1AndTransactionAndTransactionStatusAndTransactionType(productId,"response","accepted","secondary").get();

     List<Long> message1= new ArrayList<>(messageList.stream().map(Message::getProductId2).toList());

        //if he is a message responser
        List<Message> messageList1= messageRepoService.findByProductId2AndTransactionAndTransactionStatusAndTransactionType(productId,"response","accepted","secondary").get();
        List<Long> message2=new ArrayList<>(messageList1.stream().map(Message::getProductId2).toList());
        System.out.println(message1+" "+message2);

        message1.addAll(message2);
        System.out.println(productId + "iddd");
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(HttpStatus.OK,"list of collaborators of product "+productId,message1));
    }

}

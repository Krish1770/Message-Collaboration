package com.example.Message_Collaboration;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class MessageCollaborationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MessageCollaborationApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper()
	{
		return new ModelMapper();
	}
}

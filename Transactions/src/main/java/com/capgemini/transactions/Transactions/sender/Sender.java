package com.capgemini.transactions.Transactions.sender;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.transactions.Transactions.account.Account;

@Component
public class Sender {

	@Autowired
	private RabbitMessagingTemplate template;
	
	public void send(Account account){
		template.convertAndSend("updateBalance", account);
	}
	
}

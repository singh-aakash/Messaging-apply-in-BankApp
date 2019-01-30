package com.capgemini.account.Account.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.account.Account.entity.Account;
import com.capgemini.account.Account.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountResource {

	@Autowired
	private AccountService service;
	
	@GetMapping
	public List<Account> getAllAccount() {
		List<Account> accounts = service.getAllAccounts();
		return accounts;
		
	}
	
	
	@RabbitListener(queues = "updateBalance")
	public void update(Account account) {
		Account account1 = service.findById(account.getAccountNumber()).get();
		account1.setCurrentBalance(account.getCurrentBalance());
		service.update(account1);
	}

	@GetMapping("/{accountNumber}")
	public Optional<Account> getAccountById(@PathVariable int accountNumber) {
		Optional<Account> account = service.getAccountById(accountNumber);
		return account;
	}
	
	@GetMapping("/{accountNumber}/balance")
	public double getCurrentBalance(@PathVariable int accountNumber) {

		double currentBalance = service.findById(accountNumber).get().getCurrentBalance();
		return currentBalance;
	}


	public void update(Integer accountNumber, Double currentBalance) {
		// TODO Auto-generated method stub
		
	}

}

package com.db.awmd.challenge.repository;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.domain.AccountTransfer;
import com.db.awmd.challenge.exception.DuplicateAccountIdException;

import lombok.Synchronized;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class AccountsRepositoryInMemory implements AccountsRepository {

  private final Map<String, Account> accounts = new ConcurrentHashMap<>();

  @Override
  public void createAccount(Account account) throws DuplicateAccountIdException {
    Account previousAccount = accounts.putIfAbsent(account.getAccountId(), account);
    if (previousAccount != null) {
      throw new DuplicateAccountIdException(
        "Account id " + account.getAccountId() + " already exists!");
    }
  }

  @Override
  public Account getAccount(String accountId) {
    return accounts.get(accountId);
  }

  @Override
  public void clearAccounts() {
    accounts.clear();
  }

@Override
@Synchronized
public void transferAmount(AccountTransfer account) {
	// TODO Auto-generated method stub
	Account senderaccount  =   getAccount(account.getSenderAccountId());
	Account receiveraccount=	getAccount(account.getReciverAccountId());
	  BigDecimal balance=	account.getAmount();
	  //check for account exist
	  if(senderaccount!=null && receiveraccount!=null)
		  {
		  //check if sender has enogh balnce to send money 
		  	if(senderaccount.getBalance().compareTo(balance) != -1)	
		 	 senderaccount.setBalance(senderaccount.getBalance().subtract(balance));
	         receiveraccount.setBalance(senderaccount.getBalance().add(balance));
		  }
}

}

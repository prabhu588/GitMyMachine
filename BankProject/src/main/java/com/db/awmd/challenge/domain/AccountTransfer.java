package com.db.awmd.challenge.domain;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AccountTransfer {
	@NotNull
	  @NotEmpty
	private final String senderAccountId;
	@NotNull
	  @NotEmpty
	private final String reciverAccountId;
	  @NotNull
	  @Min(value = 0, message = "Initial balance must be positive.")
	private final BigDecimal amount;
	  
	public AccountTransfer(@NotNull @NotEmpty String senderAccountId, @NotNull @NotEmpty String reciverAccountId,
			@NotNull @Min(value = 0, message = "Initial balance must be positive.") String amount) {
	
		this.senderAccountId = senderAccountId;
		this.reciverAccountId = reciverAccountId;
		this.amount = BigDecimal.ZERO;
	}
	
	 @JsonCreator
	  public AccountTransfer(@JsonProperty("senderAccountId") String senderAccountId,@JsonProperty("reciverAccountId") String reciverAccountId,
	    @JsonProperty("amount") BigDecimal amount) {
		 this.senderAccountId = senderAccountId;
			this.reciverAccountId = reciverAccountId;
			this.amount = amount;
	  }
	
	
}

package com.alami.transaction.dto.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class UserTransactionDto implements Serializable {
    private Long id;
    private String name;
    private Long balance;
    @JsonProperty("transaction_amount")
    private Long transactionAmount;
    @JsonProperty("transaction_date")
    private String transactionDate;
}

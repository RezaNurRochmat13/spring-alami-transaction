package com.alami.transaction.entity;

import com.alami.transaction.util.AuditModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transaction extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    @JsonProperty("user_id")
    private Long userId;

    @Column(name = "transaction_date")
    @JsonProperty("transaction_date")
    private String transactionDate;

    @Column(name = "transaction_amount")
    @JsonProperty("transaction_amount")
    private Long transactionAmount;
}

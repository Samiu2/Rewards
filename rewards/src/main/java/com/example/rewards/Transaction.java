package com.example.rewards;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Transaction {
    private long amount;
    private String customerId; //unique identifier
    private Date transactionDate;

    public int getTransactionMonth() {
        return this.transactionDate.getMonth();
    }
}

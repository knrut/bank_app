package com.rut.bank.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class Transaction extends Entity<UUID> implements Serializable {
   private TransactionType transactionType;
   private BankAccount perfomedBy;
   private BigDecimal amount;
   private BankAccount sentTo;
   private LocalDateTime executedAt;

   public Transaction(TransactionType transactionType, BankAccount perfomedBy, BigDecimal amount, BankAccount sentTo) {
       setID(UUID.randomUUID());
       this.transactionType = transactionType;
       this.perfomedBy = perfomedBy;
       this.amount = amount;
       this.sentTo = sentTo;
       this.executedAt = LocalDateTime.now();
   }

   public Optional<BankAccount> getSentTo() {
       return Optional.ofNullable(sentTo);
   }
}

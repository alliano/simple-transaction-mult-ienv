package com.transaction.dto;

import lombok.Data;

public @Data class TransactionDto {
   
   private String rekeningUtama;
   private String rekeningTujuan;
   private double nominal;
}

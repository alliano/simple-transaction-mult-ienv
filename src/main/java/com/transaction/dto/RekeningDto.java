package com.transaction.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RekeningDto {
   
   private String name;

   private String email;

   private String noRekening;

   private String password;

   private double saldo;
}

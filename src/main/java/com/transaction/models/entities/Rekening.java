package com.transaction.models.entities;

import java.io.Serializable;


// import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Entity
@Table(name = "rekening")
public class Rekening implements Serializable {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;

   // @Column(length = 50,nullable = false)
   private String name;

   // @Column(length = 50,nullable = false,unique = true)
   private String email;

   // @Column(nullable = false,unique = true)
   private String noRekening;
   
   // @Column(length = 255,nullable = false)
   private String password;

   private double saldo;

   public Rekening(){}

}

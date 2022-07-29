package com.transaction.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.transaction.dto.RekeningDto;
import com.transaction.dto.TransactionDto;
import com.transaction.models.entities.Rekening;
import com.transaction.services.RekeningService;

@RestController
@RequestMapping(path = "/",method = RequestMethod.GET)
public class RekeningController {
   
   @Autowired
   private RekeningService rekeningService;

   @PostMapping(path = "/create")
   public Rekening createRekening(@RequestBody RekeningDto rekeningDto){
      Rekening rekening = new Rekening();
      rekening.setName(rekeningDto.getName());
      rekening.setEmail(rekeningDto.getEmail());
      rekening.setPassword(rekeningDto.getPassword());
      rekening.setNoRekening(rekeningDto.getNoRekening());
      rekening.setSaldo(rekeningDto.getSaldo());
      return rekeningService.createrRekening(rekening);
   }

   @GetMapping
   public Iterable<Rekening> findAll(){
      return rekeningService.findAll();
   }

   @PostMapping(path = "/transaction")
   public HashMap<String,String> transaction(@RequestBody TransactionDto transaction){
      return 
      rekeningService
      .transaction(transaction.getRekeningUtama(),
       transaction.getRekeningTujuan(), 
       transaction.getNominal());
   }
}

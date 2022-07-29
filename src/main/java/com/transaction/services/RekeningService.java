package com.transaction.services;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transaction.models.entities.Rekening;
import com.transaction.models.repositories.RekeningRepository;

import jakarta.transaction.Transactional;

@Service
public class RekeningService {

   @Autowired
   private RekeningRepository rekeningRepository;

   public Iterable<Rekening> findAll(){
      return rekeningRepository.findAll();
   }

   public Rekening createrRekening(Rekening rekening){
      return rekeningRepository.save(rekening);
   }

   @Transactional
   public HashMap<String,String> transaction(String rekening1, String rekening2, double amount){
      Rekening pengirim = rekeningRepository.findByNoRekening(rekening1);
      Rekening penerima = rekeningRepository.findByNoRekening(rekening2);
      HashMap<String,String> message = new HashMap<String,String>();
      try{
         if(pengirim.equals(null)){
            throw new RuntimeException("maaf rekening utama tisak valid");
         }
         else if(penerima.equals(null)){
            throw new RuntimeException("maaf rekening tujuan tisak valid");
         }
         pengirim.setSaldo(pengirim.getSaldo() - amount);
         penerima.setSaldo(penerima.getSaldo()+amount);
         rekeningRepository.save(penerima);
         rekeningRepository.save(pengirim);
         message.put("pengirim", pengirim.getName());
         message.put("penerima", penerima.getName());
         message.put("status", "transaksi berhasil");
         message.put("pengeluaran", Double.toString(amount));
         return message;

      }catch(Exception Ex){
         throw new RuntimeException("gagal transaksi " + Ex);
      }
   }
}

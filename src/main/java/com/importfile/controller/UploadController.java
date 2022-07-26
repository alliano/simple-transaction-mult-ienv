package com.importfile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.importfile.dto.ResponseHttp;
import com.importfile.model.entity.Book;
import com.importfile.services.UploadService;
import com.importfile.utils.ReadingCsv;


@RestController
@RequestMapping(path = "/book",method = RequestMethod.GET)
public class UploadController {
   
   @Autowired
   private UploadService uploadService;

   @GetMapping
   public ResponseEntity<?> findAll(){
      ResponseHttp<Book> responseHttp = new ResponseHttp<Book>();
      try{
         responseHttp.setStatus(true);
         List<Book> book = uploadService.findAll();
         responseHttp.setPayload(book);
         responseHttp.getMessage().add("succes");
         return ResponseEntity.status(HttpStatus.OK).body(responseHttp);
      }catch(Exception Ex){
         responseHttp.getMessage().add("failed insert " + Ex);
         responseHttp.setPayload(null);
         responseHttp.setStatus(false);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseHttp);
        
      }
   }

   @PostMapping(path = "/import")
   public ResponseEntity<?> importCsv(@RequestParam("file") MultipartFile file){
      ResponseHttp<Book> responseHttp = new ResponseHttp<Book>();
      try{
         if(ReadingCsv.isCsvFormat(file)){
            responseHttp.setStatus(true);
            responseHttp.getMessage().add("failed to import in database please check type of your file or etc");
            responseHttp.setPayload(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseHttp);
         }
         responseHttp.setStatus(true);
         responseHttp.getMessage().add("file success import into database");
         responseHttp.setPayload(uploadService.saveBooks(file));
         return ResponseEntity.status(HttpStatus.OK).body(responseHttp);
      }catch(Exception Ex){
         throw new RuntimeException("failed to import csv file "+Ex);
      }
   }
}

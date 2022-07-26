package com.importfile.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.importfile.model.entity.Book;
import com.importfile.model.repositories.Bookrepository;
import com.importfile.utils.ReadingCsv;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UploadService {
   
   @Autowired(required = true)
   private Bookrepository bookrepository;

   public List<Book> findAll(){
      return bookrepository.findAll();
   }

   public Iterable<Book> saveBooks(MultipartFile file){

      try{
         List<Book> books = ReadingCsv.csvToList(file.getInputStream());
         return bookrepository.saveAll(books);
      }catch(IOException IoEx){
         throw new RuntimeException(String.format("failed to insert all data \s", IoEx));
      }
   }
}

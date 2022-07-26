package com.importfile.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.importfile.model.entity.Book;

public class ReadingCsv {
   private static final String TYPE_FILE = "text.csv";

   public static boolean isCsvFormat(MultipartFile file){
      if(!TYPE_FILE.equalsIgnoreCase(TYPE_FILE))
      return false;
      else
      return true;
   }

   public static List<Book> csvToList(InputStream inputStream){
      try {
         BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
         CSVParser parserCsvFile = new CSVParser(fileReader, CSVFormat.DEFAULT
         .withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
         List<Book> books = new ArrayList<Book>();
         Iterable<CSVRecord> csvRecord = parserCsvFile.getRecords();
         for (CSVRecord csvRecord2 : csvRecord) {
            Book book = new Book();
            book.setId(Long.parseLong(csvRecord2.get("id")));
            book.setDescription(csvRecord2.get("description"));
            book.setPrice(Double.parseDouble(csvRecord2.get("price")));
            book.setTitle(csvRecord2.get("title"));
         }
         parserCsvFile.close();
         return books;
         
      } catch (IOException IoEx) {
         throw new RuntimeErrorException(null, String.format("failed to read csv file \s",IoEx));
      }
   }
}

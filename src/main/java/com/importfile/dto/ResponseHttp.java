package com.importfile.dto;

import java.util.ArrayList;
import java.util.List;


public class ResponseHttp<T extends Object> {
   
   private List<String> message = new ArrayList<>();

   private Object payload;

   private boolean status;

   public List<String> getMessage() {
      return message;
   }

   public void setMessage(List<String> message) {
      this.message = message;
   }

   public Object getPayload() {
      return payload;
   }

   public void setPayload(Object book) {
      this.payload = book;
   }

   public boolean isStatus() {
      return status;
   }

   public void setStatus(boolean status) {
      this.status = status;
   }

   
}

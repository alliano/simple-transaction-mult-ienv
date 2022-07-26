package com.importfile.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.importfile.model.entity.Book;


@Repository
public interface Bookrepository extends JpaRepository<Book,Long> {
   
}

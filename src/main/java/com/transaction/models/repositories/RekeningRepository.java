package com.transaction.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transaction.models.entities.Rekening;

@Repository
public interface RekeningRepository extends JpaRepository<Rekening,Long>{

   public Rekening findByNoRekening(String noRekening);
}

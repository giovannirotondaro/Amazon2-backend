package com.techzon.data.dao;

import com.techzon.data.entities.Reso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResoDao extends JpaRepository<Reso,Long> {
    List<Reso> findAllByUsernameDestinatario(String username);
    Optional<Reso> findById(Long id);
    
    @Query("FROM RESO r WHERE r.numeroOrdine.id =:id")
    Reso  resoByNumeroOrdine(@Param("id")Long id);
}

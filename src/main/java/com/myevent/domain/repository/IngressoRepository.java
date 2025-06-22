package com.myevent.domain.repository;

import com.myevent.domain.entity.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngressoRepository extends JpaRepository<Ingresso, Long> {

    @Query("SELECT i FROM Ingresso i")
    List<Ingresso> findAlls();

}

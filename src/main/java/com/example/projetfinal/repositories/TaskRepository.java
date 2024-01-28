package com.example.projetfinal.repositories;

import com.example.projetfinal.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    @Transactional
    @Modifying
    @Query("update Task t set t.Name = ?1 where t.id = ?2")
    void updateNomById(String Nom, Long id);
}

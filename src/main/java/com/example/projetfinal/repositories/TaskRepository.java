package com.example.projetfinal.repositories;

import com.example.projetfinal.models.Task;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TaskRepository extends CrudRepository<Task,Long> {
    @Transactional
    @Modifying
    @Query("update Task t set t.Nom = ?1 where t.id = ?2")
    void updateNomById(String Nom, Long id);

}

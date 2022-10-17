package ru.anischenko.tasklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.anischenko.tasklist.model.entity.TaskEntity;


import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<TaskEntity,Long> {
    List<TaskEntity> findByUserUsername(String username);
}

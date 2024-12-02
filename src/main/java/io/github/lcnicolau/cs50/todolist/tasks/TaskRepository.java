package io.github.lcnicolau.cs50.todolist.tasks;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TaskRepository extends JpaRepository<Task, Integer> {

    Page<Task> findByOrderByCreatedDesc(Pageable pageable);

    Page<Task> findByDescriptionContainingIgnoreCaseOrderByCreatedDesc(String description, Pageable pageable);

}

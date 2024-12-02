package io.github.lcnicolau.cs50.todolist.tasks;

import io.github.lcnicolau.cs50.todolist.planner.Planner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TaskRepository extends JpaRepository<Task, Integer> {

    Page<Task> findByAuthor(Planner author, Pageable pageable);

    Page<Task> findByAuthorAndDescriptionContainingIgnoreCase(Planner author, String description, Pageable pageable);

}

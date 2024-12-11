package io.github.lcnicolau.cs50.todolist.tasks;

import io.github.lcnicolau.cs50.todolist.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface TaskRepository extends JpaRepository<Task, Integer> {

    Page<Task> findByAuthor(User author, Pageable pageable);

    Page<Task> findByAuthorAndDescriptionContainingIgnoreCase(User author, String description, Pageable pageable);

}

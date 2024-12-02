package io.github.lcnicolau.cs50.todolist.tasks;

import io.github.lcnicolau.cs50.todolist.planner.Planner;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@Service
class TaskService {

    private final TaskRepository taskRepository;
    private final AuditorAware<Planner> auditorAware;

    TaskService(TaskRepository taskRepository, AuditorAware<Planner> auditorAware) {
        this.taskRepository = taskRepository;
        this.auditorAware = auditorAware;
    }

    Page<Task> findForCurrentUser(String search, Pageable pageable) {
        var currentUser = auditorAware.getCurrentAuditor()
                .orElseThrow(() -> new ResponseStatusException(UNAUTHORIZED, "You must be authenticated"));
        return (search.isBlank())
                ? taskRepository.findByAuthor(currentUser, pageable)
                : taskRepository.findByAuthorAndDescriptionContainingIgnoreCase(currentUser, search, pageable);
    }

    Task findByIdForCurrentUser(Integer id) {
        var currentUser = auditorAware.getCurrentAuditor()
                .orElseThrow(() -> new ResponseStatusException(UNAUTHORIZED, "You must be authenticated"));
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Task not found"));
        if (!Objects.equals(currentUser.getId(), task.getAuthor().getId())) {
            throw new ResponseStatusException(FORBIDDEN, "You are not the author of the task");
        }
        return task;
    }

    Task saveForCurrentUser(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    Task updateForCurrentUser(Integer id, Task updated) {
        var task = findByIdForCurrentUser(id);
        task.setDescription(updated.getDescription());
        task.setDone(updated.getDone());
        return taskRepository.save(task);
    }

    @Transactional
    void deleteForCurrentUser(Integer id) {
        var task = findByIdForCurrentUser(id);
        taskRepository.delete(task);
    }

}

package io.github.lcnicolau.cs50.todolist.tasks;

import io.github.lcnicolau.cs50.todolist.planner.Planner;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;
    private final AuditorAware<Planner> auditorAware;


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

    Task createForCurrentUser(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    Task updateForCurrentUser(Integer id, Map<String, String> patch) {
        var target = findByIdForCurrentUser(id);
        var updated = taskMapper.patch(patch, target);
        return taskRepository.save(updated);
    }

    @Transactional
    void deleteForCurrentUser(Integer id) {
        var target = findByIdForCurrentUser(id);
        taskRepository.delete(target);
    }

}

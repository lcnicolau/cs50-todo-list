package io.github.lcnicolau.cs50.todolist.tasks;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
class TaskService {

    private final TaskRepository taskRepository;

    TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    Page<Task> find(String search, Pageable pageable) {
        return (search.isBlank())
                ? taskRepository.findByOrderByCreatedDesc(pageable)
                : taskRepository.findByDescriptionContainingIgnoreCaseOrderByCreatedDesc(search, pageable);
    }

    Task findById(Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Task not found"));
    }

    Task save(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    Task update(Integer id, Task updated) {
        var task = findById(id);
        task.setDescription(updated.getDescription());
        task.setDone(updated.getDone());
        return taskRepository.save(task);
    }

    @Transactional
    void delete(Integer id) {
        var task = findById(id);
        taskRepository.delete(task);
    }

}

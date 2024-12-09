package io.github.lcnicolau.cs50.todolist.tasks;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
class TaskController {

    private final TaskService taskService;


    @GetMapping(params = "search")
    ModelAndView list(@RequestParam String search,
                      @SortDefault(sort = "created", direction = DESC) Pageable pageable) {
        var page = taskService.findForCurrentUser(search, pageable);
        return new ModelAndView("tasks/page", Map.of("search", search, "page", page));
    }

    @GetMapping("/{id}")
    ModelAndView find(@PathVariable Integer id,
                      @RequestParam(defaultValue = "false") Boolean edit) {
        var found = taskService.findByIdForCurrentUser(id);
        return new ModelAndView((edit) ? "tasks/edit" : "tasks/item", "item", found);
    }

    @PostMapping
    ModelAndView create(@Valid Task task) {
        var created = taskService.createForCurrentUser(task);
        return new ModelAndView("tasks/item", "item", created);
    }

    @PatchMapping("/{id}")
    ModelAndView update(@PathVariable Integer id,
                        @RequestParam Map<String, String> patch) {
        var updated = taskService.updateForCurrentUser(id, patch);
        return new ModelAndView("tasks/item", "item", updated);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    void delete(@PathVariable Integer id) {
        taskService.deleteForCurrentUser(id);
    }

}

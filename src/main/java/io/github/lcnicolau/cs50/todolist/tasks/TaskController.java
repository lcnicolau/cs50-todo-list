package io.github.lcnicolau.cs50.todolist.tasks;

import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Controller
@RequestMapping("/items")
class TaskController {

    private final TaskService taskService;

    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @GetMapping
    ModelAndView list(@RequestParam(defaultValue = "") String search,
                      @SortDefault(sort = "created", direction = DESC) Pageable pageable) {
        var found = taskService.findForCurrentUser(search, pageable);
        return new ModelAndView("items/items", Map.of("search", search, "items", found));
    }

    @GetMapping(value = "/{id}")
    ModelAndView find(@PathVariable Integer id,
                      @RequestParam(defaultValue = "false") Boolean edit) {
        var found = taskService.findByIdForCurrentUser(id);
        var view = (edit) ? "items/edit" : "items/item";
        return new ModelAndView(view, "item", found);
    }

    @PostMapping
    ModelAndView save(@Valid Task task) {
        var saved = taskService.saveForCurrentUser(task);
        return new ModelAndView("items/item", "item", saved);
    }

    @PutMapping(value = "/{id}")
    ModelAndView edit(@PathVariable Integer id,
                      @Valid Task task) {
        var updated = taskService.updateForCurrentUser(id, task);
        return new ModelAndView("items/item", "item", updated);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    void delete(@PathVariable Integer id) {
        taskService.deleteForCurrentUser(id);
    }

}

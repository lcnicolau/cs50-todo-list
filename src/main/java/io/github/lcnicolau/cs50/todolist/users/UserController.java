package io.github.lcnicolau.cs50.todolist.users;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.springframework.data.domain.Sort.Direction.ASC;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;


    @GetMapping(params = "search")
    ModelAndView list(@RequestParam String search,
                      @SortDefault(sort = "name", direction = ASC) Pageable pageable) {
        var page = userService.find(search, pageable);
        return new ModelAndView("users/page", Map.of("search", search, "page", page));
    }

    @PostMapping
    ModelAndView create(@Valid User user) {
        var created = userService.create(user);
        return new ModelAndView("users/item", "item", created);
    }

    @PatchMapping("/{id}")
    ModelAndView update(@PathVariable Integer id,
                        @RequestParam Map<String, String> patch) {
        var updated = userService.update(id, patch);
        return new ModelAndView("users/item", "item", updated);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    void delete(@PathVariable Integer id) {
        userService.delete(id);
    }

}

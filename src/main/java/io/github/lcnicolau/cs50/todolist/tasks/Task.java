package io.github.lcnicolau.cs50.todolist.tasks;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
class Task {

    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank
    private String description;
    private Boolean done = false;
    private Instant created = Instant.now();

    Task(String description) {
        this.description = description;
    }

}

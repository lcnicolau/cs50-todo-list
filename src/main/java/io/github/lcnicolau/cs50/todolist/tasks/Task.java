package io.github.lcnicolau.cs50.todolist.tasks;

import io.github.lcnicolau.cs50.todolist.planner.Planner;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Entity
@EntityListeners(AuditingEntityListener.class)
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
    @CreatedDate
    private Instant created;
    @CreatedBy
    @ManyToOne(optional = false)
    private Planner author;

    Task(String description) {
        this.description = description;
    }

}

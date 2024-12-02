package io.github.lcnicolau.cs50.todolist.planner;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Planner {

    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank
    private String name;
    @Email
    @Column(unique = true)
    private String email;
    // TODO: Validate password
    private String password;

    Planner(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

}

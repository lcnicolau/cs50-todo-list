package io.github.lcnicolau.cs50.todolist.planner;

import io.github.lcnicolau.cs50.todolist.config.validation.Password;
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

    public static final Planner USER = new Planner("User", "user@todolist.com", "$2a$10$ZQmwDHSz06NG64yxpVrUFu7RnYQHTGvKbfjD8UDLJQYFsim/Zm12q");
    public static final Planner ADMIN = new Planner("Admin", "admin@todolist.com", "$2a$10$LMrCFJFLNC4PjH7.734zy.FOUjGrtXiu4P0nQld1HROf9LGvrPPTa");

    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank
    private String name;
    @Email
    @Column(unique = true)
    private String email;
    @Password
    private String password;

    Planner(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

}

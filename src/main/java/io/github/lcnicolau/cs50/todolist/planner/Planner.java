package io.github.lcnicolau.cs50.todolist.planner;

import io.github.lcnicolau.cs50.todolist.config.validation.Password;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.CredentialsContainer;

import java.time.Instant;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Planner implements CredentialsContainer {

    public static final Planner USER = new Planner("User", "user@todolist.com", "$2a$10$ZQmwDHSz06NG64yxpVrUFu7RnYQHTGvKbfjD8UDLJQYFsim/Zm12q");
    public static final Planner ADMIN = new Planner("Admin", "admin@todolist.com", "$2a$10$LMrCFJFLNC4PjH7.734zy.FOUjGrtXiu4P0nQld1HROf9LGvrPPTa");

    @Id
    @GeneratedValue
    private Integer id;
    @NotBlank
    @Column(nullable = false)
    private String name;
    @Email
    @Column(nullable = false, unique = true)
    private String email;
    @Password
    @Column(nullable = false)
    private String password;
    @NotNull
    @Column(nullable = false)
    private Boolean enabled;
    @CreatedDate
    @Column(nullable = false)
    private Instant created;

    Planner(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = true;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

}

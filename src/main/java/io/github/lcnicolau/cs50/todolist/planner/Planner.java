package io.github.lcnicolau.cs50.todolist.planner;

import io.github.lcnicolau.cs50.todolist.config.validation.Password;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Planner implements CredentialsContainer {

    public static final Planner USER = new Planner("User", "user@todolist.com", "$2a$10$ZQmwDHSz06NG64yxpVrUFu7RnYQHTGvKbfjD8UDLJQYFsim/Zm12q", true, "USER");
    public static final Planner ADMIN = new Planner("Admin", "admin@todolist.com", "$2a$10$LMrCFJFLNC4PjH7.734zy.FOUjGrtXiu4P0nQld1HROf9LGvrPPTa", true, "ADMIN");

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

    @Column(nullable = false)
    private Boolean enabled = true;

    @Column(nullable = false)
    private String roles = "USER";

    @CreatedDate
    @Column(nullable = false)
    private Instant created;

    Planner(String name, String email, String password) {
        this(name, email, password, true);
    }

    Planner(String name, String email, String password, Boolean enabled) {
        this(name, email, password, enabled, "USER");
    }

    Planner(String name, String email, String password, Boolean enabled, String roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    public List<? extends GrantedAuthority> authorities() {
        return Arrays.stream(roles.split(","))
                .map(String::trim)
                .map("ROLE_"::concat)
                .map(SimpleGrantedAuthority::new).toList();
    }

}

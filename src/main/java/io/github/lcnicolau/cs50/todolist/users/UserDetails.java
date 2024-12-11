package io.github.lcnicolau.cs50.todolist.users;

import lombok.Getter;

@Getter
public class UserDetails extends org.springframework.security.core.userdetails.User {

    private final User user;

    UserDetails(User user) {
        super(user.getEmail(),
                user.getPassword(),
                user.getEnabled(),
                true,
                true,
                true,
                user.authorities());
        this.user = user;
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.user.eraseCredentials();
    }

}

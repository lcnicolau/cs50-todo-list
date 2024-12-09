package io.github.lcnicolau.cs50.todolist.planner;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@Getter
public class PlannerUserDetails extends User implements UserDetails {

    private final Planner planner;

    PlannerUserDetails(Planner planner) {
        super(planner.getEmail(),
                planner.getPassword(),
                planner.getEnabled(),
                true,
                true,
                true,
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.planner = planner;
    }

    public Integer getPlannerId() {
        return planner.getId();
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.planner.eraseCredentials();
    }

}

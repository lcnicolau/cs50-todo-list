package io.github.lcnicolau.cs50.todolist.planner;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class PlannerService implements UserDetailsService {

    private final PlannerRepository plannerRepository;
    private final PasswordEncoder passwordEncoder;

    PlannerService(PlannerRepository plannerRepository, PasswordEncoder passwordEncoder) {
        this.plannerRepository = plannerRepository;
        this.passwordEncoder = passwordEncoder;
    }


    Planner signup(Planner planner) {
        if (plannerRepository.findByEmail(planner.getEmail()).isPresent()) {
            throw new DuplicateKeyException("An account already exists for this email address");
        }
        planner.setPassword(passwordEncoder.encode(planner.getPassword()));
        return plannerRepository.save(planner);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return plannerRepository
                .findByEmail(username)
                .map(PlannerUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

}

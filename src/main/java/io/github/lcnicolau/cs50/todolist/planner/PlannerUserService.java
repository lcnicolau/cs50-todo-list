package io.github.lcnicolau.cs50.todolist.planner;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PlannerUserService implements UserDetailsService {

    private final PlannerRepository plannerRepository;
    private final PlannerMapper plannerMapper;
    private final PasswordEncoder passwordEncoder;


    Page<Planner> find(String search, Pageable pageable) {
        var page = (search.isBlank())
                ? plannerRepository.findAll(pageable)
                : plannerRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(search, search, pageable);
        page.getContent().forEach(Planner::eraseCredentials);
        return page;
    }

    private Planner findById(Integer id) {
        return plannerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Planner not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return plannerRepository
                .findByEmail(username)
                .map(PlannerUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Transactional
    public Planner create(Planner planner) {
        if (plannerRepository.findByEmail(planner.getEmail()).isPresent()) {
            throw new DuplicateKeyException("An account already exists for this email address");
        }
        planner.setPassword(passwordEncoder.encode(planner.getPassword()));
        return plannerMapper.eraseCredentials(plannerRepository.save(planner));
    }

    @Transactional
    Planner update(Integer id, Map<String, String> patch) {
        var target = findById(id);
        var updated = plannerMapper.patch(patch, target);
        return plannerMapper.eraseCredentials(plannerRepository.save(updated));
    }

    @Transactional
    void delete(Integer id) {
        var target = findById(id);
        plannerRepository.delete(target);
    }

}

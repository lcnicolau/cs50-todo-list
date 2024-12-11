package io.github.lcnicolau.cs50.todolist.users;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    Page<User> find(String search, Pageable pageable) {
        var page = (search.isBlank())
                ? userRepository.findAll(pageable)
                : userRepository.findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(search, search, pageable);
        page.getContent().forEach(User::eraseCredentials);
        return page;
    }

    private User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "User not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(username)
                .map(UserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Transactional
    public User create(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateKeyException("An account already exists for this email address");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.eraseCredentials(userRepository.save(user));
    }

    @Transactional
    User update(Integer id, Map<String, String> patch) {
        var target = findById(id);
        var updated = userMapper.patch(patch, target);
        return userMapper.eraseCredentials(userRepository.save(updated));
    }

    @Transactional
    void delete(Integer id) {
        var target = findById(id);
        userRepository.delete(target);
    }

}

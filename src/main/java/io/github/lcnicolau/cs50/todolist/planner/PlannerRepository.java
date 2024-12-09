package io.github.lcnicolau.cs50.todolist.planner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface PlannerRepository extends JpaRepository<Planner, Integer> {

    Page<Planner> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email, Pageable pageable);

    Optional<Planner> findByEmail(String email);

}
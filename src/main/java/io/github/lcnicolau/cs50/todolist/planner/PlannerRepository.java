package io.github.lcnicolau.cs50.todolist.planner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface PlannerRepository extends JpaRepository<Planner, Integer> {

    Optional<Planner> findByEmail(String email);

}
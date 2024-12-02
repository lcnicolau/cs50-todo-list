package io.github.lcnicolau.cs50.todolist.planner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PlannerRepository extends JpaRepository<Planner, Integer> {

}
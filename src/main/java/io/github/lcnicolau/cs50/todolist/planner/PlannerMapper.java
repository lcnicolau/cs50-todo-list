package io.github.lcnicolau.cs50.todolist.planner;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Map;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING)
interface PlannerMapper {

    @Mapping(target = "name")
    @Mapping(target = "enabled")
    @BeanMapping(ignoreByDefault = true, nullValuePropertyMappingStrategy = IGNORE)
    Planner patch(Map<String, String> source, @MappingTarget Planner target);

    @Mapping(target = "password", ignore = true)
    Planner eraseCredentials(Planner source);

}

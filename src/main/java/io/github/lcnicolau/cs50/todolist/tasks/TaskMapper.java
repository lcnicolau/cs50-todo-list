package io.github.lcnicolau.cs50.todolist.tasks;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Map;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING)
interface TaskMapper {

    @Mapping(target = "description")
    @Mapping(target = "done")
    @BeanMapping(ignoreByDefault = true, nullValuePropertyMappingStrategy = IGNORE)
    Task patch(Map<String, String> source, @MappingTarget Task target);

}

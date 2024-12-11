package io.github.lcnicolau.cs50.todolist.users;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Map;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.NullValuePropertyMappingStrategy.IGNORE;

@Mapper(componentModel = SPRING)
interface UserMapper {

    @Mapping(target = "name")
    @Mapping(target = "enabled")
    @BeanMapping(ignoreByDefault = true, nullValuePropertyMappingStrategy = IGNORE)
    User patch(Map<String, String> source, @MappingTarget User target);

    @Mapping(target = "password", ignore = true)
    User eraseCredentials(User source);

}

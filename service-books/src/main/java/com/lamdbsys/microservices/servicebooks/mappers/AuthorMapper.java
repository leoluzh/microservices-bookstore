package com.lamdbsys.microservices.servicebooks.mappers;

import com.lamdbsys.microservices.servicebooks.dtos.AuthorDto;
import com.lamdbsys.microservices.servicebooks.entities.Author;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    AuthorDto toDto(Author author);
    Author toEntity(AuthorDto authorDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void merge(AuthorDto authorDto, @MappingTarget Author author);

}

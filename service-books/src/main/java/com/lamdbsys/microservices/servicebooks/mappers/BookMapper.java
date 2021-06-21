package com.lamdbsys.microservices.servicebooks.mappers;

import com.lamdbsys.microservices.servicebooks.dtos.BookDto;
import com.lamdbsys.microservices.servicebooks.entities.Book;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDto toDto(Book book);
    Book toEntity(BookDto bookDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void merge(BookDto bookDto, @MappingTarget Book book);

}

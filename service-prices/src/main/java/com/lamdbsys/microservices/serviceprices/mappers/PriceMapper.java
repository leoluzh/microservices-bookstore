package com.lamdbsys.microservices.serviceprices.mappers;

import com.lamdbsys.microservices.serviceprices.dtos.PriceDto;
import com.lamdbsys.microservices.serviceprices.entities.Price;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    PriceDto toDto(Price price);

}

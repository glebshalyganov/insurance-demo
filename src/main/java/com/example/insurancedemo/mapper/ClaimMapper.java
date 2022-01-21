package com.example.insurancedemo.mapper;

import com.example.insurancedemo.dto.ClaimInputDto;
import com.example.insurancedemo.dto.ClaimOutputDto;
import com.example.insurancedemo.entity.Claim;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public interface ClaimMapper {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Mapping(target = "policy.id", source = "policy")
    Claim fromDto(ClaimInputDto dto);

    @Mapping(target = "policy", source = "policy.name")
    @Mapping(target = "timestamp", qualifiedByName = "formatDateTime")
    ClaimOutputDto fromEntity(Claim entity);

    @Named("formatDateTime")
    default String formatDateTime(OffsetDateTime timestamp) {
        return timestamp.format(formatter);
    }
}

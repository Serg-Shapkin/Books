package com.books.mapper;

import com.books.dto.InformationDto;
import com.books.entity.Information;

public class InformationMapper {
    public static InformationDto toInformationDto(Information information) {
        return InformationDto.builder()
                .id(information.getId())
                .publisher(information.getPublisher())
                .city(information.getCity())
                .year(information.getYear())
                .pages(information.getPages())
                .condition(information.getCondition())
                .build();
    }

    public static Information toInformation(InformationDto informationDto) {
        return Information.builder()
                .id(informationDto.getId())
                .publisher(informationDto.getPublisher())
                .city(informationDto.getCity())
                .year(informationDto.getYear())
                .pages(informationDto.getPages())
                .condition(informationDto.getCondition())
                .build();
    }
}

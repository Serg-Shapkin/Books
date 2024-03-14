package com.books.dto;

import com.books.entity.Information;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class InformationDto {
    private long id;
    private String publisher;
    private String city;
    private int year;
    private int pages;
    private Information.Condition condition;
}

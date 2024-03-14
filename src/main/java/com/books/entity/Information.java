package com.books.entity;

import lombok.*;

import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Information {
    private long id; // автоинкремент в таблице
    private String publisher;    // издательство
    private String city;         // город издательства
    private int year;            // год издательства
    private int pages;           // количество страниц
    private Condition condition; // состояние книги

    public enum Condition {
        NEW,
        GOOD,
        OLD
    }

    @Override
    public String toString() {
        return "Information: " +
                "\n - id" + id +
                "\n - publisher: " + publisher +
                "\n - city: " + city +
                "\n - year: " + year +
                "\n - pages: " + pages +
                "\n - condition: " + condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Information that = (Information) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

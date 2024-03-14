package com.books.entity;

import lombok.*;

import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private long id; // автоинкремент в таблице
    private String author;
    private String description;
    private Information information;

    @Override
    public String toString() {
        return "Book: " +
                "\n - id" + id +
                "\n - author: " + author +
                "\n - description: " + description +
                "\n - information: " + information;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

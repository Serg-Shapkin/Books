package com.books.entity;

import lombok.*;

import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private long id;
    private String author;
    private String title;
    private long user_id;            // кому принадлежит книга


    @Override
    public String toString() {
        return "Book: " +
                "\n - id" + id +
                "\n - author: " + author +
                "\n - title: " + title +
                "\n - user_id: " + user_id;
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

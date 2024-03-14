package com.books.entity;

import lombok.*;

import java.util.Objects;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private long id; // автоинкремент в таблице
    private String name;
    private int age;
    //private List<Book> books;


    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User: " +
                "\n - id" + id +
                "\n - name: " + name +
                "\n - age: " + age;
                //"\n - books: " + books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

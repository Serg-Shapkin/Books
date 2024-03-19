package com.books.servlet;

import com.books.dto.BookDto;
import com.books.entity.Book;
import com.books.service.BookService;
import com.books.service.impl.BookServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/book/*")
public class BookServlet extends HttpServlet {
    private static final Gson gson = new GsonBuilder().create();
    private final BookService bookService = new BookServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining());
        BookDto bookDto = gson.fromJson(json, BookDto.class);
        bookService.create(bookDto);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestPath = request.getRequestURI();
        String[] partPath = requestPath.split("/");

        if (partPath.length == 2 && partPath[1].equals("book")) {
            long id = Long.parseLong(request.getParameter("id"));
            response.getWriter().write(gson.toJson(bookService.getById(id), Book.class));

        } else if (partPath.length == 3 && partPath[1].equals("book") && partPath[2].equals("all")) {
            List<Book> books = bookService.getAll();
            for (Book book : books) {
                response.getWriter().write(gson.toJson(book, Book.class) + "\n");
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining());
        BookDto bookDto = gson.fromJson(json, BookDto.class);
        bookService.update(bookDto);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        bookService.delete(id);
    }
}

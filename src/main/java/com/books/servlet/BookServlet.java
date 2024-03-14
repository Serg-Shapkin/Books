package com.books.servlet;

import com.books.dto.BookDto;
import com.books.service.BookService;
import com.books.service.impl.BookServiceImp;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/book/*")
public class BookServlet extends HttpServlet {
    private static final Gson gson = new GsonBuilder().create();
    private final BookService bookService = new BookServiceImp();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining());
        BookDto bookDto = gson.fromJson(json, BookDto.class);
        bookService.create(bookDto);
    }
}

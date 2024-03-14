package com.books.servlet;

import com.books.dto.UserDto;
import com.books.service.UserService;
import com.books.service.impl.UserServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

    private static final Gson gson = new GsonBuilder().create();
    private final UserService userService = new UserServiceImpl(); // здесь исправил

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String json = request.getReader().lines().collect(Collectors.joining());
        UserDto userDto = gson.fromJson(json, UserDto.class); // из json в userDto
        userService.create(userDto); // отдали в сервис

        response.setContentType("application/json");
        response.getWriter().write(json); // отдали json
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().write("Method doGet");
    }
}

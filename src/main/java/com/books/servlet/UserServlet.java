package com.books.servlet;

import com.books.dto.UserDto;
import com.books.entity.User;
import com.books.service.UserService;
import com.books.service.impl.UserServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/user/*")
public class UserServlet extends HttpServlet {

    private static final Gson gson = new GsonBuilder().create();
    private final UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining());
        UserDto userDto = gson.fromJson(json, UserDto.class); // из json в toUserShort
        userService.create(userDto);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestPath = request.getRequestURI();
        String[] partPath = requestPath.split("/");

        if (partPath.length == 2 && partPath[1].equals("user")) {
            long id = Long.parseLong(request.getParameter("id"));
            response.getWriter().write(gson.toJson(userService.getById(id), User.class));

        } else if (partPath.length == 3 && partPath[1].equals("user") && partPath[2].equals("all")) {
            List<User> users = userService.getAll();
            for (User user : users) {
                response.getWriter().write(gson.toJson(user, User.class) + "\n");
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String json = request.getReader().lines().collect(Collectors.joining());
        UserDto userDto = gson.fromJson(json, UserDto.class);
        userService.update(userDto);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long id = Long.parseLong(request.getParameter("id"));
        userService.delete(id);
    }
}

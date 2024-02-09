package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.model.User;

import java.util.Collections;
import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {

        var course1 = new Course("Java", "Fantastic Java course");
        var course2 = new Course("Python", "Amazing Python course");
        course1.setId(1L);
        course2.setId(2L);
        List<Course> courses = List.of(course1, course2);

        var user1 = new User("petya", "petya@mail.ru", "12345678");
        var user2 = new User("vasya", "vasya@mail.ru", "87654321");
        user1.setId(1L);
        user2.setId(2L);
        List<User> users = List.of(user1,user2);

        // Создаем приложение
        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });
        // Описываем, что загрузится по адресу /
        app.get("/", ctx -> ctx.render("index.jte"));

        app.get("/hello", ctx -> {
            var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            ctx.result("Hello, " + name +  "!");
        });

        app.get("/courses", ctx -> {
            var header = "Курсы по программированию";
            var page = new CoursesPage(courses, header);
            ctx.render("courses/index.jte", Collections.singletonMap("page", page));
        });

        app.get("/courses/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var course = courses.stream()
                    .filter(c -> c.getId() == id)
                    .findAny().orElse(null);
                    var page = new CoursePage(course);
            ctx.render("courses/show.jte", Collections.singletonMap("page", page));
        });

        app.get("/users", ctx -> {
            var page = new UsersPage(users);
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });

        app.get("/users/{id}", ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var user = users.stream()
                    .filter(c -> c.getId() == id)
                    .findAny()
                    .orElse(null);
            var page = new UserPage(user);
            ctx.render("users/show.jte", Collections.singletonMap("page", page));
        });


        app.start(7070); // Стартуем веб-сервер
    }
}

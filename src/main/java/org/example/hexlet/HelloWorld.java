package org.example.hexlet;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;
import io.javalin.validation.ValidationException;
import org.example.hexlet.dto.courses.BuildCoursePage;
import org.example.hexlet.dto.courses.CoursePage;
import org.example.hexlet.dto.courses.CoursesPage;
import org.example.hexlet.dto.users.BuildUserPage;
import org.example.hexlet.dto.users.UserPage;
import org.example.hexlet.dto.users.UsersPage;
import org.example.hexlet.model.Course;
import org.example.hexlet.model.User;
import org.example.hexlet.repository.CourseRepository;
import org.example.hexlet.repository.UserRepository;
import org.example.hexlet.utils.NamedRoutes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HelloWorld {
    public static void main(String[] args) {

        var course1 = new Course("Java", "Fantastic Java course");
        var course2 = new Course("Python", "Amazing Python course");
        CourseRepository.save(course1);
        CourseRepository.save(course2);

        var user1 = new User("petya", "petya@mail.ru", "12345678");
        var user2 = new User("vasya", "vasya@mail.ru", "87654321");
        UserRepository.save(user1);
        UserRepository.save(user2);

        // Создаем приложение
        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });
        // Описываем, что загрузится по адресу /
        app.get(NamedRoutes.rootPath(), ctx -> ctx.render("index.jte"));

        app.get("/hello", ctx -> {
            var name = ctx.queryParamAsClass("name", String.class).getOrDefault("World");
            ctx.result("Hello, " + name +  "!");
        });

        app.get(NamedRoutes.coursesPath(), ctx -> {
            var header = "Курсы по программированию";
            var term = ctx.queryParam("term");
            List<Course> filteredCourses = new ArrayList<>();
            if (term != null) {
                filteredCourses = CourseRepository.search(term);
            } else {
                filteredCourses = CourseRepository.getEntities();
            }
            var page = new CoursesPage(filteredCourses, header, term);
            ctx.render("courses/index.jte", Collections.singletonMap("page", page));
        });

        app.get(NamedRoutes.buildCoursePath(), ctx -> {
            var page = new BuildCoursePage();
            ctx.render("courses/build.jte", Collections.singletonMap("page", page));
        });

        app.post(NamedRoutes.coursesPath(), ctx -> {
            var name = ctx.formParam("name");
            var description = ctx.formParam("description");
            try {
                name = ctx.formParamAsClass("name", String.class)
                        .check(value -> value.length() >= 3, "Слишком короткое название!").get();
                description = ctx.formParamAsClass("description", String.class)
                        .check(value -> value.length() >= 10, "Слишком короткое описание!")
                        .get();
                var course = new Course(name, description);
                CourseRepository.save(course);
                ctx.redirect(NamedRoutes.coursesPath());
            } catch (ValidationException e) {
                var page = new BuildCoursePage(name, description, e.getErrors());
                ctx.render("courses/build.jte", Collections.singletonMap("page", page));
            }
        });

        app.get(NamedRoutes.coursePath("{id}"), ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var course = CourseRepository.find(id)
                    .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
            var page = new CoursePage(course);
            ctx.render("courses/show.jte", Collections.singletonMap("page", page));
        });

        app.get(NamedRoutes.usersPath(), ctx -> {
            var page = new UsersPage(UserRepository.getEntities());
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });

        app.get(NamedRoutes.buildUserPath(), ctx -> {
            var page = new BuildUserPage();
            ctx.render("users/build.jte", Collections.singletonMap("page", page));
        });

        app.post(NamedRoutes.usersPath(), ctx -> {
            var name = ctx.formParam("name");
            var email = ctx.formParam("email").trim().toLowerCase();;
            try {
                var passwordConfirmation = ctx.formParam("passwordConfirmation");
                var password = ctx.formParamAsClass("password", String.class)
                        .check(value -> value.equals(passwordConfirmation), "Пароли не совпадают")
                        .check(value -> value.length() >= 8, "Короткий пароль!")
                        .get();
                var user = new User(name, email, password);
                UserRepository.save(user);
                ctx.redirect(NamedRoutes.usersPath());
            } catch (ValidationException e) {
                var page = new BuildUserPage(name, email, e.getErrors());
                ctx.render("users/build.jte", Collections.singletonMap("page", page));
            }
        });

        app.get(NamedRoutes.userPath("{id}"), ctx -> {
            var id = ctx.pathParamAsClass("id", Long.class).get();
            var user = UserRepository.find(id)
                    .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
            var page = new UserPage(user);
            ctx.render("users/show.jte", Collections.singletonMap("page", page));
        });



        app.start(7070); // Стартуем веб-сервер
    }
}

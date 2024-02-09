package org.example.hexlet.utils;

public class NamedRoutes {

    public static String rootPath() {
        return "/";
    }

    // Маршруты пользователей
    public static String usersPath() {
        return "/users";
    }

    public static String buildUserPath() {
        return "/users/build";
    }

    // Это нужно, чтобы не преобразовывать типы снаружи
    public static String userPath(Long id) {
        return userPath(String.valueOf(id));
    }

    public static String userPath(String id) {
        return "/users/" + id;
    }

    public static String editUserPath(Long id) {
        return editUserPath(String.valueOf(id));
    }

    public static String editUserPath(String id) {
        return "/users/" + id + "/edit";
    }

    // Маршруты курсов
    public static String coursesPath() {
        return "/courses";
    }

    public static String buildCoursePath() {
        return "/courses/build";
    }

    // Это нужно, чтобы не преобразовывать типы снаружи
    public static String coursePath(Long id) {
        return coursePath(String.valueOf(id));
    }

    public static String coursePath(String id) {
        return "/courses/" + id;
    }

    public static String editCoursePath(Long id) {
        return editCoursePath(String.valueOf(id));
    }

    public static String editCoursePath(String id) {
        return "/courses/" + id + "/edit";
    }
}

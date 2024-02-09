package org.example.hexlet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.hexlet.model.User;

public class UserRepository {
    // Тип зависит от того, с какой сущностью идет работа в упражнении
    private static List<User> entities = new ArrayList<User>();

    public static void save(User user) {
        // Формируется идентификатор
        user.setId((long) entities.size() + 1);
        entities.add(user);
    }

    public static Optional<User> find(Long id) {
        var user = entities.stream()
                .filter(entity -> entity.getId().equals(id))
                .findAny();
        return user;
    }

    public static List<User> getEntities() {
        return entities;
    }
}
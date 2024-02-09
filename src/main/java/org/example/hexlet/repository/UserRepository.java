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
        if (user.getId() == null) {
            user.setId((long) entities.size() + 1);
            entities.add(user);
        } else {
            UserRepository.find(user.getId()).get().setName(user.getName());
            UserRepository.find(user.getId()).get().setEmail(user.getEmail());
            UserRepository.find(user.getId()).get().setPassword(user.getPassword());
        }
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

    public static void delete(Long id) {
        var user = UserRepository.find(id).get();
        entities.remove(user);
    }
}
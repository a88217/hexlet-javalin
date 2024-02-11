package org.example.hexlet.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.example.hexlet.model.Course;

public class CourseRepository {
    private static List<Course> entities = new ArrayList<Course>();

    public static void save(Course course) {
        if (course.getId() == null) {
            course.setId((long) entities.size() + 1);
            entities.add(course);
        } else {
            UserRepository.find(course.getId()).get().setName(course.getName());
            UserRepository.find(course.getId()).get().setEmail(course.getDescription());
        }
    }

    public static List<Course> search(String term) {
        var courses = entities.stream()
                .filter(entity -> entity.getName().startsWith(term))
                .toList();
        return courses;
    }

    public static Optional<Course> find(Long id) {
        var course = entities.stream()
                .filter(entity -> entity.getId().equals(id))
                .findAny();
        return course;
    }

    public static List<Course> getEntities() {
        return entities;
    }

    public static void delete(Long id) {
        var course = CourseRepository.find(id).get();
        entities.remove(course);
    }
}
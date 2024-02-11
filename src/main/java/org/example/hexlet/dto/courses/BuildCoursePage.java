package org.example.hexlet.dto.courses;

import java.util.List;
import java.util.Map;

import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.hexlet.dto.BasePage;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BuildCoursePage extends BasePage {
    private String name;
    private String description;
    private Map<String, List<ValidationError<Object>>> errors;
}
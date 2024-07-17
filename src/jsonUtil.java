package com.example.json;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.Map;

public class JsonUtil {

    public static JsonObject createStudentJsonObject(Student student) {
        JsonObjectBuilder gradesBuilder = Json.createObjectBuilder();
        for (Map.Entry<String, Integer> entry : student.getGrades().entrySet()) {
            gradesBuilder.add(entry.getKey(), entry.getValue());
        }

        return Json.createObjectBuilder()
                .add("name", student.getName())
                .add("grades", gradesBuilder)
                .build();
    }
}

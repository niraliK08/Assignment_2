package com.example.json;

import javax.json.*;
import java.io.*;
import java.util.Map;

public class JsonUtils {

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

    public static JsonObject readJsonFile() {
        try (FileReader fileReader = new FileReader(JSON_FILE_PATH)) {
            JsonReader jsonReader = Json.createReader(fileReader);
            return jsonReader.readObject();
        } catch (IOException e) {
            System.out.println("Error: Failed to read JSON data from file");
            e.printStackTrace();
            return null;
        }
    }

    public static void writeJsonFile(JsonObject jsonObject) {
        try (FileWriter fileWriter = new FileWriter(JSON_FILE_PATH)) {
            try (JsonWriter jsonWriter = Json.createWriter(fileWriter)) {
                jsonWriter.writeObject(jsonObject);
            }
        } catch (IOException e) {
            System.out.println("Error: Failed to write JSON data to file");
            e.printStackTrace();
        }
    }

    public static void addStudent(Student student) {
        JsonObject existingJson = readJsonFile();

        JsonObject newStudentJson = createStudentJsonObject(student);

        JsonArrayBuilder studentsArrayBuilder = Json.createArrayBuilder();
        if (existingJson.containsKey("students")) {
            JsonArray existingStudents = existingJson.getJsonArray("students");
            existingStudents.forEach(studentsArrayBuilder::add);
        }
        studentsArrayBuilder.add(newStudentJson);

        JsonObject updatedJson = Json.createObjectBuilder()
                .add("students", studentsArrayBuilder)
                .build();
    }
}

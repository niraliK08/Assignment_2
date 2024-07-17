package com.example.json;

import javax.json.*;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        //part A
        Map<String, Integer> gradesJohn = new HashMap<>();
        gradesJohn.put("Math", 85);
        gradesJohn.put("Physics", 90);
        gradesJohn.put("Chemistry", 78);

        Map<String, Integer> gradesJane = new HashMap<>();
        gradesJane.put("Biology", 88);
        gradesJane.put("Math", 92);
        gradesJane.put("English", 81);

        Student john = new Student("John Doe", gradesJohn);
        Student jane = new Student("Jane Smith", gradesJane);

        JsonArrayBuilder studentsArrayBuilder = Json.createArrayBuilder();

        studentsArrayBuilder.add(JsonUtils.createStudentJsonObject(john));
        studentsArrayBuilder.add(JsonUtils.createStudentJsonObject(jane));

        JsonObject studentsJsonObject = Json.createObjectBuilder()
                .add("students", studentsArrayBuilder)
                .build();

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("students.json");

            JsonWriter jsonWriter = Json.createWriter(fileWriter);

            jsonWriter.writeObject(studentsJsonObject);

            jsonWriter.close();

        }

        //part B
        JsonUtils.generateInitialJson();
        JsonProcessor processor = new JsonProcessor();
        processor.readProcessAndUpdateJson();

    }
}

package com.example.json;

import javax.json.*;
import java.io.FileWriter;
import java.io.FileReader;
import java.util.Map;

public class JsonProcessor {

    public static void readAndUpdate() {
        try {

            FileReader fileReader = new FileReader("students.json");
            JsonReader reader = Json.createReader(fileReader);
            JsonObject jsonObject = reader.readObject();
            JsonArray studentsArray = jsonObject.getJsonArray("students");


            JsonArrayBuilder updatedStudentsArrayBuilder = Json.createArrayBuilder();
            for (int i = 0; i < studentsArray.size(); i++) {
                JsonObject studentObject = studentsArray.getJsonObject(i);


                String name = studentObject.getString("name");
                JsonObject gradesObject = studentObject.getJsonObject("grades");

                // Calculate average marks
                double sum = 0;
                int count = 0;
                for (Map.Entry<String, JsonValue> entry : gradesObject.entrySet()) {
                    if (entry.getValue() instanceof JsonNumber) {
                        sum += ((JsonNumber) entry.getValue()).doubleValue();
                        count++;
                    }
                }
                double averageMarks = count > 0 ? sum / count : 0;

                int rank;
                if (averageMarks >= 90) {
                    rank = 1;
                } else if (averageMarks >= 80) {
                    rank = 2;
                } else if (averageMarks >= 70) {
                    rank = 3;
                } else {
                    rank = 4;
                }

                JsonObject updatedStudent = Json.createObjectBuilder()
                        .add("name", name)
                        .add("grades", gradesObject)
                        .add("average_marks", averageMarks)
                        .add("rank", rank)
                        .build();

                updatedStudentsArrayBuilder.add(updatedStudent);
            }

            JsonObject updatedJson = Json.createObjectBuilder()
                    .add("students", updatedStudentsArrayBuilder)
                    .build();
            FileWriter fileWriter = new FileWriter("updated_students.json");
            JsonWriter jsonWriter = Json.createWriter(fileWriter);
            jsonWriter.writeObject(updatedJson);

        }
    }
}

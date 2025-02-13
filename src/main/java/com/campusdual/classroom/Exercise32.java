package com.campusdual.classroom;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Exercise32 {

    public static void main(String[] args) {
        String text = generateStringToSave(null);
        printToFile(text);
    }

    public static String generateStringToSave(String string) {
        if (string == null || string.isEmpty()) {
            return generateUserInputToSave();
        }
        return string;
    }

    private static String generateUserInputToSave() {
        StringBuilder sb = new StringBuilder();
        System.out.println("Escribe el texto que quieres guardar. Pulsa 'ENTER' dos veces para finalizar:");

        String string;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (!(string = reader.readLine()).isEmpty()) {
                sb.append(string).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.err.println("Error leyendo entrada del usuario: " + e.getMessage());
        }

        return sb.toString();
    }

    public static void printToFile(String string) {
        Path filePath = Path.of("src/main/resources/data.txt");

        try {
            Files.createDirectories(filePath.getParent());
            Files.writeString(filePath, string, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            System.out.println("Texto guardado en " + filePath.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error escribiendo en el archivo: " + e.getMessage());
        }
    }
}

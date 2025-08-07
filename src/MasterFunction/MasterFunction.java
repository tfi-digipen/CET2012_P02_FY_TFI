package MasterFunction;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MasterFunction {
    public static boolean checkIfFileExist(String fileName) {
        return Files.exists(Path.of(fileName));
    }

    public static List<String> getFileContent(String fileName) {
        try {
            return Files.readAllLines(Path.of(fileName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void writeToFile(String fileName, String fileContent) {
        try {
            Files.writeString(Path.of(fileName), fileContent, StandardCharsets.US_ASCII, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String toTitleCase(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public static boolean checkIsValidEmail(String input) {
        String pattern = "^[\\w]+((((\\.-)*(\\.){0,1}[\\w]{1,})*((-\\.)*(-){0,1}[\\w]{1,})*)*" +
                "@[0-9a-zA-Z]+(((\\.-)*(\\.){0,1}[0-9a-zA-Z]{1,})*((-\\.)*(-){0,1}[0-9a-zA-Z]{1,})*)*" +
                "(\\.[a-z]{2,3}){1}){0,1}$";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(input);
        return matcher.matches();
    }
}
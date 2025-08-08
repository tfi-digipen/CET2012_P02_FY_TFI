package MasterFunction;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MasterFunction containing several methods
 */
public class MasterFunction {
    /**
     * Check if file exist method
     *
     * @param fileName Complete path + filename
     * @return true if file exist
     */
    public static boolean checkIfFileExist(String fileName) {
        return Files.exists(new File(fileName).toPath());
    }

    /**
     * Get contents of the file
     *
     * @param fileName Complete path + filename
     * @return content of the files by lines
     */
    public static List<String> getFileContent(String fileName) {
        try {
            return Files.readAllLines(new File(fileName).toPath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Write content to file
     *
     * @param fileName    Complete path + filename
     * @param fileContent Content to write
     */
    public static void writeToFile(String fileName, String fileContent) {
        try {
            Files.write(new File(fileName).toPath(), fileContent.getBytes(StandardCharsets.US_ASCII), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to convert first character to Uppercase and the rest to Lowercase
     *
     * @param input Input to be converted
     * @return Converted input
     */
    public static String toTitleCase(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    /**
     * Method to check whether data3 is valid email or valid data3
     *
     * @param input Input to check
     * @return true if input valid
     */
    public static boolean checkIsValidEmailOrData3(String input) {
        String pattern = "^[\\w]+((((\\.){0,1}[\\w]{1,})*((-){0,1}[\\w]{1,})*)*" +
                "@[0-9a-zA-Z]+(((\\.){0,1}[0-9a-zA-Z]{1,})*((-){0,1}[0-9a-zA-Z]{1,})*)*" +
                "(\\.[a-z]{2,3}){1}){0,1}$";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(input);
        return matcher.matches();
    }
}
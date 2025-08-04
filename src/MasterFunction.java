import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MasterFunction {
    public static boolean checkIfFileExist(String fileName) {
        return false;
    }

    public static String[] getFileContent(String fileName) {
        ArrayList<String> fileContentList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            fileContentList.add(br.readLine());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return fileContentList.toArray(new String[fileContentList.size()]);
    }

    public static void writeToFile(String fileName, String fileContent) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false))) {
            bw.write(fileContent);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String toTitleCase(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public static boolean checkIsValidEmail(String input) {
        //*String pattern = "^[0-9a-zA-Z_]+(?:.[0-9a-zA-Z_]+)*(?:-[0-9a-zA-Z_.]+)*[0-9a-zA-Z_]*@[0-9a-zA-Z]+(?:.[0-9a-zA-Z]+)*(?:-[0-9a-zA-Z.]+)*[0-9a-zA-Z_]*(?:\.[a-z]{2,3})$";
//        String pattern = "^[0-9a-zA-Z_]+(?:(?:.-.){0,}(?:.-){0,}(?:.){0,1}[0-9a-zA-Z_])*(?:(?:-.-){0,}(?:-.){0,}(?:-){0,1}[0-9a-zA-Z_])*@[0-9a-zA-Z]+(?:(?:.-.){0,}(?:.-){0,}(?:.){0,1}[0-9a-zA-Z])*(?:(?:-.-){0,}(?:-.){0,}(?:-){0,1}[0-9a-zA-Z])*(?:\\.[a-z]{2,3})$";
//        Pattern p = Pattern.compile(pattern);
//        Matcher matcher = p.matcher(input);
//        if (matcher.matches()) {
//            var split = input.split("@");
//            var firstChar = split[0].charAt(0);
//            var lastChar = split[0].charAt(split[0].length() - 1);
//            if (firstChar == '.' || firstChar == '-' || lastChar == '.' || lastChar == '-') {
//                return false;
//            }
//            int dotCount = 0;
//            int dashCount = 0;
//            for (var c : split[0].toCharArray()) {
//                if (c == '.')
//                    dotCount++;
//                else if (c == '-')
//                    dashCount++;
//            }
//            return (dotCount <= 1 && dashCount <= 2);
//        }
//        return false;
        return true;
    }
}
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MasterFunction {
    public static boolean checkIfFileExist(String fileName) {
        return false;
    }

    public static String[] getFileContent(String fileName) {
        return null;
    }

    public static String toTitleCase(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public static boolean checkIsValidEmail(String input) {
        String pattern = "\\A[A-Za-z0-9._-]+@[A-Za-z0-9.-]+\\.[a-z]{2,3}\\Z";
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(input);
        if (matcher.matches()) {
            var split = input.split("@");
            var firstChar = split[0].charAt(0);
            var lastChar = split[0].charAt(split[0].length() - 1);
            if (firstChar == '.' || firstChar == '-' || lastChar == '.' || lastChar == '-') {
                return false;
            }
            int dotCount = 0;
            int dashCount = 0;
            for (var c : split[0].toCharArray()) {
                if (c == '.')
                    dotCount++;
                else if (c == '-')
                    dashCount++;
            }
            return (dotCount <= 1 && dashCount <= 2);
        }
        return false;
    }
}
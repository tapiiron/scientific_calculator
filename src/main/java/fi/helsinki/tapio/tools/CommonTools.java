package fi.helsinki.tapio.tools;

public class CommonTools {

    public static boolean isNumber(String string) {
        if (string == null || string.isEmpty()) {
            return false;
        }
        try {
            Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}

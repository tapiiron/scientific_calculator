package fi.helsinki.tapio.tools;

public class CommonTools {

    /**
     * Determines whether the given string can be parsed as a valid number.
     *
     * @param string the input string to check for numeric validity
     * @return {@code true} if the string is not null, not empty, and can be parsed as a number;
     * {@code false} otherwise
     */
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

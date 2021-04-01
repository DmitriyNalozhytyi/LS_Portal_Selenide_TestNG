package utils;

public class CustomRandom {

    private static final String text = "1234567890-=!@#$%^&*()_+qwertyuiop[]QWERTYUIOP{}asdfghjkl;'\\ASDFGHJKL:\"|zxcvbnm,./ZXCVBNM<>?";
    public static final  String PSWD_TEXT = "1234567890qwertyuiopQWERTYUIOPasdfghjklASDFGHJKLzxcvbnmZXCVBNM";
    public static final  String ALPHABET_TEXT = "qwertyuiopQWERTYUIOPasdfghjklASDFGHJKLzxcvbnmZXCVBNM";
    public static final  String ALPHABET_UPPER_CASE = "ABCDEFGHIKLMNOPQRSTVXYZ";
    public static final  String ALPHABET_LOWER_CASE = "abcdefghiklmnopqrstvxyz";
    public static final  String DIGITS = "1234567890";

    public static String getText(String text, int length) {
        StringBuffer str = new StringBuffer(length);
        int textLength = text.length();
        for (int i = 0; i < length; i++) {
            int ind = (int) Math.round(Math.random() * textLength) - 1;
            if (ind < 0) {
                ind = 0;
            }
            str.append(text.charAt(ind));
        }
        return str.toString();
    }
    public static String getText(int length) {
        return getText(text, length);
    }

    public static String getAlphabetText(int length) {
        return getText(ALPHABET_TEXT, length);
    }

    public static String getPswd(int length) {
        return getText(PSWD_TEXT, length);
    }

    public static String getSecurePswd(int lowerCaseLength, int upperCaseLength, int digitLength) {
        return getText(ALPHABET_UPPER_CASE, upperCaseLength) +  getText(ALPHABET_LOWER_CASE, lowerCaseLength) +  getText(DIGITS, digitLength);
    }
}
import java.util.Arrays;

class Kmp {
    public static void main(String[] args) {
        String text = "ABDAABAABCCDVCABCADEEQAAA";
        String pattern = "ABCA";
        search(text, pattern);

    }
    static void search(String text, String pattern) {
        System.out.format("Search [%s] in [%s]%n", pattern, text);
        int [] lps = computeTable(pattern);
        int textIndex = 0, textLen = text.length();
        int patternIndex = 0, patternLen = pattern.length();
        while(textIndex < textLen) {
            // match case
            if (text.charAt(textIndex) == pattern.charAt(patternIndex)) {
                ++textIndex;
                ++patternIndex;
            } 
            if (patternIndex == patternLen) {
                // find pattern in text
                System.out.format("Find match at index %s%n",textIndex - patternLen);
                patternIndex = lps[patternIndex - 1];
            } else if (textIndex < textLen && pattern.charAt(patternIndex) != text.charAt(textIndex)) {
                // mismatch case
                if (patternIndex != 0) {
                    // go to prev precomputed value
                    patternIndex = lps[patternIndex - 1];
                } else {
                    ++textIndex;
                }
            }
        }
    }
    static int[] computeTable(String pattern) {
        int m = pattern.length();
        int [] lps = new int [m];
        int len = 0;
        int index = 1;
        while(index < m) {
            if (pattern.charAt(index) == pattern.charAt(len)) {
                lps[index++] = ++len;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    ++index;
                }
            }
        }
        return lps;

    }
}
package committee.nova.petphrase.util;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class StringUtil {
    static final List<Character> punctuations = List.of('!', '?', '.', '(', ')', '！', '？', '。', '（', '）', '~', '”', '“', '‘', '’', '"', '\'');

    public static int getLastPunc(String string) {
        final int length = string.length();
        if (length == 1) return (punctuations.contains(string.charAt(0))) ? -1 : 0;
        return getPunc(string, length - 1);
    }

    public static int getPunc(String string, int index) {
        return (!punctuations.contains(string.charAt(index))) ? index : (index == 0) ? -1 : getPunc(string, index - 1);
    }

    public static String fillPetPhraseIn(String original, String petPhrase, List<String> filteredPrefix) {
        if (original.isEmpty() || original.charAt(0) == '/' || original.charAt(0) == '!') return original;

        for (String s : filteredPrefix) {
            if (original.contains(s)) return original;
        }
        
        final int index = getLastPunc(original) + 1;
        if (index == 0) return original;
        return StringUtils.substring(original, 0, index) + petPhrase + StringUtils.substring(original, index);
    }
}

package data.raw.process.stem.porter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PorterRuleStep4 {
    private static final String VOWEL = "aeiou";
    private static final String VOWEL_CONSONANT_REGEX = "([" + VOWEL + "][a-z&&[^" + VOWEL + "]])";

    protected String stemmerProcess(final String rawWords) {
        List<String> splitRawWords = new ArrayList<>(Arrays.asList(rawWords.split(" ")));
        StringBuilder stemRawWords = new StringBuilder();
        splitRawWords.forEach(word -> {
            if ((word.endsWith("ance"))) {
                word = wordEndingANCE(word);
            } else if (word.endsWith("ent")) {
                word = wordEndingENT(word);
            } else if (word.endsWith("IVE")) {
                word = wordEndingIVE(word);
            }
            stemRawWords.append(word + " ");
        });
        return stemRawWords.toString();

    }

    private String wordEndingANCE(final String word) {
        String wordMorphology = "ance";
        Pattern patternVowelConsonant = Pattern.compile(VOWEL_CONSONANT_REGEX);
        Matcher matcher = patternVowelConsonant.matcher(word);
        int regexMatchOccurrence = 0;
        while (matcher.find()) {
            if (++regexMatchOccurrence > 1) {
                return word.replaceAll("(" + wordMorphology + ")\\b", "ic");
            }
        }
        return word;
    }

    private String wordEndingENT(final String word) {
        String wordMorphology = "ent";
        Pattern patternVowelConsonant = Pattern.compile(VOWEL_CONSONANT_REGEX);
        Matcher matcher = patternVowelConsonant.matcher(word);
        int regexMatchOccurrence = 0;
        while (matcher.find()) {
            if (++regexMatchOccurrence > 1) {
                return word.replaceAll("(" + wordMorphology + ")\\b", "");
            }
        }
        return word;
    }

    private String wordEndingIVE(final String word) {
        String wordMorphology = "ive";
        Pattern patternVowelConsonant = Pattern.compile(VOWEL_CONSONANT_REGEX);
        Matcher matcher = patternVowelConsonant.matcher(word);
        int regexMatchOccurrence = 0;
        while (matcher.find()) {
            if (++regexMatchOccurrence > 1) {
                return word.replaceAll("(" + wordMorphology + ")\\b", "");
            }
        }
        return word;
    }
}

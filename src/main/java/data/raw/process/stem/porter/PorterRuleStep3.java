package data.raw.process.stem.porter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PorterRuleStep3 {
    private static final String VOWEL = "aeiou";
    private static final String VOWEL_CONSONANT_REGEX = "([" + VOWEL + "][a-z&&[^" + VOWEL + "]])";

    protected String stemmerProcess(final String rawWords) {
        List<String> splitRawWords = new ArrayList<>(Arrays.asList(rawWords.split(" ")));
        StringBuilder stemRawWords = new StringBuilder();
        splitRawWords.forEach(word -> {
            if (word.endsWith("icate")) {
                word = wordEndingICATE(word);
            } else if (word.endsWith("ful")) {
                word = wordEndingFUL(word);
            } else if (word.endsWith("ness")) {
                word = wordEndingNESS(word);
            }
            stemRawWords.append(word + " ");
        });
        return stemRawWords.toString();

    }

    private String wordEndingICATE(final String word) {
        String wordMorphology = "icate";
        Pattern patternVowelConsonant = Pattern.compile(VOWEL_CONSONANT_REGEX);
        Matcher matcher = patternVowelConsonant.matcher(word);
        int regexMatchOccurrence = 0;
        while (matcher.find()) {
            if (++regexMatchOccurrence > 2) {
                return word.replaceAll("(" + wordMorphology + ")\\b", "ic");
            }
        }
        return word;
    }

    private String wordEndingFUL(final String word) {
        String wordMorphology = "ful";
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

    private String wordEndingNESS(final String word) {
        String wordMorphology = "ness";
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

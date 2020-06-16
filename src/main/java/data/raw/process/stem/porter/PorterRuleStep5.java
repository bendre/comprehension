package data.raw.process.stem.porter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PorterRuleStep5 {
    private static final String VOWEL = "aeiou";
    private static final String VOWEL_CONSONANT_REGEX = "([" + VOWEL + "][a-z&&[^" + VOWEL + "]])";

    protected String stemmerProcess(final String rawWords) {
        List<String> words = new ArrayList<>(Arrays.asList(rawWords.split(" ")));
        StringBuilder stemRawWords = new StringBuilder();
        words.forEach(word -> {
            if (word.endsWith("e")) {
                word = wordEndingE(word);
            } else if (word.endsWith("ness")) {
                word = wordEndingNESS(word);
            } else if (word.endsWith("ll")) {
                word = wordEndingLL(word);
            }
            stemRawWords.append(word + " ");
        });

        return stemRawWords.toString();

    }

    private String wordEndingE(final String word) {
        String wordMorphology = "e";
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
        Pattern patternConsonantVowelConsonant = Pattern.compile("([a-z&&[^" + VOWEL + "]])([" + VOWEL + "])([a-z&&[^" + VOWEL + "wxy]])\\b");
        Matcher matcher = patternVowelConsonant.matcher(word);
        int regexMatchOccurrence = 0;
        while (matcher.find()) {
            if (++regexMatchOccurrence == 1) {
                Matcher matcherVCV = patternConsonantVowelConsonant.matcher(word);
                if (!matcherVCV.find()) {
                    return word.replaceAll("(" + wordMorphology + ")\\b", "e");
                }
            }
        }
        return word;
    }

    private String wordEndingLL(final String word) {
        String wordMorphology = "ll";
        Pattern patternVowelConsonant = Pattern.compile(VOWEL_CONSONANT_REGEX);
        Matcher matcher = patternVowelConsonant.matcher(word);
        int regexMatchOccurrence = 0;
        while (matcher.find()) {
            if (++regexMatchOccurrence > 1) {
                return word.replaceAll("(" + wordMorphology + ")\\b", "l");
            }
        }
        return word;
    }
}

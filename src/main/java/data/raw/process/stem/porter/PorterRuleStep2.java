package data.raw.process.stem.porter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class PorterRuleStep2 {
    private static final String VOWEL = "aeiou";
    private static final String VOWEL_CONSONANT_REGEX = "([" + VOWEL + "][a-z&&[^" + VOWEL + "]])";

    protected String stemmerProcess(final String rawWords) {
        StringBuilder stemRawWords = new StringBuilder();
        List<String> splitRawWords = new ArrayList<>(Arrays.asList(rawWords.split(" ")));
        splitRawWords.forEach(word -> {
            if (word.endsWith("ational")) {
                word = step2WordEndATIONAL(word);
            } else if (word.endsWith("ization")) {
                word = step2WordEndIZATION(word);
            } else if (word.endsWith("biliti")) {
                word = step2WordEndBILITI(word);
            }
            stemRawWords.append(word + " ");
        });
        return stemRawWords.toString();
    }

    private String step2WordEndATIONAL(final String word) {
        final String wordMorphology = "ational";
        final Integer occurrenceMaxLimit = 3;
        Pattern patternVowelConsonant = Pattern.compile("");
        Matcher matcher = patternVowelConsonant.matcher(word);
        int regexMatchOccurrence = 0;
        while (matcher.find()) {
            if (++regexMatchOccurrence > occurrenceMaxLimit) {
                return word.replaceAll("(" + wordMorphology + ")\\b", "ate");
            }
        }
        return word;
    }

    private String step2WordEndIZATION(final String word) {
        final String wordMorphology = "ization";
        final Integer occurrenceMaxLimit = 3;
        Pattern patternVowelConsonant = Pattern.compile(VOWEL_CONSONANT_REGEX);
        Matcher matcher = patternVowelConsonant.matcher(word);
        int regexMatchOccurrence = 0;
        while (matcher.find()) {
            if (++regexMatchOccurrence > occurrenceMaxLimit) {
                return word.replaceAll("(" + wordMorphology + ")\\b", "ize");
            }
        }
        return word;
    }

    private String step2WordEndBILITI(final String word) {
        final String wordMorphology = "biliti";
        final Integer occurrenceMaxLimit = 2;
        Pattern patternVowelConsonant = Pattern.compile(VOWEL_CONSONANT_REGEX);
        Matcher matcher = patternVowelConsonant.matcher(word);
        int regexMatchOccurrence = 0;
        while (matcher.find()) {
            if (++regexMatchOccurrence > occurrenceMaxLimit) {
                return word.replaceAll("(" + wordMorphology + ")\\b", "ble");
            }
        }
        return word;
    }
}

package data.raw.process.stem.porter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 class PorterRuleStep1 {
    private static final String VOWEL = "aeiou";

    protected String stemmerProcess(final String rawWords) {
        String stemRawWords = rawWords;
        stemRawWords = step1A(stemRawWords);
        stemRawWords = step1B(stemRawWords);
        return stemRawWords;

    }

    /**
     * Step 1A (Word Ending)
     * matchedChar (mc)
     * replaceChar(rc)
     * mc-> rc
     * Replace character that matches the condition
     * 1. sses -> ss
     * 2. ies -> i
     * 3. ss -> ss
     * 4. if ss do nothing else remove last s
     *
     * @param rawWords
     * @return
     */
    private String step1A(final String rawWords) {
        String stemRawWords = rawWords;
        stemRawWords = stemRawWords.replaceAll("(sses)\\b", "ss");
        stemRawWords = stemRawWords.replaceAll("(ies)\\b", "i");
        stemRawWords = stemRawWords.replaceAll("(ss)\\b", "ss");
        stemRawWords = stemRawWords.replaceAll("(?<=^|[a-z&&[^s]])(s)\\b", "");
        return stemRawWords;
    }


    /**
     * Step B1 [Word Ending]
     * matchedChar (mc)
     * replaceChar(rc)
     * mc->rc
     * 1. if eed and m>1 -> ee
     * 2. if ed and vowel in between then StepB2
     * 3. if ing and vowel in between then StepB2
     *
     * @param rawWords
     * @return
     */
    private String step1B(final String rawWords) {

        final String wordEndEED = "eed";
        final String wordEndED = "ed";
        final String wordEndING = "ing";
        final String wordEndY = "y";

        List<String> splitRawWords = new ArrayList<>(Arrays.asList(rawWords.split(" ")));
        StringBuilder stemRawWords = new StringBuilder();
        splitRawWords.forEach(word -> {
            if (word.endsWith(wordEndEED)) {
                word = step1BWordEndEED(wordEndEED, word);

            } else if (word.endsWith(wordEndED)) {
                word = step1BWordEndED(wordEndED, word);
                word = step1BClean(word);

            } else if (word.endsWith(wordEndING)) {
                word = step1BWordEndING(wordEndING, word);
                word = step1BClean(word);
            } else if (word.endsWith(wordEndY)) {
                word = step1CWordEndY(word);
            }
            stemRawWords.append(word + " ");
        });
        return stemRawWords.toString();
    }

    /**
     * m > 1 ending with eed replace eed with ed
     * (vc) include eed, so m>1
     *
     * @param wordEndEED
     * @param word
     * @return
     */
    private String step1BWordEndEED(final String wordEndEED, final String word) {
        Pattern patternVowelConsonant = Pattern.compile("([" + VOWEL + "][a-z&&[^" + VOWEL + "]])");
        int regexMatchOccurrence = 0;
        Matcher matcher = patternVowelConsonant.matcher(word);
        while (matcher.find()) {
            if (++regexMatchOccurrence > 1) {
                return word.replaceAll("(" + wordEndEED + ")\\b", "ee");
            }
        }
        return word;
    }

    /**
     * (*V*) ending with ed -> remove ed
     *
     * @param wordEndED
     * @param word
     * @return
     */
    private String step1BWordEndED(final String wordEndED, final String word) {
        Pattern patternVowel = Pattern.compile("([" + VOWEL + "])");
        Matcher matcher = patternVowel.matcher(word);
        int regexMatchOccurrence = 0;
        while (matcher.find()) {
            if (++regexMatchOccurrence > 1) {
                return word.replaceAll("(" + wordEndED + ")\\b", "");
            }
        }
        return word;
    }

    /**
     * (*V*) ending with ed -> remove ed
     *
     * @param wordEndING
     * @param word
     * @return
     */
    private String step1BWordEndING(final String wordEndING, final String word) {
        Pattern patternVowel = Pattern.compile("([" + VOWEL + "])");
        Matcher matcher = patternVowel.matcher(word);
        int regexMatchOccurrence = 0;
        while (matcher.find()) {
            if (++regexMatchOccurrence > 1) {
                return word.replaceAll("(" + wordEndING + ")\\b", "");
            }
        }
        return word;
    }

    protected String step1BClean(final String word) {
        String stemRawWord = word;
        if (stemRawWord.endsWith("at")) {
            stemRawWord = step1BCleanWordEndAT(stemRawWord);
        } else if (stemRawWord.endsWith("bl")) {
            stemRawWord = step1BCleanWordEndBL(stemRawWord);
        } else {
            stemRawWord = step1BCleanWordEndDoubleConsonant(stemRawWord);
            stemRawWord = step1BCleanConsonantVowelConsonant(stemRawWord);
        }
        return stemRawWord;
    }

    private String step1BCleanWordEndAT(final String data) {
        return data.replaceAll("(at)\\b", "ate");
    }

    private String step1BCleanWordEndBL(final String data) {
        return data.replaceAll("(bl)\\b", "ble");
    }

    private String step1BCleanWordEndDoubleConsonant(final String data) {
        String doubleConsonantRegEx = "([a-z&&[^lsz]&&[^" + VOWEL + "]])\\1\\b";
        return data.replaceAll(doubleConsonantRegEx, "$1");
    }

    private String step1BCleanConsonantVowelConsonant(final String word) {
        Pattern patternVowelConsonant = Pattern.compile("([" + VOWEL + "][a-z&&[^" + VOWEL + "]])");
        Pattern patternVowelConsonantVowel = Pattern.compile("([a-z&&[^" + VOWEL + "]])([" + VOWEL + "])([a-z&&[^" + VOWEL + "wxy]])\\b");
        Matcher matcher = patternVowelConsonant.matcher(word);
        int regexMatchOccurrence = 0;
        while (matcher.find()) {
            if (++regexMatchOccurrence > 0) {
                Matcher matcherVowelConsonantVowel = patternVowelConsonantVowel.matcher(word);
                if (matcherVowelConsonantVowel.find()) {
                    return word + "e";
                }
            }
        }
        return word;
    }

    private String step1CWordEndY(final String word) {
        Pattern patternVowelConsonantVowel = Pattern.compile("([a-z&&[^" + VOWEL + "]])([" + VOWEL + "])([a-z&&[^" + VOWEL + "wxy]])\\b");
        Matcher matcher = patternVowelConsonantVowel.matcher(word);
        int regexMatchOccurrence = 0;
        while (matcher.find()) {
            if (++regexMatchOccurrence > 0) {
                return word.replaceAll("(y)\\b", "i");
            }
        }
        return word;
    }
}

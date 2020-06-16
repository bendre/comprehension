package unit.pair;

import org.junit.Test;

import java.util.*;

public class WordQuestionPairTest {


    @Test
    public void questionSentenceMatchedFull() {
        Map<String, List<Integer>> pair = new HashMap<>();
        List<Integer> w1L = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> w2L = new ArrayList<>(Arrays.asList(1, 2, 4, 5, 6));
        List<Integer> w3L = new ArrayList<>(Arrays.asList(8, 4, 5, 2));
        pair.put("word1", w1L);
        pair.put("word2", w2L);
        pair.put("word3", w3L);


        // int sentenceId = occurrenceInterpreter.questionOrAnswerSentencePair(pair);
        // System.out.println(sentenceId);
    }

    @Test
    public void questionSentenceMatchedPartial() {
        Map<String, List<Integer>> pair = new HashMap<>();
        List<Integer> w1L = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> w2L = new ArrayList<>(Arrays.asList(11, 2, 14, 87, 6));
        List<Integer> w3L = new ArrayList<>(Arrays.asList(8, 40, 11));
        pair.put("word1", w1L);
        pair.put("word2", w2L);
        pair.put("word3", w3L);


        // int sentenceId = occurrenceInterpreter.questionOrAnswerSentencePair(pair);
        // System.out.println(sentenceId);
    }
}

package data.comprehension.interpreter;

import data.model.*;
import data.util.MapComparator;
import model.AnswerCount;
import model.QuestionAnswerPair;
import model.SentenceCount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class ComprehensionInterpreter {
    private static Logger logger = LoggerFactory.getLogger(ComprehensionInterpreter.class);
    ParagraphQuestionAnswer paragraphQuestionAnswer;
    private Map<Integer, Integer> questionAnswerPair = new HashMap<>();
    private Map<Integer, Integer> answerSentenceConflict = new HashMap<>();
    private Map<Integer, String> stemmedAnswerIterate = new HashMap<>();

    public ComprehensionInterpreter(final ParagraphQuestionAnswer paragraphQuestionAnswersModel) {
        this.paragraphQuestionAnswer = paragraphQuestionAnswersModel;

        stemmedAnswerIterate = paragraphQuestionAnswer.getAnswerText().getTokenizedStemAnswers()
                .stream()
                .collect(Collectors.toMap(Answer::getAnswerId, Answer::getStemmedAnswer));

        logger.info("Comprehension interpreter is initialized");
    }

    /**
     * Algorithm to find the pair.
     * Inputs:
     * <b> StemSentence </b>
     * | sentenceId | "words, words" |
     * | sentenceId | "words, words" |
     * | sentenceId | "words, words" |
     * <b> StemQuestions </b>
     * | questionId | "words, words" |
     * | questionId | "words, words" |
     * | questionId | "words, words" |
     * <b> StemAnswers </b>
     * | questionId | "words, words" |
     * | questionId | "words, words" |
     * | questionId | "words, words" |
     * <p>
     * Processing
     * Step1: For each Question:
     * Step1.1: Question[Words] --> Intersection --> Sentence[Words]
     * Step1.1 : Match corresponding sentence  ---> Q1 : S3   [Step 1.1: Result]
     * Step 1.2: For each Answer with the above selected sentence (S3)
     * Step 1.2: Answer[Words] --> Intersection --> Sentence[Words]
     * Step 1.2: Max word matching paired [Step 1.2: Result]      Q1 -> S3 -> A4
     * Step 1.2.1: if above interaction is is max value (Mark it as conflict answer not decidable  -> mark all tentative answer)
     * <p>
     * Step 1.3: return pair
     */


    public List<QuestionAnswerPair> matchQuestionAnswer() {
        paragraphQuestionAnswer.getQuestionText().getTokenizedStemQuestions()
                .stream()
                .forEach(question -> {
                    List<String> questionWordToken = question.getTokenizedWords();
                    Map<String, HashSet<Integer>> wordOccurrencesWithSentences = paragraphQuestionAnswer.getParagraphText().wordOccurrencesWithSentences(questionWordToken);
                    Map<Integer, Integer> questionSentenceCounts = sentenceFrequencyCount(wordOccurrencesWithSentences);
                    SentenceCount questionSentenceMatchBestMatch = sentenceBestMatch(questionSentenceCounts);
                    Sentence bestMatchedSentence = paragraphQuestionAnswer.getParagraphText().getTokenizedStemSentences()
                            .stream()
                            .filter(sentenceId -> sentenceId.getSentenceId().equals(questionSentenceMatchBestMatch.getSentenceId()))
                            .findAny()
                            .orElse(null);
                    logger.info("Question and Sentence best match found");

                    Map<Integer, Integer> wordFrequencyOfAllAnswersWithSentence = stemmedAnswerIterate.entrySet()
                            .stream()
                            .map(answer -> {
                                Integer wordCount = bestMatchedSentence.wordOccurrencesWithSentences(new ArrayList<>(Arrays.asList(answer.getValue().split(" "))));
                                return new AnswerCount(answer.getKey(), wordCount);
                            })
                            .collect(Collectors.toMap(AnswerCount::getAnswerId, AnswerCount::getCount));

                    Map<Integer, Integer> beatMatch = MapComparator.getMaxValue(wordFrequencyOfAllAnswersWithSentence);
                    updateQuestionAnswerOrHandleConflict(question.getQuestionId(), new ArrayList<>(beatMatch.keySet()));
                });
        return questionAnswerMapping();
    }

    private void updateQuestionAnswerOrHandleConflict(final Integer questionId, final List<Integer> answerIds) {
        if (answerIds.size() == 1) {
            int answerId = answerIds.get(0);
            pairQuestionAnswer(answerId, questionId);
            canConflictedQuestionBeResolved(answerId);
        } else {
            addAnswerToConflict(answerIds, questionId);
        }
    }

    private void pairQuestionAnswer(final Integer answerId, final Integer questionId) {
        logger.info("Question Answer match found");
        questionAnswerPair.put(questionId, answerId);
        stemmedAnswerIterate.remove(answerId);
    }

    private void addAnswerToConflict(final List<Integer> answerIds, final Integer questionId) {
        logger.info("Multiple answer possibility for given question");
        logger.info("Will be parked for more better match");
        answerIds.stream()
                .forEach(answerId -> answerSentenceConflict.put(answerId, questionId));
    }

    private void canConflictedQuestionBeResolved(final Integer answerId) {
        logger.info("One of the conflicted Answer is resolved, check if remaining can be settled");
        Integer conflictQuestion = -1;
        if (answerSentenceConflict.get(answerId) != null) {
            conflictQuestion = answerSentenceConflict.get(answerId);
            answerSentenceConflict.remove(answerId);
        }
        Integer conflictQuestionCopy = conflictQuestion;
        Map<Integer, Integer> unConflictedAnswer = answerSentenceConflict.entrySet().stream()
                .filter(conflict -> conflict.getValue().equals(conflictQuestionCopy))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (unConflictedAnswer.size() == 1) {
            unConflictedAnswer.entrySet().stream().forEach(unConflicted -> {
                questionAnswerPair.put(unConflicted.getValue(), unConflicted.getKey());
                stemmedAnswerIterate.remove(unConflicted.getKey());
            });
        }
    }

    /**
     * Find the frequency of the sentences.
     * Input structure:
     * |Word1| [sentenceId1, sentenceId2, sentenceId3]
     * |Word2| [sentenceId1, sentenceId3, sentenceId4]
     * <p>
     * Output:
     * |sentenceId1| 2|
     * |sentenceId2| 1|
     *
     * @param wordOccurrences
     * @return
     */
    public Map<Integer, Integer> sentenceFrequencyCount(final Map<String, HashSet<Integer>> wordOccurrences) {
        Map<Integer, Integer> sentenceCounts = new HashMap<>();
        wordOccurrences.entrySet().forEach(word ->
                word.getValue()
                        .forEach(sentencesId -> {
                            Integer sentenceCount = sentenceCounts.get(sentencesId) == null ? 0 : sentenceCounts.get(sentencesId);
                            sentenceCounts.put(sentencesId, sentenceCount + 1);
                        })
        );
        return sentenceCounts;
    }

    /**
     * Find the max frequency of Key.
     * Input:
     * |sentenceId1 | 4 |
     * |sentenceId1 | 2 |
     * |sentenceId1 | 6 |
     * <p>
     * Output:
     * SentenceId1 -> 6
     *
     * @param sentenceCounts
     * @return
     */
    private SentenceCount sentenceBestMatch(final Map<Integer, Integer> sentenceCounts) {
        SentenceCount sentenceCount = new SentenceCount();
        sentenceCount.setCount(-1);
        sentenceCount.setSentenceId(-1);
        try {
            sentenceCounts.entrySet()
                    .stream()
                    .max((Map.Entry<Integer, Integer> e1, Map.Entry<Integer, Integer> e2) -> e1.getValue()
                            .compareTo(e2.getValue())
                    ).ifPresent(max -> {
                sentenceCount.setSentenceId(max.getKey());
                sentenceCount.setCount(max.getValue());
            });
            return sentenceCount;
        } catch (Exception e) {
            throw new IllegalStateException("" + e.getLocalizedMessage());
        }
    }

    /**
     * Output
     * [Q1:A3]
     * [Q2: A1]
     * [Q3: A4]
     *
     * @return
     */

    private List<QuestionAnswerPair> questionAnswerMapping() {
        try {
            return paragraphQuestionAnswer.getQuestionText().getTokenizedStemQuestions()
                    .stream()
                    .map(question -> {
                        Answer answer = paragraphQuestionAnswer.getAnswerText().getTokenizedStemAnswers()
                                .stream()
                                .filter(answerId -> questionAnswerPair.get(question.getQuestionId()).equals(answerId.getAnswerId()))
                                .findAny()
                                .orElse(new Answer());
                        return new QuestionAnswerPair(question.getOriginalQuestion().trim(), answer.getOriginalAnswer().trim());
                    })
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalStateException("Exception occurred while mapping questionAnswer" + e.getLocalizedMessage());
        }
    }
}

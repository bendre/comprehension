package data.raw.process.input;

import data.model.AnswerText;
import data.model.IText;
import data.model.QuestionText;
import data.model.SentenceText;
import data.raw.process.interpreter.RawDataRequestInterpreter;
import model.QuestionAnswerPair;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractAnchorRawDataSet {

    /**
     * This will forward the rawData set request to the next module interpreter handler.
     *
     * @return List<QuestionAnswerPair>
     */

    protected List<QuestionAnswerPair> rawDataInterpreterHandler() {
        RawDataRequestInterpreter interpreterRequestHandler = new RawDataRequestInterpreter();
        List<IText> sentenceQuestionAnswers = new ArrayList<>();
        sentenceQuestionAnswers.add(new SentenceText());
        sentenceQuestionAnswers.add(new QuestionText());
        sentenceQuestionAnswers.add(new AnswerText());
        return interpreterRequestHandler.rawDataRequestInterpreter(sentenceQuestionAnswers);
    }

    /**
     * Validate input raw data set to respective charset.
     *
     * @param rawText
     * @param questionTextSet
     * @param answerTextSet
     * @param charset
     * @return is valid or not
     * @throws IllegalArgumentException
     * @implSpec Default charset: US_ASCII
     */


}

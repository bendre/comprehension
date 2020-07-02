package data.raw.process.input;

import data.model.*;
import data.raw.process.interpreter.RawDataRequestInterpreter;
import model.QuestionAnswerPair;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

abstract class AbstractAnchorRawDataSet {

    /**
     * This will forward the rawData set request to the next module interpreter handler.
     *
     * @return List<QuestionAnswerPair>
     */

    protected List<QuestionAnswerPair> rawDataInterpreterHandler(String paragraphText, String questionText, String answerText, String charSet) {
        RawDataRequestInterpreter interpreterRequestHandler = new RawDataRequestInterpreter();

        ParagraphQuestionAnswer paragraphQuestionAnswer = new ParagraphQuestionAnswer()
                .setParagraphText(new ParagraphText(charSet, paragraphText))
                .setQuestionText(new QuestionText(charSet, questionText))
                .setAnswerText(new AnswerText(charSet, answerText));

        return interpreterRequestHandler.rawDataRequestInterpreter(paragraphQuestionAnswer);
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

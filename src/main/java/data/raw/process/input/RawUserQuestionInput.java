package data.raw.process.input;


import data.configuration.ReadConfiguration;
import data.util.FileOperation;
import model.QuestionAnswerPair;

import java.util.List;
import java.util.stream.Collectors;

public class RawUserQuestionInput extends AbstractAnchorRawDataSet {

    public void processRawData(String userQuestion) {


        ReadConfiguration readConfiguration = new ReadConfiguration();
        String charSet = readConfiguration.getSentenceQuestionAnswerFileNameProperty().getCharset();
        String paragraphText = FileOperation.readFile(readConfiguration.getSentenceQuestionAnswerFileNameProperty().getParagraphFileName());
        String questionText = userQuestion;
        String answerText = FileOperation.readFile(readConfiguration.getSentenceQuestionAnswerFileNameProperty().getAnswerFileName());

        List<QuestionAnswerPair> questionAnswer = rawDataInterpreterHandler(paragraphText, questionText, answerText, charSet);
        List<String> answers = questionAnswer.stream().map(QuestionAnswerPair::getAnswer).collect(Collectors.toList());

        System.out.println(answers);
        //FileOperation.writeAnswerToFile(answers);
    }

}

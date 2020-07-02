package data.raw.process.input;

import data.configuration.ReadConfiguration;
import data.util.FileOperation;
import model.QuestionAnswerPair;

import java.util.List;
import java.util.stream.Collectors;

public class RawDataSetFileImplementer extends AbstractAnchorRawDataSet {

    /**
     * This function is exposed to consume the application.
     * This will Accept request in the form of files and convert to accpeted format for intercepter
     * i.e File -> String
     *
     * @implSpec File should be place in the project root folder i.e POM location
     */
    public void processRawData() {
        ReadConfiguration readConfiguration = new ReadConfiguration();
        String charSet = readConfiguration.getSentenceQuestionAnswerFileNameProperty().getCharset();
        String paragraphText = FileOperation.readFile(readConfiguration.getSentenceQuestionAnswerFileNameProperty().getParagraphFileName());
        String questionText = FileOperation.readFile(readConfiguration.getSentenceQuestionAnswerFileNameProperty().getQuestionFileName());
        String answerText = FileOperation.readFile(readConfiguration.getSentenceQuestionAnswerFileNameProperty().getAnswerFileName());


        List<QuestionAnswerPair> questionAnswerPairs = rawDataInterpreterHandler(paragraphText, questionText, answerText, charSet);
        // As the the problem statement
        List<String> answers = questionAnswerPairs.stream().map(QuestionAnswerPair::getAnswer).collect(Collectors.toList());
        FileOperation.writeAnswerToFile(answers);
    }
}

package data.configuration;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

@Data
public class ReadConfiguration {
    private static Logger logger = LoggerFactory.getLogger(ReadConfiguration.class);
    private static SentenceQuestionAnswerFileNameProperty sentenceQuestionAnswerFileNameProperty;

    public SentenceQuestionAnswerFileNameProperty getSentenceQuestionAnswerFileNameProperty() {
        if (sentenceQuestionAnswerFileNameProperty == null) {
            sentenceQuestionAnswerFileNameProperty = getSentenceQuestionAnswerFileName();
        }
        return sentenceQuestionAnswerFileNameProperty;

    }

    /**
     * Read configuration file name 'configuration.yml' in resources folder.
     *
     * @return
     */
    public SentenceQuestionAnswerFileNameProperty getSentenceQuestionAnswerFileName() {
        SentenceQuestionAnswerFileNameProperty textFileNameProperty = new SentenceQuestionAnswerFileNameProperty();
        Yaml yaml = new Yaml(new Constructor(SentenceQuestionAnswerFileNameProperty.class));
        try {
            InputStream inputStream = this.getClass()
                    .getClassLoader()
                    .getResourceAsStream("configuration.yml");
            textFileNameProperty = yaml.load(inputStream);
            logger.info("configuration file read: {}", textFileNameProperty);
            logger.info(textFileNameProperty.getQuestionFileName());
        } catch (Exception e) {
            throw new IllegalArgumentException("configuration file cannot be read");
        }
        return textFileNameProperty;
    }

}

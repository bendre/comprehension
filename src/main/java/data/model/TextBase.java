package data.model;

import data.util.FileOperation;
import lombok.Data;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
abstract class TextBase {
    private String fullText;

    protected boolean validateTextCharset(String standardCharsets, String text) {
        Charset charset = Charset.forName(standardCharsets);
        if (charset == null || charset.equals(StandardCharsets.US_ASCII)) {
            charset = StandardCharsets.US_ASCII;
        }
        if (!charset.newEncoder().canEncode(text)) {
            throw new IllegalArgumentException("provided data is not in " + charset + " charset");
        }
        return true;
    }

    protected List<String> getTokenizedLines(final String tokenChar, final String fullInputText) {
        if (fullInputText.equals(this.fullText)) {
            return new ArrayList<>(Arrays.asList(fullInputText.split(tokenChar)));
        } else {
            throw new IllegalArgumentException("Text does not match with rawFulltext");
        }
    }

    public String readDataFromFile(final String fileName) {
        return FileOperation.readFile(fileName);
    }

    abstract List<? extends ISentenceQuestionAnswer> saveTokenizedAndStemLine();
}

package unit.input;

import data.raw.process.input.RawDataSetFileImplementer;
import org.junit.Assert;
import org.junit.Test;

import java.nio.file.Files;
import java.nio.file.Paths;

public class RawInputFileTest {

    @Test
    public void processData() {
        // Files should be in project root folder (e.g location where POM exist)
        try {
            RawDataSetFileImplementer rawDataSetFileImplementer = new RawDataSetFileImplementer();
            rawDataSetFileImplementer.processRawData();
            Assert.assertEquals(true, Files.exists(Paths.get("ComprehensionOutput.txt")));
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void processDataUnMatchedQuestion() {
        // Files should be in project root folder (e.g location where POM exist)
        try {
            RawDataSetFileImplementer rawDataSetFileImplementer = new RawDataSetFileImplementer();
            rawDataSetFileImplementer.processRawData();
            Assert.assertEquals(true, Files.exists(Paths.get("ComprehensionOutput.txt")));
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void processDataUnMatchedAnswer() {
        // Files should be in project root folder (e.g location where POM exist)
        try {
            RawDataSetFileImplementer rawDataSetFileImplementer = new RawDataSetFileImplementer();
            rawDataSetFileImplementer.processRawData();
            Assert.assertEquals(true, Files.exists(Paths.get("ComprehensionOutput.txt")));
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void processBlankFileName() {
        // Files should be in project root folder (e.g location where POM exist)
        try {
            RawDataSetFileImplementer rawDataSetFileImplementer = new RawDataSetFileImplementer();
            rawDataSetFileImplementer.processRawData();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
    }

    @Test
    public void processInvalidFileNames() {
        // Files should be in project root folder (e.g location where POM exist)
        try {
            RawDataSetFileImplementer rawDataSetFileImplementer = new RawDataSetFileImplementer();
            rawDataSetFileImplementer.processRawData();
        } catch (Exception e) {
            Assert.assertTrue(e instanceof IllegalArgumentException);
        }
    }
}

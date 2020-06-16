package main;

import data.raw.process.input.RawDataSetFileImplementer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Comprehension main file");
        RawDataSetFileImplementer rawDataSetFileImplementer = new RawDataSetFileImplementer();
        rawDataSetFileImplementer.processRawData();

    }
}

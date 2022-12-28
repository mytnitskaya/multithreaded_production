package ru.cft.shift.helper;

import ru.cft.shift.ProductionParameters;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class InputReader {
    private static final String producerCountKey = "producerCount";
    private static final String producerTimeKey = "producerTime";
    private static final String consumerCountKey = "consumerCount";
    private static final String consumerTimeKey = "consumerTime";
    private static final String storageSizeKey = "storageSize";

    public static ProductionParameters getParametersFromProperties(){
        Properties properties = getProperties();
        ProductionParameters parameters = new ProductionParameters();
        parameters.producerCount = Integer.parseInt(properties.getProperty(producerCountKey));
        parameters.consumerCount = Integer.parseInt(properties.getProperty(consumerCountKey));
        parameters.producerTime = Integer.parseInt(properties.getProperty(producerTimeKey));
        parameters.consumerTime = Integer.parseInt(properties.getProperty(consumerTimeKey));
        parameters.storageSize = Integer.parseInt(properties.getProperty(storageSizeKey));
        return parameters;
    }

    private static Properties getProperties(){
        Properties properties;
        try (InputStream input = new FileInputStream("src/main/resources/prop.properties")) {
            properties = new Properties();
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}

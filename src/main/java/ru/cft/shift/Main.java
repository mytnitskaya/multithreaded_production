package ru.cft.shift;

import ru.cft.shift.helper.InputReader;

public class Main {
    public static void main(String[] args) {
        ProductionParameters parameters = InputReader.getParametersFromProperties();
        new MultithreadedProduction().startProduction(parameters);
    }
}

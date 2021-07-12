package edu.school21.logic;

public class PreProcessorToLowerImpl implements PreProcessor {

    @Override
    public String preprocess(String message) {
        return message.toLowerCase();
    }
}

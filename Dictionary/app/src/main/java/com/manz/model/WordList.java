package com.manz.model;

import java.util.List;

public class WordList {

    private String version;
    private List<WordModel> words;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<WordModel> getWords() {
        return words;
    }

    public void setWords(List<WordModel> words) {
        this.words = words;
    }
}

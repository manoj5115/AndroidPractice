package com.manz.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.manz.db.Words;

public class WordModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("word")
    @Expose
    private String word;
    @SerializedName("variant")
    @Expose
    private Integer variant;
    @SerializedName("meaning")
    @Expose
    private String meaning;
    @SerializedName("ratio")
    @Expose
    private Double ratio;

    private String url;

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word The word
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * @return The variant
     */
    public Integer getVariant() {
        return variant;
    }

    /**
     * @param variant The variant
     */
    public void setVariant(Integer variant) {
        this.variant = variant;
    }

    /**
     * @return The meaning
     */
    public String getMeaning() {
        return meaning;
    }

    /**
     * @param meaning The meaning
     */
    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    /**
     * @return The ratio
     */
    public Double getRatio() {
        return ratio;
    }

    /**
     * @param ratio The ratio
     */
    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public String getUrl() {
        return (ratio > -1) ? "http://appsculture.com/vocab/images/" + getId() + ".png" : null;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Words cloneDao() {
        return new Words(Long.valueOf(id), word, variant, meaning, ratio);
    }
}

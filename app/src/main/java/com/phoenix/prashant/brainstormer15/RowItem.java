package com.phoenix.prashant.brainstormer15;

public class RowItem {
    private int imageId;
    private String  title;
    private String optA;
    private String optB;
    private String optC;
    private String optD;
    private String correct;
    private String selected;


    public RowItem( String []title) {
        this.imageId = imageId;
        this.title = title[0];
        this.optA=title[1];
        this.optB=title[2];
        this.optC=title[3];
        this.optD=title[4];
        this.correct=title[5];


    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getOptB() {
        return optB;
    }

    public String getOptA() {
        return optA;
    }

    public String getOptC() {
        return optC;
    }

    public String getOptD() {
        return optD;
    }

    public void setOptA(String optA) {
        this.optA = optA;
    }

    public void setOptB(String optB) {
        this.optB = optB;
    }

    public void setOptC(String optC) {
        this.optC = optC;
    }

    public void setOptD(String optD) {
        this.optD = optD;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

}
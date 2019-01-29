package com.predictorsd.geodesycalculator;

class Actions{
    private String name;
    private String lstring;
    private int imageID;
    private int noticeID;
    Actions(String n, int i,String l){
        name=n;
        imageID=i;
        lstring=l;
    }
    String getName(){
        return name;
    }

    int getImageID() {
        return imageID;
    }

    int getNoticeID() {
        return noticeID;
    }

    String getLstring(){ return lstring; }
}
package org.zs.phm3.models;


public enum FileType {

    IMAGE("IMAGE"), DOC("DOC"), ICO("ICO");

    private String title;

    FileType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "FileType{" +
                "title='" + title + '\'' +
                '}';
    }
}


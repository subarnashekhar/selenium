package edu.techsmart.selenium.pages.enums;

public enum Tab {
    ALL("All"), COURSES("Courses"), BLOG("Blog"), RESOURCES("Resources"), AUTHORS("Authors");


    private String value;

    Tab(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return value;
    }
}

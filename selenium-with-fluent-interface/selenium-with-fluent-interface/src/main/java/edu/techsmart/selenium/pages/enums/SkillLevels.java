package edu.techsmart.selenium.pages.enums;

public enum SkillLevels {

    BEGINNER("Beginner"), ADVANCED("Advanced") , INTERMEDIATE("Intermediate");

    private String value;

    SkillLevels(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}

package org.ibs.fazlyakhmetov.tests.utils;

public class Food {
    private String FruitName;
    private String FruitType;
    private Integer  ExoticType;

    public String getFruitName() {
        return FruitName;
    }

    public void setFruitName(String fruitName) {
        FruitName = fruitName;
    }

    public String getFruitType() {
        return FruitType;
    }

    public void setFruitType(String fruitType) {
        FruitType = fruitType;
    }

    public Integer getExoticType() {
        return ExoticType;
    }

    public void setExoticType(Integer exoticType) {
        ExoticType = exoticType;
    }

    public Food(String fruitName, String fruitType, Integer exoticType) {
        FruitName = fruitName;
        FruitType = fruitType;
        ExoticType = exoticType;




    }
}

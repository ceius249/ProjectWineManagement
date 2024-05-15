package com.example.projectwinemanagement.model;

public class Wine {
    int id;
    String name;
    int alcoholContent;
    int age;
    OriginalCountry originalCountry;
    int originalCountryId;
    String imageViewPath;

    public Wine(String name, int alcoholContent, int age, OriginalCountry originalCountry, String imageViewPath) {
        this.name = name;
        this.alcoholContent = alcoholContent;
        this.age = age;
        this.originalCountry = originalCountry;
        this.imageViewPath = imageViewPath;
    }

    public Wine(int id, String name, int alcoholContent, int age, OriginalCountry originalCountry, String imageViewPath) {
        this.id = id;
        this.name = name;
        this.alcoholContent = alcoholContent;
        this.age = age;
        this.originalCountry = originalCountry;
        this.imageViewPath = imageViewPath;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(int alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public OriginalCountry getOriginalCountry() {
        return originalCountry;
    }

    public void setOriginalCountry(OriginalCountry originalCountry) {
        this.originalCountry = originalCountry;
    }

    public int getOriginalCountryId() {
        return originalCountryId;
    }

    public void setOriginalCountryId(int originalCountryId) {
        this.originalCountryId = originalCountryId;
    }

    public String getImageViewPath() {
        return imageViewPath;
    }

    public void setImageViewPath(String imageViewPath) {
        this.imageViewPath = imageViewPath;
    }

    @Override
    public String toString() {
        return "Wine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", alcoholContent=" + alcoholContent +
                ", age=" + age +
                ", originalCountry=" + originalCountry +
                ", originalCountryId=" + originalCountryId +
                ", imageViewPath='" + imageViewPath + '\'' +
                '}';
    }
}

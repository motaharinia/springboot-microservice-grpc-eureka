package com.motaharinia.msutility.object.sample;


public class ClassA extends ClassFatherA {

    private String name;
    private String family;
    private String fatherName;
    private String test;
    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "ClassA{" + "name=" + name + ", family=" + family + ", fatherName=" + fatherName + ", test=" + test + ", mobile=" + mobile + '}';
    }

    
}

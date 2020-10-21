package com.motaharinia.msutility.object.sample;


public class ClassB extends ClassFatherB {

    private String name;
    private String user_fatherName;
    private String user_test;
    private String defaultContact_mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_fatherName() {
        return user_fatherName;
    }

    public void setUser_fatherName(String user_fatherName) {
        this.user_fatherName = user_fatherName;
    }

    public String getUser_test() {
        return user_test;
    }

    public void setUser_test(String user_test) {
        this.user_test = user_test;
    }

    public String getDefaultContact_mobile() {
        return defaultContact_mobile;
    }

    public void setDefaultContact_mobile(String defaultContact_mobile) {
        this.defaultContact_mobile = defaultContact_mobile;
    }

    @Override
    public String toString() {
        return "ClassB{" + "name=" + name + ", user_fatherName=" + user_fatherName + ", user_test=" + user_test + ", defaultContact_mobile=" + defaultContact_mobile + '}';
    }

}

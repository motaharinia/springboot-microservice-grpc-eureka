package com.motaharinia.msutility.captcha;

import java.awt.*;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-09-28<br>
 * Time: 15:50:45<br>
 * Description:<br>
 */
public class CaptchaConfigModel {

    /**
     * عرض تصویر مورد نظر جهت تولید
     */
    private Integer width = 450;
    /**
     * طول تصویر مورد نظر جهت تولید
     */
    private Integer height = 100;


    /**
     * آیا تصویر کپچا تولیدی خط تصادفی هم داشته باشد؟
     */
    private Boolean linesIncluded = false;
    /**
     * تعداد خطهای تصادفی جهت تولید
     */
    private Integer linesNumber = 20;
    /**
     * رنگ خطهایی که تصادفی تولید میشوند
     */
    private Color linesColor = new Color(86, 86, 86);


    /**
     * آیا تصویر کپچا تولیدی نقطه تصادفی هم داشته باشد؟
     */
    private Boolean dotsIncluded = false;
    /**
     * تعداد نقطه های تصادفی جهت تولید
     */
    private Integer dotsNumber = 1000;
    /**
     * رنگ نقطه هایی که تصادفی تولید میشوند
     */
    private Color dotsColor = new Color(0, 0, 0);


    /**
     * آیا حروف کپچا از چند فونت ایجاد شوند؟
     */
    private Boolean fontVarious = false;
    /**
     * ایندکس فایل فونت پیش فرض در ریسورس
     */
    private Integer fontFileDefaultIndex = 12;
    /**
     * تعداد فایلهای فونت موجود در ریسورس
     */
    private Integer fontCount = 13;
    /**
     * اندازه پیکسلی عرض فونت برای جا شدن تعداد حروف در تصویر
     */
    private Integer fontSize = (width / 6) - 10;
    /**
     * رنگ متن پیش فرض حروفی که تصادفی تولید میشوند
     */
    private Color fontDefaultColor = new Color(0, 0, 0);
    /**
     * آیا حروف کپچا رنگی باشند؟
     */
    private Boolean fontColorful = false;
    /**
     * اگرحروف کپچا قرار هست رنگی باشند از چه رنگهایی ایجاد شوند
     */
    private Color[] fontColorfulArray = new Color[]{
            new Color(0, 0, 0), //black
            new Color(64, 64, 64), //gray
            new Color(0, 0, 255), //blue
            new Color(255, 0, 0), //red
            new Color(0, 255, 0), //green
            new Color(51, 0, 0), //brown
            new Color(255, 128, 0), //orange
            new Color(102, 0, 102), //purple
    };


    /**
     * رنگ تصویر پس زمینه
     */
    private Color backgroundColor = new Color(250, 250, 250);
    /**
     * آیا تصویر پیش زمینه متغیر باشد؟
     */
    private Boolean backgroundImageVarious = true;
    /**
     * ایندکس فایل تصویر پس زمینه پیش فرض در ریسورس
     */
    private Integer backgroundImageFileDefaultIndex = 9;
    /**
     * تعداد فایلهای تصویر پس زمینه موجود در ریسورس
     */
    private Integer backgroundImageCount = 26;


    //getter-setter:
    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Boolean getLinesIncluded() {
        return linesIncluded;
    }

    public void setLinesIncluded(Boolean linesIncluded) {
        this.linesIncluded = linesIncluded;
    }

    public Integer getLinesNumber() {
        return linesNumber;
    }

    public void setLinesNumber(Integer linesNumber) {
        this.linesNumber = linesNumber;
    }

    public Color getLinesColor() {
        return linesColor;
    }

    public void setLinesColor(Color linesColor) {
        this.linesColor = linesColor;
    }

    public Boolean getDotsIncluded() {
        return dotsIncluded;
    }

    public void setDotsIncluded(Boolean dotsIncluded) {
        this.dotsIncluded = dotsIncluded;
    }

    public Integer getDotsNumber() {
        return dotsNumber;
    }

    public void setDotsNumber(Integer dotsNumber) {
        this.dotsNumber = dotsNumber;
    }

    public Color getDotsColor() {
        return dotsColor;
    }

    public void setDotsColor(Color dotsColor) {
        this.dotsColor = dotsColor;
    }

    public Boolean getFontVarious() {
        return fontVarious;
    }

    public void setFontVarious(Boolean fontVarious) {
        this.fontVarious = fontVarious;
    }

    public Integer getFontFileDefaultIndex() {
        return fontFileDefaultIndex;
    }

    public void setFontFileDefaultIndex(Integer fontFileDefaultIndex) {
        this.fontFileDefaultIndex = fontFileDefaultIndex;
    }

    public Integer getFontCount() {
        return fontCount;
    }

    public void setFontCount(Integer fontCount) {
        this.fontCount = fontCount;
    }

    public Integer getFontSize() {
        return fontSize;
    }

    public void setFontSize(Integer fontSize) {
        this.fontSize = fontSize;
    }

    public Color getFontDefaultColor() {
        return fontDefaultColor;
    }

    public void setFontDefaultColor(Color fontDefaultColor) {
        this.fontDefaultColor = fontDefaultColor;
    }

    public Boolean getFontColorful() {
        return fontColorful;
    }

    public void setFontColorful(Boolean fontColorful) {
        this.fontColorful = fontColorful;
    }

    public Color[] getFontColorfulArray() {
        return fontColorfulArray;
    }

    public void setFontColorfulArray(Color[] fontColorfulArray) {
        this.fontColorfulArray = fontColorfulArray;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Boolean getBackgroundImageVarious() {
        return backgroundImageVarious;
    }

    public void setBackgroundImageVarious(Boolean backgroundImageVarious) {
        this.backgroundImageVarious = backgroundImageVarious;
    }

    public Integer getBackgroundImageFileDefaultIndex() {
        return backgroundImageFileDefaultIndex;
    }

    public void setBackgroundImageFileDefaultIndex(Integer backgroundImageFileDefaultIndex) {
        this.backgroundImageFileDefaultIndex = backgroundImageFileDefaultIndex;
    }

    public Integer getBackgroundImageCount() {
        return backgroundImageCount;
    }

    public void setBackgroundImageCount(Integer backgroundImageCount) {
        this.backgroundImageCount = backgroundImageCount;
    }
}

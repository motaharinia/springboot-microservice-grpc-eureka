package com.motaharinia.msutility.string;

import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.customexception.UtilityExceptionKeyEnum;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.springframework.util.ObjectUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: https://github.com/motaharinia
 * Date: 2020-06-16
 * Time: 23:14:13
 * Description: اینترفیس پدر تمامی اینترفیسهای جستجوی پیشرفته دیتابیس که حکم میکند تمام آنها باید متد گتر آی دی را داشته باشند
 * تمام اینترفیسهای جستجوی پیشرفته دیتابیس باید از این اینترفیس گسترش یابند
 */
public interface StringTools {


    /**
     * این متد رشته و طول مورد نظر را از ورودی دریافت میکند و رشته هش شده بر مبنای الگوی رمزگذاری ام دی 5 با حداکثر طول درخواستی را تولید میکند
     *
     * @param input        رشته ورودی جهت رمزنگاری
     * @param resultLength حداکثر طول رشته هش شده خروجی
     * @return خروجی: رشته هش شده بر مبنای الگوی رمزگذاری ام دی 5 با حداکثر طول درخواستی
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
*/
    @NotNull
    static String generateMD5Hash(@NotNull String input, @NotNull Integer resultLength) throws Exception {
        if (ObjectUtils.isEmpty(input)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "input");
        }
        if (ObjectUtils.isEmpty(resultLength)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "resultLength");
        }
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] md5sum = md.digest(input.getBytes());
        String output = String.format("%032x", new BigInteger(1, md5sum));
        if (resultLength <= input.length()) {
            return output.substring(0, resultLength);
        } else {
            return output;
        }
    }

    /**
     * این متد رشته ای را از ورودی دریافت میکند و تمام تگهای اچ تی ام ال آن را حذف میکند
     *
     * @param htmlString رشته ورودی
     * @return خروجی: رشته بدون تگهای اچ تی ام ال
     */
    @NotNull
    static String removeHtml(@NotNull String htmlString) throws UtilityException {
        if (ObjectUtils.isEmpty(htmlString)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "htmlString");
        }
        return Jsoup.parse(htmlString).text();
    }

    /**
     * این متد رشته ورودی و طول مورد نظر رشته خروجی را دریافت میکند و رشته مورد نظر را به اندازه طول درخواستی کوتاه میکند
     * اگر طول رشته ورودی کمتر یا مساوی طول درخواستی باشد تغییری در رشته داده نمیشود
     * ولی در غیر این صورت رشته ورودی کوتاه شده و در انتهای آن سه نقطه قرار میگیرد
     *
     * @param input      رشته ورودی
     * @param charNumber طول مورد نظر
     * @return خروجی: رشته اصلاح شده طبق طول مورد نظر
     */
    @NotNull
    static String summarizeString(@NotNull String input, @NotNull Integer charNumber) throws UtilityException {
        if (ObjectUtils.isEmpty(input)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "input");
        }
        if (ObjectUtils.isEmpty(charNumber)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "charNumber");
        }
        String result;
        if (input.length() < charNumber) {
            result = input;
        } else {
            if (charNumber > 3) {
                result = input.substring(0, charNumber - 3) + "...";
            } else {
                result = input.substring(0, charNumber) + "...";
            }
        }
        return result;
    }

    /**
     * این متد رشته ورودی و عبارت مورد نظر را از ورودی میگیرد و آن عبارت در رشته را هایلایت مینماید
     *
     * @param inputText رشته ورودی
     * @param search    عبارت مورد نظر جهت هایلایت
     * @return خروجی: رشته با عبارات هایلایت شده
     */
    @NotNull
    static String highlight(@NotNull String inputText, @NotNull String search) throws UtilityException {
        if (ObjectUtils.isEmpty(inputText)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "inputText");
        }
        if (ObjectUtils.isEmpty(search)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "search");
        }
        return inputText.replaceAll(search, "<span class='highlight'>" + search + "</span>");
    }

    /**
     * این متد رشته ای تصادفی را با توجه به نوع ، طول و اینکه اعداد با پیش صفر باشند یا خیر را تولید میکند
     *
     * @param randomGenerationTypeEnum نوع ترکیب رشته تصادفی
     * @param length                   طول رشته مورد نظر
     * @param withLeadingZero          با صفر شروع شود؟
     * @return خروجی: رشته تصادفی
     */
    @NotNull
    static String generateRandomString(@NotNull RandomGenerationTypeEnum randomGenerationTypeEnum, @NotNull Integer length, @NotNull Boolean withLeadingZero) throws UtilityException {
        if (ObjectUtils.isEmpty(randomGenerationTypeEnum)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "randomGenerationTypeEnum");
        }
        if (ObjectUtils.isEmpty(length)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "length");
        }
        if (ObjectUtils.isEmpty(withLeadingZero)) {
            throw new UtilityException(StringTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "withLeadingZero");
        }
        String characterLower = "abcdefghigklmnopqrstuvwxyz";
        String characterUpper = "ABCDEFGHIJKLMNPQRSTUVWXYZ";
        String number = "1234567890";
        String punc = "_-$#@%^*&=+";
        String characters = "";

        switch (randomGenerationTypeEnum) {
            case CHARACTER_ALL:
                characters = characterLower + characterUpper;
                break;
            case CHARACTER_LOWER:
                characters = characterLower;
                break;
            case CHARACTER_UPPER:
                characters = characterUpper;
                break;
            case NUMBER:
                characters = number;
                break;
            case CHARACTER_NUMBER:
                characters = characterLower + characterUpper + number;
                break;
            case PUNCTUATION:
                characters = punc;
                break;
            case CHARACTER_NUMBER_PUNCTUATION:
                characters = characterLower + characterUpper + number + punc;
                break;
            case CHARACTER_NUMBER_UNDERLINE:
                characters = characterLower + characterUpper + number + "_";
                break;
            case NUMBER_UNDERLINE:
                characters = number + "_";
                break;
        }
        String result = "";
        Random rand = new Random();
        Integer charactersLength = characters.length();
        Integer randomNum = 0;
        Character randomChar;
        for (int i = 0; i < length; i++) {
            randomNum = rand.nextInt(charactersLength);
            randomChar = characters.charAt(randomNum);
            if ((i == 0) && (withLeadingZero == false)) {
                while (((int) randomChar == 48)) {
                    randomNum = rand.nextInt((charactersLength - 1));
                    randomChar = characters.charAt(randomNum);
                }
            }
            result += randomChar;
        }
        return result;
    }

}

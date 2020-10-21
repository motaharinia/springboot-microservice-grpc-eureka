package com.motaharinia.msutility.encoding;


import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.customexception.UtilityExceptionKeyEnum;
import com.motaharinia.msutility.fso.FsoTools;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.ObjectUtils;

import java.util.Base64;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * اینترفیس متدهای ابزاری رمزنگاری و رمزگشایی
 */
public interface EncodingTools {

    /**
     * این متد یک رشته نشانی وب را از ورودی دریافت میکند و معادل رمزنگاری شده base64 آن را با اصلاح بک اسلش به اسلش خروجی میدهد
     *
     * @param url رشته نشانی وب
     * @return خروجی: معادل رمزنگاری شده base64 نشانی وب با اصلاح بک اسلش به اسلش
     */
    @NotNull
    static String base64Encode(@NotNull String url) {
        if (ObjectUtils.isEmpty(url)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "url");
        }
        String encodedString = Base64.getEncoder().encodeToString(url.getBytes());
        return encodedString.replaceAll("\\\\+", "-").replaceAll("/", "_");
    }

    /**
     * این متد یک رشته نشانی وب رمز نگاری شده base64 را از ورودی دریافت میکند و  آن را به صورت رشته نشانی وب معمولی با اصلاح بعضی کارکترها خروجی میدهد
     *
     * @param url رشته نشانی وب رمز نگاری شده base64
     * @return خروجی: رشته نشانی وب معمولی با اصلاح بعضی کارکترها
     */
    @NotNull
    static String base64decode(@NotNull String url) {
        if (ObjectUtils.isEmpty(url)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "url");
        }
        byte[] decodedBytes = Base64.getDecoder().decode(url.replaceAll("-", "+").replaceAll("_", "/"));
        String decodedString = new String(decodedBytes);
        return decodedString;
    }
}

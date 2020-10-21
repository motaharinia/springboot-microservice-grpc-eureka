package com.motaharinia.msutility;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Locale;

import static java.lang.System.out;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-15<br>
 * Time: 17:13:07<br>
 * Description:<br>
 *     این کلاس برای بررسی تنظیمات کارکترست سیستم عامل و جی وی ام است. با اجرا کردن آن مشخصات را در کنسول میبینید.
 */
public class JvmCharsetCheck {

    public static String getEncoding()
    {
        final byte [] bytes = {'D'};
        final InputStream inputStream = new ByteArrayInputStream(bytes);
        final InputStreamReader reader = new InputStreamReader(inputStream);
        final String encoding = reader.getEncoding();
        return encoding;
    }
    public static void main(final String[] arguments)
    {
        out.println("Default Locale:   " + Locale.getDefault());
        out.println("Default Charset:  " + Charset.defaultCharset());
        out.println("file.encoding;    " + System.getProperty("file.encoding"));
        out.println("sun.jnu.encoding: " + System.getProperty("sun.jnu.encoding"));
        out.println("Default Encoding: " + getEncoding());
    }
}

package ir.micser.login.business.service.captchacode;



import ir.micser.login.presentation.captchacode.CaptchaCodeCheckModel;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *  اینترفیس کد کپچا
 */
@Component
public interface CaptchaCodeService  {

    /**
     * این متد نوع و شناسه انتیتی مرجع یا کلمه کاربری لاگین و درخواست وب را از ورودی دریافت کرده و در صورت نبودن آن را ایجاد و در صورت بودن ویرایش می نماید و مدل کپچا کد را خروجی میدهد
     * @param captchCodeTypeEnum نوع
     * @param referenceId شناسه انتیتی مورد نظر
     * @param referenceUsername کلمه کاربری لاگین
     * @param request درخواست وب
     * @return خروجی: مدل کپچا کد
     * @throws Exception
     */
    CaptchaCodeModel createOrUpdate(CaptchCodeTypeEnum captchCodeTypeEnum, Integer referenceId, String referenceUsername, HttpServletRequest request) throws Exception;

    /**
     * این متد نوع و شناسه انتیتی مرجع یا کلمه کاربری لاگین و کلید کپچا و مقدار کپچا را از ورودی دریافت کرده و مدل بررسی کپچا کد را خروجی میدهد
     * @param captchCodeTypeEnum نوع
     * @param referenceId شناسه انتیتی مورد نظر
     * @param referenceUsername کلمه کاربری لاگین
     * @param captchaKey کلید کپچا
     * @param captchValue مقدار کپچا
     * @return خروجی: مدل بررسی کد کپچا
     * @throws Exception
     */
    CaptchaCodeCheckModel check(CaptchCodeTypeEnum captchCodeTypeEnum, Integer referenceId, String referenceUsername, String captchaKey, String captchValue) throws Exception;

    /**
     * این متد در ابتدای هر روز اجرا میشود و مقدار retry همه کپچا کدهای منقضی نشده را صفر میکند<br>
     *     تا کاربرانی که روز قبل ignore شده اند بتوانند مجدد کد کپچا وارد نمایند
     * @throws Exception
     */
    void resetRetry() throws Exception;
}

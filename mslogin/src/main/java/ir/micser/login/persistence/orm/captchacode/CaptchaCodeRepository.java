package ir.micser.login.persistence.orm.captchacode;


import ir.micser.login.business.service.captchacode.CaptchCodeTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import th.co.geniustree.springdata.jpa.repository.JpaSpecificationExecutorWithProjection;

import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس ریپازیتوری کد کپچا
 */
@Repository
public interface CaptchaCodeRepository extends JpaRepository<CaptchaCode, Integer>, JpaSpecificationExecutorWithProjection<CaptchaCode> {
    /**
     * این متد بر اساس نوع و منقضی شده و شناسه انتیتی مورد نظر ورودی جستجو کرده و انتیتی کد کپچ را خروجی میدهد
     *
     * @param typeEnum    نوع
     * @param expired     منقضی شده؟
     * @param referenceId شناسه انتیتی مورد نظر
     * @return خروجی: انتیتی کد کپچا
     */
    CaptchaCode findByTypeEnumEqualsAndExpiredEqualsAndReferenceIdEquals(CaptchCodeTypeEnum typeEnum, Boolean expired, Integer referenceId);

    /**
     * این متد بر اساس نوع و منقضی شده و کلمه کاربری لاگین ورودی جستجو کرده و انتیتی کد کپچ را خروجی میدهد
     *
     * @param typeEnum          نوع
     * @param expired           منقضی شده؟
     * @param referenceUsername کلمه کاربری لاگین
     * @return خروجی: انتیتی کد کپچا
     */
    CaptchaCode findByTypeEnumEqualsAndExpiredEqualsAndReferenceUsernameEquals(CaptchCodeTypeEnum typeEnum, Boolean expired, String referenceUsername);

    /**
     * این متد بر اساس منقضی شده ورودی جستجو میکند و لیست انتیتی را خروجی میدهد
     *
     * @param expired منقضی شده؟
     * @return خروجی: لیست انتیتی
     */
    List<CaptchaCode> findAllByExpiredEquals(Boolean expired);


    /**
     * این متد کلید کپچا را از ورودی دریافت میکند و انتیتی آن را خروجی میدهد
     *
     * @param captchaKey کاید کپچا
     * @return خروجی: انتیتی کپچا
     */
    CaptchaCode findByCaptchaKeyEquals(String captchaKey);
}

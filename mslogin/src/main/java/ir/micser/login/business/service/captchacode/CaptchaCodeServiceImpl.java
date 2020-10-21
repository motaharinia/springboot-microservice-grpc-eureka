package ir.micser.login.business.service.captchacode;

import com.motaharinia.msutility.captcha.CaptchaConfigModel;
import com.motaharinia.msutility.captcha.CaptchaTools;
import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.string.RandomGenerationTypeEnum;
import com.motaharinia.msutility.string.StringTools;
import ir.micser.login.persistence.orm.captchacode.CaptchaCode;
import ir.micser.login.persistence.orm.captchacode.CaptchaCodeRepository;
import ir.micser.login.presentation.captchacode.CaptchaCodeCheckModel;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس پیاده سازی اینترفیس کد کپچا
 */
@Component
@Service
@Transactional(rollbackFor = Exception.class)
public class CaptchaCodeServiceImpl implements CaptchaCodeService {

//    private SettingService settingService;
    private CaptchaCodeRepository captchaCodeRepository;

//    @Autowired
//    public CaptchaCodeServiceImpl(CaptchaCodeRepository captchaCodeRepository, SettingService settingService) {
//        this.captchaCodeRepository = captchaCodeRepository;
//        this.settingService = settingService;
//    }
    @Autowired
    public CaptchaCodeServiceImpl(CaptchaCodeRepository captchaCodeRepository) {
        this.captchaCodeRepository = captchaCodeRepository;
    }

    @Override
    public CaptchaCodeModel createOrUpdate(CaptchCodeTypeEnum captchCodeTypeEnum, Integer referenceId, String referenceUsername, HttpServletRequest request) throws Exception {

        if (ObjectUtils.isEmpty(referenceId) && ObjectUtils.isEmpty(referenceUsername)) {
            throw new BusinessException(CaptchaCode.class, CaptchaCodeBusinessExceptionKeyEnum.CAPTCHA_CODE_REFERENCE_IS_NULL, "referenceId and referenceUsername is empty");
        }

        CaptchaCodeModel captchaCodeModel = new CaptchaCodeModel();
        //ساخت کلید و مقدار کپچا
        captchaCodeModel.setCaptchaKey(StringTools.generateRandomString(RandomGenerationTypeEnum.NUMBER, 6, false));
        captchaCodeModel.setCaptchaValue(StringTools.generateRandomString(RandomGenerationTypeEnum.CHARACTER_NUMBER, 6, false));

        //ساخت تصویر کپچا
        BufferedImage bufferedImage = CaptchaTools.generateCaptcha(new CaptchaConfigModel(), captchaCodeModel.getCaptchaValue(), BufferedImage.TYPE_INT_RGB);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "JPG", baos);
        baos.flush();
        byte[] captchaImageByteArray = baos.toByteArray();
        baos.close();
        captchaCodeModel.setCaptchaImageByteArray(captchaImageByteArray);

        //ثبت در دیتابیس
        CaptchaCode captchaCode = null;
        if (captchCodeTypeEnum != CaptchCodeTypeEnum.LOGIN_CHECK_BY_USERNAME) {
            captchaCode = this.findByReferenceId(referenceId, captchCodeTypeEnum, false);
        } else {
            captchaCode = this.findByReferenceUsername(referenceUsername, captchCodeTypeEnum, false);
        }
        if (captchaCode != null) {
            captchaCode.setCaptchaKey(captchaCodeModel.getCaptchaKey());
            captchaCode.setCaptchaValue(captchaCodeModel.getCaptchaValue());
            captchaCode = captchaCodeRepository.save(captchaCode);
        } else {
            captchaCode = new CaptchaCode();
            captchaCode.setCaptchaKey(captchaCodeModel.getCaptchaKey());
            captchaCode.setCaptchaValue(captchaCodeModel.getCaptchaValue());
            captchaCode.setExpired(false);
            captchaCode.setReferenceId(referenceId);
            captchaCode.setReferenceUsername(referenceUsername);
            captchaCode.setTypeEnum(captchCodeTypeEnum);
            captchaCode = captchaCodeRepository.save(captchaCode);
        }
        captchaCodeModel.setId(captchaCode.getId());
        return captchaCodeModel;
    }

    @Override
    public CaptchaCodeCheckModel check(CaptchCodeTypeEnum captchCodeTypeEnum, Integer referenceId, String referenceUsername, String captchaKey, String captchValue) throws Exception {

        CaptchaCodeCheckModel captchaCodeCheckModel = new CaptchaCodeCheckModel();
        try {
            //validation
            if (ObjectUtils.isEmpty(captchCodeTypeEnum)) {
                throw new BusinessException(CaptchaCode.class, CaptchaCodeBusinessExceptionKeyEnum.CAPTCHA_CODE_TYPE_IS_NULL, "");
            } else {
                if (captchCodeTypeEnum != CaptchCodeTypeEnum.LOGIN_CHECK_BY_USERNAME && referenceId == null) {
                    throw new BusinessException(CaptchaCode.class, CaptchaCodeBusinessExceptionKeyEnum.CAPTCHA_CODE_REFERENCE_IS_NULL, "");
                }
            }

            //find
            CaptchaCode captchaCode = null;
            if (captchCodeTypeEnum != CaptchCodeTypeEnum.LOGIN_CHECK_BY_USERNAME) {
                captchaCode = this.findByReferenceId(referenceId, captchCodeTypeEnum, false);
            } else {
                captchaCode = this.findByReferenceUsername(referenceUsername, captchCodeTypeEnum, false);
            }


            if (!ObjectUtils.isEmpty(captchaCode)) {
                //تنظیمات مربوط به تعداد دفعات مجاز برای تست کد
//                Setting setting = settingService.findByKey(SettingModuleEnum.SECURITY, SettingKeyEnum.SECURITY_CAPTCHA_CODE_TOTAL);
//                Integer allowCount = Integer.parseInt(setting.getValue());

                Integer allowCount=5;
                //آیا به حدنصاب نرسیده است؟
                if (captchaCode.getRetry() < allowCount) {
                    //به حدنصاب نرسیده است.
                    if (StringUtils.isNotEmpty(captchaKey) && StringUtils.isNotEmpty(captchValue)
                            && captchaCode.getCaptchaValue().toLowerCase().equals(captchValue.toLowerCase())) {
                        captchaCode.setExpired(Boolean.TRUE);
                        captchaCodeRepository.save(captchaCode);
                        captchaCodeCheckModel.setCaptchaExpired(Boolean.TRUE);
                    } else {
                        //captcha error
                        captchaCode.setRetry(captchaCode.getRetry() + 1);
                        captchaCodeRepository.save(captchaCode);
                        captchaCodeCheckModel.setCaptchaRetry(captchaCode.getRetry());
                        throw new BusinessException(CaptchaCode.class, CaptchaCodeBusinessExceptionKeyEnum.CAPTCHA_CODE_REJECT, "");

                    }
                } else {

                    captchaCode.setRetry(captchaCode.getRetry() + 1);
                    captchaCodeRepository.save(captchaCode);
                    captchaCodeCheckModel.setCaptchaRetry(captchaCode.getRetry());
                    throw new BusinessException(CaptchaCode.class, CaptchaCodeBusinessExceptionKeyEnum.CAPTCHA_CODE_IGNORE, "");
                }

            } else {
                throw new BusinessException(CaptchaCode.class, CaptchaCodeBusinessExceptionKeyEnum.CAPTCHA_CODE_NOT_FOUND, "");
            }

        } catch (Exception exception) {
            captchaCodeCheckModel.setCaptchaException(exception);
        }

        return captchaCodeCheckModel;
    }

    private CaptchaCode findByReferenceId(Integer referenceId, CaptchCodeTypeEnum captchCodeTypeEnum, Boolean expired) throws Exception {
        if (ObjectUtils.isEmpty(captchCodeTypeEnum)  ||  ObjectUtils.isEmpty(referenceId) ) {
            return null;
        }
        CaptchaCode captchaCode = captchaCodeRepository.findByTypeEnumEqualsAndExpiredEqualsAndReferenceIdEquals(captchCodeTypeEnum, expired, referenceId);
        return captchaCode;
    }

    private CaptchaCode findByReferenceUsername(String referenceUsername, CaptchCodeTypeEnum captchCodeTypeEnum, Boolean expired) throws Exception {
        if (ObjectUtils.isEmpty(captchCodeTypeEnum)  ||  ObjectUtils.isEmpty(referenceUsername) ) {
            return null;
        }
        CaptchaCode captchaCode = captchaCodeRepository.findByTypeEnumEqualsAndExpiredEqualsAndReferenceUsernameEquals(captchCodeTypeEnum, expired, referenceUsername);
        return captchaCode;
    }

    public void resetRetry() throws Exception {
        List<CaptchaCode> captchaCodeList = captchaCodeRepository.findAllByExpiredEquals(Boolean.FALSE);
        for (CaptchaCode captchaCode : captchaCodeList) {
            captchaCode.setRetry(0);
            captchaCodeRepository.save(captchaCode);
        }
    }

}

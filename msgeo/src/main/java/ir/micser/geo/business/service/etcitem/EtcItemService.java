package ir.micser.geo.business.service.etcitem;


import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import ir.micser.geo.persistence.orm.etcitem.EtcItem;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *  اینترفیس مدیریت مقادیر ثابت
 */
@Component
public interface EtcItemService  {

    /**
     * این متد یک مقدار ثابت را با استفاده از شناسه آن جستجو میکند و چک میکند آن مقدار ثابت غیرفعال نباشد
     * @param id شناسه مقدار ثابت
     * @param etcItemEnumClass کلاس مقدارثابت
     * @param checkTypeTag  تگ مقدار ثابت برای جستجوی گروهی فیلتر کردن در کامبوها استفاده میشود
     * @param checkInvalid آیا بررسی کند که غیرفعال نباشد؟
     * @return خروجی: مقدار ثابت
     */
    @NotNull
    EtcItem findByIdAndCheckEntity(@NotNull Integer id, @NotNull Class etcItemEnumClass, String checkTypeTag, @NotNull Boolean checkInvalid) throws UtilityException, InvocationTargetException, IllegalAccessException, BusinessException,Exception;

    /**
     * این متد یک مقدار ثابت را با استفاده از مقدار آن جستجو میکند و چک میکند آن مقدار ثابت غیرفعال نباشد
     * @param checkEtcItemEnum  مقدار ثابت که حاوی مقدار برای جستجو است
     * @param checkTypeTag  تگ مقدار ثابت برای جستجوی گروهی فیلتر کردن در کامبوها استفاده میشود
     * @param checkInvalid آیا بررسی کند که غیرفعال نباشد؟
     * @return خروجی: مقدار ثابت
     */
    @NotNull
    EtcItem findByValueAndCheckEntity(@NotNull EtcItemEnum checkEtcItemEnum, String checkTypeTag, @NotNull Boolean checkInvalid) throws IllegalAccessException, UtilityException, InvocationTargetException,Exception;
    
//     @Override
//    CustomComboModel customCombo(CustomComboFilterModel model) throws Exception;

    /**
     * این متد typeTag دلخواه را دریافت میکند و تمام مقادیر ثابت مربوط به آن typeTag را خروجی میدهد
     * @param typeTag تگ مقدار ثابت برای جستجوی گروهی فیلتر کردن در کامبوها استفاده میشود
     * @return خروجی: لیستی از مقادیر ثابت
     */
    @NotNull
    List<EtcItem> findByTypeTag(@NotNull String typeTag);
}

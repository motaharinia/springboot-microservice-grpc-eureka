package ir.micser.geo.business.service.city;

import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.search.data.SearchDataModel;
import com.motaharinia.msutility.search.filter.SearchFilterModel;
import ir.micser.geo.presentation.city.CityModel;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * اینترفیس مدیریت شهر ها
 */
public interface CityService {
    /**
     * متد ثبت
     *
     * @param cityModel مدل ثبت
     * @return خروجی: مدل ثبت حاوی شناسه
     */
    @NotNull
    CityModel create(@NotNull CityModel cityModel) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException;

    /**
     * متد جستجوی با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل جستجو شده
     */
    @NotNull
    CityModel readById(@NotNull Integer id) throws UtilityException;

    /**
     * متد جستجو با مدل فیلتر جستجو
     *
     * @param searchFilterModel        مدل فیلتر جستجو
     * @param searchViewTypeInterface        کلاس اینترفیس نوع نمایش خروجی که ستونهای(فیلدهای) خروجی داخل آن تعریف شده است
     * @param searchValueList لیست مقادیر مورد نیاز جهت جستجو
     * @return خروجی: مدل داده جستجو
     * @throws UtilityException
     */
    @NotNull
    SearchDataModel readGrid(@NotNull SearchFilterModel searchFilterModel, @NotNull Class searchViewTypeInterface, @NotNull List<Object> searchValueList) throws UtilityException;


    /**
     * متد ویرایش
     *
     * @param cityModel مدل ویرایش
     * @return خروجی: مدل ویرایش شده
     */
    @NotNull
    CityModel update(@NotNull CityModel cityModel) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException;

    /**
     * متد حذف با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل حذف شده
     */
    @NotNull
    CityModel delete(@NotNull Integer id) throws UtilityException;

}

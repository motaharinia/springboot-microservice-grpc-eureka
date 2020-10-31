package ir.micser.geo.presentation.city;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.json.CustomObjectMapper;
import com.motaharinia.msjpautility.search.data.SearchDataModel;
import com.motaharinia.msjpautility.search.filter.SearchFilterModel;
import ir.micser.geo.business.service.city.CityService;
import ir.micser.geo.business.service.city.CitySearchViewTypeEnum;
import ir.micser.geo.business.service.city.CitySearchViewTypeBrief;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *  کلاس کنترلر شهر
 */
@RestController
public class CityController {

    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    /**
     * متد ثبت
     *
     * @param cityModel مدل ثبت
     * @return خروجی: مدل ثبت حاوی شناسه
     */
    @PostMapping("/v1/city")
    public CityModel create(@RequestBody @Validated CityModel cityModel) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException {
        return cityService.create(cityModel);
    }

    /**
     * متد جستجوی با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل جستجو شده
     */
    @GetMapping("/v1/city/{id}")
    public CityModel readById(@PathVariable Integer id) throws UtilityException {
        return cityService.readById(id);
    }



    /**
     * متد جستجو با رشته مدل فیلتر جستجو
     *
     * @param searchFilterModelJson رشته جیسون مدل فیلتر جستجو
     * @param searchViewTypeEnum    نوع نمایش خروجی که ستونهای(فیلدهای) خروجی داخل آن تعریف شده است
     * @param searchValueList       لیست مقادیر مورد نیاز جهت جستجو
     * @return خروجی: مدل داده جستجو
     * @throws UtilityException
     */
    @GetMapping("/v1/city")
    public SearchDataModel readGrid(@RequestParam(name = "searchFilterModel") Optional<String> searchFilterModelJson, @RequestParam(name = "searchViewTypeEnum") CitySearchViewTypeEnum searchViewTypeEnum, @RequestParam(name = "searchValueList") List<Object> searchValueList) throws JsonProcessingException, UtilityException, ClassNotFoundException {
        CustomObjectMapper customObjectMapper = new CustomObjectMapper();
        SearchFilterModel searchFilterModel = customObjectMapper.readValue(searchFilterModelJson.get(), SearchFilterModel.class);
        //تعیین اینترفیس ستونهای(فیلدهای خروجی) داده
        Class searchViewTypeInterface = CitySearchViewTypeBrief.class;
        if (!ObjectUtils.isEmpty(searchViewTypeEnum)) {
            searchViewTypeInterface = Class.forName(searchViewTypeEnum.getValue());
        }
        //در صورت نال بودن باید new شود
        if(ObjectUtils.isEmpty(searchValueList)){
            searchValueList = new ArrayList<>();
        }

        SearchDataModel searchDataModel = cityService.readGrid(searchFilterModel, searchViewTypeInterface, searchValueList);
        return searchDataModel;
    }


    /**
     * متد جستجو با مدل فیلتر جستجو
     *
     * @param searchFilterModel  مدل فیلتر جستجو
     * @param searchViewTypeEnum    نوع نمایش خروجی که ستونهای(فیلدهای) خروجی داخل آن تعریف شده است
     * @param searchValueList       لیست مقادیر مورد نیاز جهت جستجو
     * @return خروجی: مدل داده جستجو
     * @throws JsonProcessingException
     * @throws UtilityException
     */
    @GetMapping("/v1/cityByModel")
    public SearchDataModel readGridByModel(@RequestBody @Validated SearchFilterModel searchFilterModel, @RequestParam(name = "searchViewTypeEnum") CitySearchViewTypeEnum searchViewTypeEnum, @RequestParam(name = "searchValueList") List<Object> searchValueList) throws JsonProcessingException, UtilityException, ClassNotFoundException {
        //تعیین اینترفیس ستونهای(فیلدهای خروجی) داده
        Class searchViewTypeInterface = CitySearchViewTypeBrief.class;
        if (!ObjectUtils.isEmpty(searchViewTypeEnum)) {
            searchViewTypeInterface = Class.forName(searchViewTypeEnum.getValue());
        }
        //در صورت نال بودن باید new شود
        if(ObjectUtils.isEmpty(searchValueList)){
            searchValueList = new ArrayList<>();
        }

        SearchDataModel searchDataModel = cityService.readGrid(searchFilterModel, searchViewTypeInterface, searchValueList);
        return searchDataModel;
    }


    /**
     * متد ویرایش
     *
     * @param cityModel مدل ویرایش
     * @return خروجی: مدل ویرایش شده
     */
    @PutMapping("/v1/city")
    public CityModel update(@RequestBody @Validated CityModel cityModel) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException {
        return cityService.update(cityModel);
    }

    /**
     * متد حذف با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل حذف شده
     */
    @DeleteMapping("/v1/city/{id}")
    public CityModel delete(@PathVariable Integer id) throws UtilityException {
        return cityService.delete(id);
    }


}

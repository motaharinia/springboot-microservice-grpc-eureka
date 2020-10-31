package ir.micser.geo.business.service.city;


import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.grpc.GrpcExceptionHandler;
import com.motaharinia.msjpautility.search.data.SearchDataModel;
import com.motaharinia.msjpautility.search.filter.SearchFilterModel;
import io.grpc.stub.StreamObserver;
import ir.micser.geo.business.service.city.stub.CityGrpc.CityImplBase;
import ir.micser.geo.business.service.city.stub.CityMicro.*;
import ir.micser.geo.business.service.etcitem.EtcItemService;
import ir.micser.geo.persistence.orm.city.City;
import ir.micser.geo.persistence.orm.city.CityRepository;
import ir.micser.geo.persistence.orm.city.CitySpecification;
import ir.micser.geo.presentation.city.CityModel;
import net.devh.boot.grpc.server.service.GrpcService;
import org.jetbrains.annotations.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس پیاده سازی اینترفیس مدیریت شهر ها
 */
@GrpcService(interceptors = { GrpcExceptionHandler.class })
@Service
@Transactional(rollbackFor = Exception.class)
public class CityServiceImpl extends CityImplBase implements CityService {


    /**
     * ریپازیتوری شهر
     */
    private CityRepository cityRepository;

    /**
     * سرویس مقادیر ثابت
     */
    private EtcItemService etcItemService;

    /**
     * مشخصات جستجوی شهر
     */
    private CitySpecification citySpecification;
    /**
     * تبدیل کننده مدل
     */
    private ModelMapper modelMapper;

    /**
     * متد سازنده پیش فرض
     */
    public CityServiceImpl() {
    }

    /**
     * متد سازنده
     */
    @Autowired
    public CityServiceImpl(CityRepository cityRepository, EtcItemService etcItemService, CitySpecification citySpecification, ModelMapper modelMapper) {
        this.cityRepository = cityRepository;
        this.etcItemService = etcItemService;
        this.citySpecification = citySpecification;
        this.modelMapper = modelMapper;
    }

    /**
     * متد ثبت
     *
     * @param cityModel مدل ثبت
     * @return خروجی: مدل ثبت حاوی شناسه
     */
    @Override
    @NotNull
    public CityModel create(@NotNull CityModel cityModel) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException {


        //ثبت شهر
        City city = new City();
        city.setTitle(cityModel.getTitle());
        city = cityRepository.save(city);
        cityModel.setId(city.getId());
        return cityModel;
    }

    /**
     * متد جستجوی با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل جستجو شده
     */
    @Override
    @NotNull
    public CityModel readById(@NotNull Integer id) throws UtilityException {
        CityModel cityModel = new CityModel();
        City city = cityRepository.findById(id).get();
        if (!ObjectUtils.isEmpty(city)) {
            cityModel.setId(city.getId());
            cityModel.setTitle(city.getTitle());
        }
//    CityModel  cityModel= (CityModel) SerializationUtils.clone(city);
        return cityModel;
    }


    /**
     * متد جستجو با مدل فیلتر جستجو
     *
     * @param searchFilterModel        مدل فیلتر جستجو
     * @param searchViewTypeInterface        کلاس اینترفیس نوع نمایش خروجی که ستونهای(فیلدهای) خروجی داخل آن تعریف شده است
     * @param searchValueList لیست مقادیر مورد نیاز جهت جستجو
     * @return خروجی: مدل داده جستجو
     * @throws UtilityException
     */
    @Override
    @NotNull
    public SearchDataModel readGrid(@NotNull SearchFilterModel searchFilterModel, @NotNull Class searchViewTypeInterface, @NotNull List<Object> searchValueList) throws UtilityException {
        //تعریف فیلترهای جستجو
        citySpecification = (CitySpecification) searchFilterModel.makeSpecification(new CitySpecification());
        //جستجو بر طبق فیلترهای جستجو و کلاس اینترفیس نوع نمایش و صفحه بندی دلخواه کلاینت ساید
        Page<CitySearchViewTypeBrief> viewPage = cityRepository.findAll(citySpecification, searchViewTypeInterface, searchFilterModel.makePageable());
        //تعریف خروجی بر اساس جستجو
        SearchDataModel searchDataModel = new SearchDataModel(viewPage, searchFilterModel, searchViewTypeInterface, "");
        return searchDataModel;
    }

    /**
     * متد ویرایش
     *
     * @param cityModel مدل ویرایش
     * @return خروجی: مدل ویرایش شده
     */
    @Override
    @NotNull
    public CityModel update(@NotNull CityModel cityModel) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException {
        City city = cityRepository.findById(cityModel.getId()).get();
        city.setTitle(cityModel.getTitle());
        cityRepository.save(city);
        return cityModel;
    }

    /**
     * متد حذف با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل حذف شده
     */
    @Override
    @NotNull
    public CityModel delete(@NotNull Integer id) throws UtilityException {
        CityModel cityModel = this.readById(id);
        City city = cityRepository.findById(cityModel.getId()).get();
        cityRepository.save(city);
        cityRepository.delete(city);
        return cityModel;
    }

    @NotNull
    private CityModel convertToDto(@NotNull City city) {
        CityModel cityModel = modelMapper.map(city, CityModel.class);
//        cityModel.setSubmissionDate(city.getSubmissionDate(), cityService.getCurrentUser().getPreference().getTimezone());
        return cityModel;
    }


    /**
     * این متد gRPC یک مدل حاوی شناسه شهر را از ورودی دریافت میکند و مدل آن را خروجی میدهد
     * @param readByIdRequestModel مدل شناسه
     * @param responseObserver
     */
    @Override
    public void grpcReadById(ReadByIdRequestModel readByIdRequestModel, StreamObserver<ReadByIdResponseModel> responseObserver) {
        ReadByIdResponseModel.Builder readByIdResponseModel = ReadByIdResponseModel.newBuilder();
        CityModel cityModel = null;
        try {
            cityModel = this.readById(readByIdRequestModel.getId());
        } catch (UtilityException e) {
            e.printStackTrace();
        }
        if (!ObjectUtils.isEmpty(cityModel.getId())) {
            readByIdResponseModel.setId(cityModel.getId());
            readByIdResponseModel.setTitle(cityModel.getTitle());
        }
        responseObserver.onNext(readByIdResponseModel.build());
        responseObserver.onCompleted();
    }
}

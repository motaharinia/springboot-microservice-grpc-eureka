package ir.micser.geo.business.service.cityplace;


import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msjpautility.entity.EntityTools;
import com.motaharinia.msjpautility.search.data.SearchDataModel;
import com.motaharinia.msjpautility.search.filter.SearchFilterModel;
import io.grpc.stub.StreamObserver;
import ir.micser.geo.business.service.city.CitySearchViewTypeBrief;
import ir.micser.geo.business.service.cityplace.stub.CityPlaceGrpc.*;
import ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.*;
import ir.micser.geo.persistence.orm.city.City;
import ir.micser.geo.persistence.orm.city.CityRepository;
import ir.micser.geo.persistence.orm.cityplace.CityPlace;
import ir.micser.geo.persistence.orm.cityplace.CityPlaceRepository;
import ir.micser.geo.persistence.orm.cityplace.CityPlaceSpecification;
import ir.micser.geo.presentation.cityplace.CityPlaceModel;
import net.devh.boot.grpc.server.service.GrpcService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس پیاده سازی اینترفیس مدیریت لوکیشن شهری
 */
@GrpcService
@Service
@Transactional(rollbackFor = Exception.class)
public class CityPlaceServiceImpl extends CityPlaceImplBase implements CityPlaceService {


    /**
     * ریپازیتوری لوکیشن شهری
     */
    private CityPlaceRepository cityPlaceRepository;

    /**
     * سرویس مقادیر ثابت
     */
    private CityRepository cityRepository;

    /**
     * مشخصات جستجوی لوکیشن شهری
     */
    private CityPlaceSpecification cityPlaceSpecification;

    /**
     * متد سازنده پیش فرض
     */
    public CityPlaceServiceImpl() {
    }


    @Autowired
    private PlatformTransactionManager transactionManager;



    /**
     * متد سازنده
     */
    @Autowired
    public CityPlaceServiceImpl(CityPlaceRepository cityPlaceRepository, CityRepository cityRepository, CityPlaceSpecification cityPlaceSpecification) {
        this.cityPlaceRepository = cityPlaceRepository;
        this.cityRepository = cityRepository;
        this.cityPlaceSpecification = cityPlaceSpecification;
    }

    /**
     * متد ثبت
     *
     * @param cityPlaceModel مدل ثبت
     * @return خروجی: مدل ثبت حاوی شناسه
     */
    @Override
    @NotNull
    public CityPlaceModel create(@NotNull CityPlaceModel cityPlaceModel) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException,Exception {
        //ثبت لوکیشن شهری ادمین
        CityPlace cityPlace = new CityPlace();
        cityPlace.setAdminUserId(cityPlaceModel.getAdminUserId());
        City city = cityRepository.findById(cityPlaceModel.getCity_id()).get();
        EntityTools.checkEntity(city, City.class, true);
        cityPlace.setCity(city);
        cityPlace.setLatitude(cityPlaceModel.getLatitude());
        cityPlace.setLongitude(cityPlaceModel.getLongitude());
        cityPlace.setTitle(cityPlaceModel.getTitle());
        cityPlace = cityPlaceRepository.save(cityPlace);
        cityPlaceModel.setId(cityPlace.getId());
        return cityPlaceModel;
    }

    /**
     * متد جستجوی با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل جستجو شده
     */
    @Override
    @NotNull
    public CityPlaceModel readById(@NotNull Integer id) {
        CityPlaceModel cityPlaceModel = new CityPlaceModel();
        CityPlace cityPlace = cityPlaceRepository.findById(id).get();
        if (!ObjectUtils.isEmpty(cityPlace)) {
            cityPlaceModel.setId(cityPlace.getId());
            cityPlaceModel.setAdminUserId(cityPlace.getAdminUserId());
            if (!ObjectUtils.isEmpty(cityPlace.getCity())) {
                cityPlaceModel.setCity_id(cityPlace.getCity().getId());
            }
            cityPlaceModel.setLatitude(cityPlace.getLatitude());
            cityPlaceModel.setLongitude(cityPlace.getLongitude());
            cityPlaceModel.setTitle(cityPlace.getTitle());
        }
//    CityPlaceModel  cityPlaceModel= (CityPlaceModel) SerializationUtils.clone(cityPlace);
        return cityPlaceModel;
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
        cityPlaceSpecification = (CityPlaceSpecification) searchFilterModel.makeSpecification(new CityPlaceSpecification());
        //جستجو بر طبق فیلترهای جستجو و کلاس اینترفیس نوع نمایش و صفحه بندی دلخواه کلاینت ساید
        Page<CitySearchViewTypeBrief> viewPage = cityPlaceRepository.findAll(cityPlaceSpecification, searchViewTypeInterface, searchFilterModel.makePageable());
        //تعریف خروجی بر اساس جستجو
        SearchDataModel searchDataModel = new SearchDataModel(viewPage, searchFilterModel, searchViewTypeInterface, "");
        return searchDataModel;
    }


    /**
     * متد ویرایش
     *
     * @param cityPlaceModel مدل ویرایش
     * @return خروجی: مدل ویرایش شده
     */
    @Override
    @NotNull
    public CityPlaceModel update(@NotNull CityPlaceModel cityPlaceModel) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException,Exception {
        CityPlace cityPlace = cityPlaceRepository.findById(cityPlaceModel.getId()).get();
        cityPlace.setAdminUserId(cityPlaceModel.getAdminUserId());
        City city = cityRepository.findById(cityPlaceModel.getCity_id()).get();
        EntityTools.checkEntity(city, City.class, true);
        cityPlace.setCity(city);
        cityPlace.setLatitude(cityPlaceModel.getLatitude());
        cityPlace.setLongitude(cityPlaceModel.getLongitude());
        cityPlace.setTitle(cityPlaceModel.getTitle());
        cityPlaceRepository.save(cityPlace);
        return cityPlaceModel;
    }

    /**
     * متد حذف با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل حذف شده
     */
    @Override
    @NotNull
    public CityPlaceModel delete(@NotNull Integer id) throws UtilityException {
        CityPlaceModel cityPlaceModel = this.readById(id);
        CityPlace cityPlace = cityPlaceRepository.findById(cityPlaceModel.getId()).get();
        cityPlaceRepository.save(cityPlace);
        cityPlaceRepository.delete(cityPlace);
        return cityPlaceModel;
    }


    /**
     * این متد gRPC مدل ثبت را دریافت کرده و یک لوکیشن شهری ادمین برای ادمین در شهر داخل مدل ثبت مینماید
     *
     * @param createRequestModel مدل ثبت
     * @param responseObserver
     */
    @Override
    public void grpcCreate(CreateRequestModel createRequestModel, StreamObserver<CreateResponseModel> responseObserver) {
        CreateResponseModel.Builder createResponseModel = CreateResponseModel.newBuilder();
        CityPlaceModel cityPlaceModel = new CityPlaceModel();
        cityPlaceModel.setCity_id(createRequestModel.getCityId());
        cityPlaceModel.setLatitude(createRequestModel.getLatitude());
        cityPlaceModel.setLongitude(createRequestModel.getLongitude());
        cityPlaceModel.setAdminUserId(createRequestModel.getAdminUserId());
        cityPlaceModel.setTitle(createRequestModel.getTitle());

        CityPlace cityPlace = new CityPlace();

         TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        definition.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
        definition.setTimeout(3);
        definition.setName("test");
        TransactionStatus status = transactionManager.getTransaction(definition);

        try {
            cityPlace.setAdminUserId(cityPlaceModel.getAdminUserId());
            City city = cityRepository.findById(cityPlaceModel.getCity_id()).get();
            EntityTools.checkEntity(city, City.class, true);
            cityPlace.setCity(city);
            cityPlace.setLatitude(cityPlaceModel.getLatitude());
            cityPlace.setLongitude(cityPlaceModel.getLongitude());
            cityPlace.setTitle(cityPlaceModel.getTitle());
            cityPlace = cityPlaceRepository.save(cityPlace);

//            entityManager.persist(payment);
//            transactionManager.commit(status);
        } catch (Exception ex) {
            transactionManager.rollback(status);
        }

//        try {
//            cityPlaceModel = this.create(cityPlaceModel);
//        } catch (Exception ex) {
//            System.out.println("CityPlaceServiceImpl.grpcCreate exception:" + ex.toString());
//        }

        createResponseModel.setAdminUserId(cityPlaceModel.getAdminUserId());
        createResponseModel.setCityId(cityPlaceModel.getCity_id());
        createResponseModel.setId(cityPlace.getId());
        createResponseModel.setLatitude(cityPlaceModel.getLatitude());
        createResponseModel.setLongitude(cityPlaceModel.getLongitude());
        createResponseModel.setTitle(cityPlaceModel.getTitle());
        createResponseModel.setTransactionName(definition.getName());

        responseObserver.onNext(createResponseModel.build());
        responseObserver.onCompleted();
    }


    /**
     * @param request
     * @param responseObserver
     */
    @Override
    public void grpcCommit(TransactionModel request, StreamObserver<Empty> responseObserver) {
        Empty.Builder emptyModel = Empty.newBuilder();

        DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(definition);
        transactionManager.commit(status);

        responseObserver.onNext(emptyModel.build());
        responseObserver.onCompleted();
    }
}

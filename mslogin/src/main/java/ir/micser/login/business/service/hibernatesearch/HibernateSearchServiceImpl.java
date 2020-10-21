package ir.micser.login.business.service.hibernatesearch;

import com.motaharinia.msutility.json.PrimitiveResponse;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.hibernate.search.MassIndexer;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.sort.SortFieldContext;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor=Exception.class)
public class HibernateSearchServiceImpl implements HibernateSearchService{


    private FullTextEntityManager fullTextEntityManager;
    @Autowired
    public HibernateSearchServiceImpl(FullTextEntityManager fullTextEntityManager) {
        this.fullTextEntityManager = fullTextEntityManager;
    }


    @Override
    public QueryBuilder getQueryBuilder(Class entityClass) throws InterruptedException {
        return fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(entityClass).get();
    }

    /**
     *  این متد کلاس و کوئری جستجوی ایندکس و رشته مرتب سازی را از ورودی دریافت می کند و لیستی از آبجکت های جستجو شده را خروجی می دهد.
     * @param entityClass کلاس ایندکس شده
     * @param luceneQuery کوئری جستجوی ایندکس
     * @param projection نام فیلدهای آبجکت خروجی
     * @param sortString رشته مرتب سازی
     * @param pagingRows
     * @param pagingNo
     * @return خروجی: لیست آبجکت های سرچ شده
     * @throws Exception این متد ممکن است اکسپشن صادر کند
     */
    @Override
    public List<Object[]> indexArrayListBy(Class entityClass, Query luceneQuery, String projection, String sortString, Integer pagingRows, Integer pagingNo) throws Exception {

        FullTextQuery query = fullTextEntityManager.createFullTextQuery(luceneQuery, entityClass);
        //------------------//
        // Projection
        if (StringUtils.isNotEmpty(projection)) {
            String[] projectionArray = projection.trim().split(",");
            query.setProjection(projectionArray);
        }

        //------------------//
        //Pageing
        if (pagingRows != null && pagingRows != 0) {
            query.setFirstResult(pagingNo);
        }
        if (pagingNo != null && pagingRows != 0) {
            query.setMaxResults(pagingRows);
        }
        //-------------------//
        //Sort
        if (StringUtils.isNotEmpty(sortString)) {
            query.setSort(this.initSort(entityClass, sortString));
        }
        //-------------------//
        return query.getResultList();

    }

    /**
     * این متد کلاس و کوئری جستجوی ایندکس و رشته مرتب سازی را از ورودی دریافت می کند و لیستی از شناسه های جستجو شده را خروجی می دهد.
     * @param entityClass کلاس ایندکس شده
     * @param luceneQuery کوئری جستجوی ایندکس
     * @param sortString رشته مرتب سازی
     * @return خروجی: لیستی از شناسه های جستجو شده
     * @throws Exception این متد ممکن است اکسپشن صادر کند
     */
    @Override
    public List<Integer> indexIdListBy(Class entityClass, Query luceneQuery, String sortString) throws Exception {
        List<Object[]> arrayObjectList = indexArrayListBy(entityClass, luceneQuery, "id", sortString, null, null);
        List<Integer> idList = arrayObjectList.parallelStream().map(object -> (Integer) object[0]).collect(Collectors.toList());
        return idList;
    }

    /**
     * این متد نام کلاس ایندکس شده و فیلد مرتب شده را از ورودی دریافت می کند و خروجی جستجو شده را مرتب می کند
     * @param entityClass کلاس ایندکس شده
     * @param sortString رشته مرتب سازی مثال lastName ASC,firstName DESC
     * @return خروجی: کلاسی از نوع مرتب شده
     * @throws InterruptedException این متد ممکن است اکسپشن صادر کند
     */
    private Sort initSort(Class entityClass, @NotNull String sortString) throws InterruptedException {
        SortFieldContext sortFieldContext;
        QueryBuilder builder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(entityClass).get();

        String[] sortStringArray = sortString.trim().split(",");
        //اگر توکن پیدا نشود
        if (sortStringArray.length == 0) {
            return null;
        }

        //===========================================//
        //اولی با بقیه متفاوت است به همین خاطر ابتدا
        //اولین را ایجاد میکنیم
        String[] zeroIndexSingleSortStrinArray = sortStringArray[0].trim().split(" ");
        switch (zeroIndexSingleSortStrinArray[1]) {
            case "ASC":
                sortFieldContext = builder.sort().byField(zeroIndexSingleSortStrinArray[0]).asc();
                break;
            case "DESC":
                sortFieldContext = builder.sort().byField(zeroIndexSingleSortStrinArray[0]).desc();
                break;
            default:
                return null;
        }
        //------------------------------------------//
        if (sortStringArray.length > 1) {
            for (int i = 1; i < sortStringArray.length; i++) {
                String[] afterZeroSingleSortStrinArray = sortStringArray[0].trim().split(" ");
                switch (afterZeroSingleSortStrinArray[1]) {
                    case "ASC":
                        sortFieldContext.andByField(afterZeroSingleSortStrinArray[0]).asc();
                        break;
                    case "DESC":
                        sortFieldContext.andByField(afterZeroSingleSortStrinArray[0]).desc();
                        break;
                    default:
                        return null;
                }
            }
        }

        return sortFieldContext.createSort();
    }

    /**
     * این متد ستی از نام انتیتی ها دریافت میکند و ایندکس آنها را ایجاد میکند اگر ست خالی ارسال شود کل انتیتیها را ایندکس میکند
     * @param entitySet ستی از کلاس هایی که میخواهیم ایندکس شوند
     * @return خروجی: مقدار true برمیگرداند
     * @throws Exception این متد ممکن است اکسپشن صادر کند
     */
    @Override
    public PrimitiveResponse rebuildIndexer(Set<String> entitySet) throws Exception {
        Set<Class> classSet = new HashSet<>();
        // اگر لیست نام کلاس انتیتی خالی نباشد.
        if (!ObjectUtils.isEmpty(entitySet)) {

            String basePersistencePackageAddress = this.getClass().getName();
            int basePosition = StringUtils.ordinalIndexOf(basePersistencePackageAddress, ".", 2);
            basePersistencePackageAddress = basePersistencePackageAddress.substring(0, basePosition + 1)+"login.persistence.orm.";


            for (String entityNameString : entitySet) {
                entityNameString = entityNameString.substring(0, 1).toUpperCase() + entityNameString.substring(1);
                Class classClass = Class.forName(basePersistencePackageAddress + entityNameString.toLowerCase().trim() + "." + entityNameString.trim());
                classSet.add(classClass);
            }
        }
        MassIndexer massIndexer = null;
        //کلاسها مشخص شده است
        if (CollectionUtils.isNotEmpty(classSet)) {
            massIndexer = fullTextEntityManager.createIndexer(classSet.toArray(new Class[classSet.size()]));

        }
        // همه کلاسها را در نظر بگیر
        else {
            massIndexer = fullTextEntityManager.createIndexer();
        }
        massIndexer
                .purgeAllOnStart(true)
                .batchSizeToLoadObjects(100)
                .typesToIndexInParallel(2)
                .idFetchSize(100)
                .threadsToLoadObjects(1)
                .startAndWait();

        return new PrimitiveResponse(true);
    }

}

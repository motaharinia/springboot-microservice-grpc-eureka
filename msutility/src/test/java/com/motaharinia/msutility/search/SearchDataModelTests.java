package com.motaharinia.msutility.search;

import com.motaharinia.msutility.customfield.CustomDate;
import com.motaharinia.msutility.search.data.SearchDataModel;
import com.motaharinia.msutility.search.filter.*;
import com.motaharinia.msutility.search.sample.SearchRowViewAdminUserBrief;
import com.motaharinia.msutility.search.sample.SearchRowViewAdminUserBriefImpl;
import org.junit.jupiter.api.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-14<br>
 * Time: 20:49:58<br>
 * Description:<br>
 * کلاس تست SearchDataModel
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SearchDataModelTests {

    /**
     * این متد مقادیر پیش فرض قبل از هر تست این کلاس تست را مقداردهی اولیه میکند
     */
    @BeforeEach
    void initUseCase() {
        Locale.setDefault(new Locale("fa", "IR"));
    }

    /**
     * این متد بعد از هر تست این کلاس اجرا میشود
     */
    @AfterEach
    void finalizeEach() {
        Locale.setDefault(Locale.US);
    }

    @Order(1)
    @Test
    void serializeTest() throws ClassNotFoundException {
        try {
            CustomDate dateOfBirth=new CustomDate();
            dateOfBirth.setYear(1399);
            dateOfBirth.setMonth(4);
            dateOfBirth.setDay(3);


            Locale.setDefault(new Locale("fa", "IR"));
            List<SearchFilterRestrictionModel> searchFilterRestrictionModelList = new ArrayList<>();
            searchFilterRestrictionModelList.add(new SearchFilterRestrictionModel("firstName", SearchFilterOperationEnum.MATCH, "mostafa",SearchFilterNextConditionOperatorEnum.AND));
            searchFilterRestrictionModelList.add(new SearchFilterRestrictionModel("dateOfBirth", SearchFilterOperationEnum.GREATER_THAN, dateOfBirth,SearchFilterNextConditionOperatorEnum.AND));
            List<SearchFilterSortModel> searchFilterSortModelList = new ArrayList<>();
            searchFilterSortModelList.add(new SearchFilterSortModel("firstName", SearchFilterSortTypeEnum.ASC));
            searchFilterSortModelList.add(new SearchFilterSortModel("lastName", SearchFilterSortTypeEnum.DSC));
            SearchFilterModel searchFilterModel = new SearchFilterModel();
            searchFilterModel.setPage(1);
            searchFilterModel.setRows(10);
            searchFilterModel.setRestrictionList(searchFilterRestrictionModelList);
            searchFilterModel.setSortList(searchFilterSortModelList);

            SearchRowViewAdminUserBriefImpl searchRowViewTest = null;
            List<SearchRowViewAdminUserBrief> viewList = new ArrayList<>();
            viewList.add(new SearchRowViewAdminUserBriefImpl(1));
            viewList.add(new SearchRowViewAdminUserBriefImpl(2));
            Page<SearchRowViewAdminUserBrief> viewPage = new PageImpl<>(viewList);


            SearchDataModel searchDataModel = new SearchDataModel(viewPage, searchFilterModel,SearchRowViewAdminUserBrief.class, "");
            System.out.println("searchDataModel.toString():" + searchDataModel.toString());

//            String json = mapper.writeValueAsString(searchDataModel);
            assertThat(searchDataModel.getRecords()).isNotEqualTo(null);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }
}

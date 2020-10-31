package com.motaharinia.msjpautility.search;

import com.motaharinia.msjpautility.search.filter.SearchFilterSortModel;
import com.motaharinia.msjpautility.search.sample.SearchRowViewAdminUserBriefImpl;
import com.motaharinia.msutility.customfield.CustomDate;
import com.motaharinia.msjpautility.search.data.SearchDataModel;
import com.motaharinia.msjpautility.search.sample.SearchRowViewAdminUserBrief;
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
            List<com.motaharinia.msjpautility.search.filter.SearchFilterRestrictionModel> searchFilterRestrictionModelList = new ArrayList<>();
            searchFilterRestrictionModelList.add(new com.motaharinia.msjpautility.search.filter.SearchFilterRestrictionModel("firstName", com.motaharinia.msjpautility.search.filter.SearchFilterOperationEnum.MATCH, "mostafa", com.motaharinia.msjpautility.search.filter.SearchFilterNextConditionOperatorEnum.AND));
            searchFilterRestrictionModelList.add(new com.motaharinia.msjpautility.search.filter.SearchFilterRestrictionModel("dateOfBirth", com.motaharinia.msjpautility.search.filter.SearchFilterOperationEnum.GREATER_THAN, dateOfBirth, com.motaharinia.msjpautility.search.filter.SearchFilterNextConditionOperatorEnum.AND));
            List<com.motaharinia.msjpautility.search.filter.SearchFilterSortModel> searchFilterSortModelList = new ArrayList<>();
            searchFilterSortModelList.add(new com.motaharinia.msjpautility.search.filter.SearchFilterSortModel("firstName", com.motaharinia.msjpautility.search.filter.SearchFilterSortTypeEnum.ASC));
            searchFilterSortModelList.add(new SearchFilterSortModel("lastName", com.motaharinia.msjpautility.search.filter.SearchFilterSortTypeEnum.DSC));
            com.motaharinia.msjpautility.search.filter.SearchFilterModel searchFilterModel = new com.motaharinia.msjpautility.search.filter.SearchFilterModel();
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

package com.motaharinia.msjpautility.search;

import com.motaharinia.msjpautility.search.filter.SearchFilterSortModel;
import com.motaharinia.msutility.json.CustomObjectMapper;
import org.junit.jupiter.api.*;

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
 * کلاس تست SearchFilterModel
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SearchFilterModelTests {

    private final CustomObjectMapper mapper = new CustomObjectMapper();

    /**
     * این متد مقادیر پیش فرض قبل از هر تست این کلاس تست را مقداردهی اولیه میکند
     */
    @BeforeEach
    void initUseCase() {
        Locale.setDefault(Locale.US);
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
    void serializeDeserializeTest() {
        try {
            Locale.setDefault(new Locale("fa", "IR"));

            List<com.motaharinia.msjpautility.search.filter.SearchFilterRestrictionModel> searchFilterRestrictionModelList = new ArrayList<>();
            searchFilterRestrictionModelList.add(new com.motaharinia.msjpautility.search.filter.SearchFilterRestrictionModel("firstName", com.motaharinia.msjpautility.search.filter.SearchFilterOperationEnum.MATCH, "mostafa", com.motaharinia.msjpautility.search.filter.SearchFilterNextConditionOperatorEnum.AND));
            List<com.motaharinia.msjpautility.search.filter.SearchFilterSortModel> searchFilterSortModelList = new ArrayList<>();
            searchFilterSortModelList.add(new SearchFilterSortModel("lastName", com.motaharinia.msjpautility.search.filter.SearchFilterSortTypeEnum.ASC));
            com.motaharinia.msjpautility.search.filter.SearchFilterModel searchFilterModel = new com.motaharinia.msjpautility.search.filter.SearchFilterModel();
            searchFilterModel.setPage(1);
            searchFilterModel.setRows(10);
            searchFilterModel.setRestrictionList(searchFilterRestrictionModelList);
            searchFilterModel.setSortList(searchFilterSortModelList);

            String json = mapper.writeValueAsString(searchFilterModel);
            com.motaharinia.msjpautility.search.filter.SearchFilterModel searchFilterModel2 = mapper.readValue(json, com.motaharinia.msjpautility.search.filter.SearchFilterModel.class);
            assertThat(searchFilterModel2.getRestrictionList().get(0).getFieldValue()).isEqualTo("mostafa");
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }
}

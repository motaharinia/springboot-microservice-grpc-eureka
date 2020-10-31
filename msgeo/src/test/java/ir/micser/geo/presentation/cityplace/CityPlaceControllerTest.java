package ir.micser.geo.presentation.cityplace;


import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.json.CustomObjectMapper;
import com.motaharinia.msjpautility.search.data.SearchDataModel;
import com.motaharinia.msjpautility.search.filter.*;
import com.motaharinia.msutility.string.RandomGenerationTypeEnum;
import com.motaharinia.msutility.string.StringTools;
import ir.micser.geo.business.service.cityplace.CityPlaceSearchViewTypeEnum;
import ir.micser.geo.persistence.orm.etcitem.EtcItemInitialData;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *  کلاس تست ماژول لوکیشن شهری
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CityPlaceControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private EtcItemInitialData etcItemInitialData;

    /**
     * شیی crud
     */
    private static Integer crudId=1;
    private static String random;

    CustomObjectMapper customObjectMapper = new CustomObjectMapper();

    /**
     * این متد مقادیر پیش فرض قبل از هر تست این کلاس تست را مقداردهی اولیه میکند
     */
    @BeforeEach
    void initUseCase() throws InvocationTargetException, UtilityException, IllegalAccessException, BusinessException {
        Locale.setDefault(new Locale("fa"));
        etcItemInitialData.checkEtcItems();
    }




    @Test
    @Order(1)
    public void create() throws Exception {
        try {
            String uri = "http://localhost:" + port + "/v1/cityPlace";
            Map<String, String> variableHashMap = new HashMap<String, String>();

             random=StringTools.generateRandomString(RandomGenerationTypeEnum.CHARACTER_ALL,5,false);

            CityPlaceModel cityPlaceModel = new CityPlaceModel();
            cityPlaceModel.setTitle("HomePosition "+random);
            cityPlaceModel.setAdminUserId(1);
            cityPlaceModel.setCity_id(1);
            cityPlaceModel.setLatitude("35.791354");
            cityPlaceModel.setLongitude("51.356406");

            // build the request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<CityPlaceModel> entity = new HttpEntity<>(cityPlaceModel, headers);
            ResponseEntity<CityPlaceModel> response = this.restTemplate.exchange(uri, HttpMethod.POST, entity, CityPlaceModel.class);

            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);
            cityPlaceModel=response.getBody();
            assertThat(cityPlaceModel.getTitle()).isEqualTo("HomePosition "+random);
            crudId = cityPlaceModel.getId();


        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Test
    @Order(2)
    public void readById() {
        try {
            String uri = "http://localhost:" + port + "/v1/cityPlace/" + crudId;

            // build the request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<CityPlaceModel> response = this.restTemplate.exchange(uri,HttpMethod.GET,entity, CityPlaceModel.class);

            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);
            CityPlaceModel cityPlaceModel=response.getBody();
            assertThat(cityPlaceModel.getId()).isEqualTo(crudId);

        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Test
    @Order(3)
    public void readGrid() {
        try {
            String uri = "http://localhost:" + port + "/v1/cityPlace";

            if(ObjectUtils.isEmpty(random)){
                random="skill";
            }

            List<String> titleList = new ArrayList<>();
            titleList.add("HomePosition");
            titleList.add("WorkPosition");
            titleList.add("ParkPosition");

            List<SearchFilterRestrictionModel> searchFilterRestrictionModelList = new ArrayList<>();
            searchFilterRestrictionModelList.add(new SearchFilterRestrictionModel("title", SearchFilterOperationEnum.MATCH, "HomePosition",SearchFilterNextConditionOperatorEnum.AND));
//            searchFilterRestrictionModelList.add(new SearchFilterRestrictionModel("title", SearchFilterOperationEnum.IN, titleList,SearchFilterNextConditionOperatorEnum.AND));
            List<SearchFilterSortModel> searchFilterSortModelList = new ArrayList<>();
            searchFilterSortModelList.add(new SearchFilterSortModel("title", SearchFilterSortTypeEnum.ASC));
            SearchFilterModel searchFilterModel = new SearchFilterModel();

            searchFilterModel.setPage(0);
            searchFilterModel.setRows(20);
            searchFilterModel.setRestrictionList(searchFilterRestrictionModelList);
            searchFilterModel.setSortList(searchFilterSortModelList);


            uri += "?searchFilterModel={searchFilterModel}&searchViewTypeEnum={searchViewTypeEnum}&searchValueList={searchValueList}";

            // build the request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<SearchFilterModel> entity = new HttpEntity<>(searchFilterModel, headers);
            ResponseEntity<SearchDataModel> response = this.restTemplate.exchange(uri, HttpMethod.GET, entity, SearchDataModel.class,customObjectMapper.writeValueAsString(searchFilterModel), CityPlaceSearchViewTypeEnum.CITY_PLACE_BRIEF.toString(), new String[]{});

            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);
            SearchDataModel searchDataModel=response.getBody();
            assertThat(searchDataModel.getPage()).isEqualTo(searchFilterModel.getPage());

        } catch (Exception ex) {
            fail(ex.toString());
        }
    }



    @Test
    @Order(4)
    public void update() throws Exception {
        try {
            String uri = "http://localhost:" + port + "/v1/cityPlace";
            //جستجوی لوکیشن شهری  جهت ویرایش
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<CityPlaceModel> response = this.restTemplate.exchange(uri+ "/"+crudId,HttpMethod.GET,entity, CityPlaceModel.class);
            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);
            CityPlaceModel cityPlaceModel=response.getBody();
            assertThat(cityPlaceModel.getId()).isEqualTo(crudId);

            random=StringTools.generateRandomString(RandomGenerationTypeEnum.CHARACTER_ALL,5,false);

            //ویرایش اطلاعات مدل
            cityPlaceModel.setTitle( cityPlaceModel.getTitle() + " updated");
            cityPlaceModel.setLongitude( cityPlaceModel.getLongitude() + " updated");
            cityPlaceModel.setLatitude( cityPlaceModel.getLatitude() + " updated");

            // build the request
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            entity = new HttpEntity<>(cityPlaceModel, headers);
            response = this.restTemplate.exchange(uri, HttpMethod.PUT, entity, CityPlaceModel.class);
            cityPlaceModel =response.getBody();

            assertThat(cityPlaceModel.getTitle()).contains("updated");
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Test
    @Order(5)
    public void delete() throws Exception {
        try {
            String uri = "http://localhost:" + port + "/v1/cityPlace/"+crudId;

            // build the request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<CityPlaceModel> response = this.restTemplate.exchange(uri, HttpMethod.DELETE, entity, CityPlaceModel.class);
            CityPlaceModel cityPlaceModel =response.getBody();

            assertThat(cityPlaceModel.getId()).isEqualTo(crudId);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }
}

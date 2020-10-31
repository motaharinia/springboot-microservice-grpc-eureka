package com.motaharinia.msjpautility.entity;

import com.motaharinia.msjpautility.entity.sample.SampleEntity;
import com.motaharinia.msjpautility.entity.sample.entity.EntityB;
import com.motaharinia.msjpautility.entity.sample.entity.EntityCity;
import com.motaharinia.msjpautility.entity.sample.entity.EntityEparchy;
import com.motaharinia.msjpautility.entity.sample.model.ModelB;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.customexception.UtilityExceptionKeyEnum;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-16<br>
 * Time: 23:27:46<br>
 * Description:<br>
 * کلاس تست EntityTools
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EntityToolsTests {

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
    void checkEntityNullTest() {
        SampleEntity sampleEntity = null;
        try {
            com.motaharinia.msjpautility.entity.EntityTools.checkEntity(sampleEntity, SampleEntity.class, true);
            fail("check null failed");
        } catch (Exception ex) {
            UtilityException utilityException = new UtilityException(SampleEntity.class, UtilityExceptionKeyEnum.CHECK_ENTITY_IS_NULL, "entity");
            assertThat(ex)
                    .isInstanceOf(UtilityException.class)
                    .hasMessage(utilityException.getMessage());
        }
    }

    @Order(2)
    @Test
    void checkEntityNullListTest() {
        List<SampleEntity> sampleEntityList = null;
        try {
            com.motaharinia.msjpautility.entity.EntityTools.checkEntity(sampleEntityList, SampleEntity.class, true);
            fail("check null failed");
        } catch (Exception ex) {
            UtilityException utilityException = new UtilityException(SampleEntity.class, UtilityExceptionKeyEnum.CHECK_ENTITY_IS_NULL, "entity");
            assertThat(ex)
                    .isInstanceOf(UtilityException.class)
                    .hasMessage(utilityException.getMessage());
        }
    }


    @Order(3)
    @Test
    void checkEntityNullListItemTest() {
        List<SampleEntity> sampleEntityList = new ArrayList<>();
        sampleEntityList.add(new SampleEntity(1));
        sampleEntityList.add(null);
        try {
            com.motaharinia.msjpautility.entity.EntityTools.checkEntity(sampleEntityList, SampleEntity.class, true);
            fail("check null failed");
        } catch (Exception ex) {
            UtilityException utilityException = new UtilityException(SampleEntity.class, UtilityExceptionKeyEnum.CHECK_ENTITY_IS_NULL, "entityOfCollection");
            assertThat(ex)
                    .isInstanceOf(UtilityException.class)
                    .hasMessage(utilityException.getMessage());
        }
    }

    @Order(4)
    @Test
    void checkEntityInvalidTest() {
        SampleEntity sampleEntity = new SampleEntity(1);
        sampleEntity.setInvalid(true);
        try {
            com.motaharinia.msjpautility.entity.EntityTools.checkEntity(sampleEntity, SampleEntity.class, true);
            fail("check invalid failed");
        } catch (Exception ex) {
            UtilityException utilityException = new UtilityException(SampleEntity.class, UtilityExceptionKeyEnum.CHECK_ENTITY_IS_INVALID, "entity.id:" + sampleEntity.getId().toString());
            assertThat(ex)
                    .isInstanceOf(UtilityException.class)
                    .hasMessage(utilityException.getMessage());
        }
    }

    @Order(5)
    @Test
    void checkEntityInvalidListItemTest() {
        SampleEntity sampleEntity = new SampleEntity(1);
        sampleEntity.setInvalid(true);

        List<SampleEntity> sampleEntityList = new ArrayList<>();
        sampleEntityList.add(sampleEntity);
        try {
            com.motaharinia.msjpautility.entity.EntityTools.checkEntity(sampleEntityList, SampleEntity.class, true);
            fail("check invalid failed");
        } catch (Exception ex) {
            UtilityException utilityException = new UtilityException(SampleEntity.class, UtilityExceptionKeyEnum.CHECK_ENTITY_IS_INVALID, "entityOfCollection.id:" + sampleEntity.getId().toString());
            assertThat(ex)
                    .isInstanceOf(UtilityException.class)
                    .hasMessage(utilityException.getMessage());
        }
    }

    @Order(6)
    @Test
    void convertToModelTest() {
        try {
            EntityB entityB = new EntityB();
            entityB.setId(20);
            entityB.setStringA("test");
            entityB.setDoubleB(10d);
            entityB.setDateOfBorn(new Date());
            entityB.setCustomHtmlString("<html><head></head><body><h1>hello</h1><body></html>");
            EntityCity entityCity = new EntityCity();
            entityCity.setId(80);
            entityCity.setName("tehran");
            EntityEparchy entityEparchy = new EntityEparchy();
            entityEparchy.setId(75);
            entityEparchy.seteName("ep");
            entityCity.setEntityEparchy(entityEparchy);
            entityB.setEntityCity(entityCity);

            ModelB modelB = new ModelB();
            modelB = (ModelB) EntityTools.convertToModel(modelB, entityB);


            assertThat(modelB.getId()).isEqualTo(entityB.getId());
            assertThat(modelB.getStringA()).isEqualTo(entityB.getStringA());
            assertThat(modelB.getDoubleB()).isEqualTo(entityB.getDoubleB());
            Calendar dateOfBornCalendar = Calendar.getInstance();
            dateOfBornCalendar.setTime(entityB.getDateOfBorn());
            assertThat(modelB.getDateOfBorn().getYear()).isEqualTo(dateOfBornCalendar.get(Calendar.YEAR));
            assertThat(modelB.getDateOfBorn().getMonth()).isEqualTo(dateOfBornCalendar.get(Calendar.MONTH) + 1);
            assertThat(modelB.getDateOfBorn().getDay()).isEqualTo(dateOfBornCalendar.get(Calendar.DAY_OF_MONTH));
            assertThat(modelB.getCustomHtmlString()).isNotNull();
            assertThat(modelB.getCustomHtmlString().getCustomHtmlString()).isEqualTo(entityB.getCustomHtmlString());
            assertThat(modelB.getEntityCity_id()).isEqualTo(entityB.getEntityCity().getId());
            assertThat(modelB.getEntityCity_entityEparchy_eName()).isEqualTo(entityB.getEntityCity().getEntityEparchy().geteName());

        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

}

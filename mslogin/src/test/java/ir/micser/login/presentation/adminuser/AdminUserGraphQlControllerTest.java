package ir.micser.login.presentation.adminuser;

import com.motaharinia.msutility.string.RandomGenerationTypeEnum;
import com.motaharinia.msutility.string.StringTools;
import graphql.Assert;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLRuntime;
import ir.micser.login.business.service.adminuser.AdminUserService;
import ir.micser.login.business.service.fso.FsoService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminUserGraphQlControllerTest {

    @Autowired
    AdminUserService adminUserService;

    @Autowired
    FsoService fsoService;

    private static Integer crudId = 8;

    GraphQLSchema schema = null;
    GraphQL exe = null;

    @BeforeEach
    public void beforeEach() {
        //AdminUserControllerایجاد اسکیما برای تولید دستورات گراف کیوال در کنترلر
        schema = new AdminUserGraphQlSchemaGenerator()
                .withOperationsFromSingleton(new AdminUserGraphQlController(adminUserService, fsoService))
                .generate();
        exe = GraphQLRuntime.newGraphQL(schema).build();
    }


    @Test
    @Order(1)
    public void create() {

        String random = StringTools.generateRandomString(RandomGenerationTypeEnum.CHARACTER_ALL, 5, false);

        String query = "mutation {create(adminUserModel: {firstName: \"" + "Mostafa " + random + "\" lastName: \"" + "Motaharinia " + random + "\" username: \"" + "eng.motahari_" + random + "@gmail.com" + "\" gender_id: 1 defaultAdminUserContact_city_id :1 }) {id username}}";
        ExecutionResult result = exe.execute(query);
        System.out.println("result :: " + result);
        Assert.assertNotNull(result.getData());
        crudId = (Integer) ((Map<String, Map<String, Object>>) result.getData()).get("create").get("id");
        Assert.assertNotNull(crudId);
    }

    @Test
    @Order(2)
    public void readById() {
        String query = "{common_adminUser_readById(id: " + crudId + ") {username firstName lastName}}";
        ExecutionResult result = exe.execute(query);
        System.out.println("result :: " + result);
        Assert.assertNotNull(result.getData());
    }

    @Test
    @Order(3)
    public void readGrid() {
        //String query = "{readGrid(searchFilterModelJson: \"{\\\"searchRowView\\\":\\\"com.motaharinia.business.service.adminuser.SearchRowViewAdminUserBrief\\\",\\\"restrictionList\\\":[{\\\"fieldName\\\":\\\"firstName\\\",\\\"fieldOperation\\\":\\\"MATCH\\\",\\\"fieldValue\\\":\\\"mostafa\\\",\\\"nextConditionOperator\\\":\\\"AND\\\"},{\\\"fieldName\\\":\\\"defaultAdminUserContact.address\\\",\\\"fieldOperation\\\":\\\"MATCH\\\",\\\"fieldValue\\\":\\\"Shahrak Gharb\\\",\\\"nextConditionOperator\\\":\\\"AND\\\"}],\\\"sortList\\\":[{\\\"fieldName\\\":\\\"firstName\\\",\\\"type\\\":\\\"ASC\\\"},{\\\"fieldName\\\":\\\"lastName\\\",\\\"type\\\":\\\"DSC\\\"},{\\\"fieldName\\\":\\\"defaultAdminUserContact.address\\\",\\\"type\\\":\\\"DSC\\\"},{\\\"fieldName\\\":\\\"gender.value\\\",\\\"type\\\":\\\"DSC\\\"}],\\\"page\\\":0,\\\"rows\\\":20}\"){page records searchDataColModelList {index name} searchDataRowModelList {id rowCellArray} total userData}}";
        String query = "{readGrid(searchViewTypeEnum: ADMIN_USER_BRIEF searchFilterModelJson: \"{\\\"restrictionList\\\":[{\\\"fieldName\\\":\\\"firstName\\\",\\\"fieldOperation\\\":\\\"MATCH\\\",\\\"fieldValue\\\":\\\"mostafa\\\",\\\"nextConditionOperator\\\":\\\"AND\\\"},{\\\"fieldName\\\":\\\"defaultAdminUserContact.address\\\",\\\"fieldOperation\\\":\\\"MATCH\\\",\\\"fieldValue\\\":\\\"Shahrak Gharb\\\",\\\"nextConditionOperator\\\":\\\"AND\\\"}],\\\"sortList\\\":[{\\\"fieldName\\\":\\\"firstName\\\",\\\"type\\\":\\\"ASC\\\"},{\\\"fieldName\\\":\\\"lastName\\\",\\\"type\\\":\\\"DSC\\\"},{\\\"fieldName\\\":\\\"defaultAdminUserContact.address\\\",\\\"type\\\":\\\"DSC\\\"},{\\\"fieldName\\\":\\\"gender.value\\\",\\\"type\\\":\\\"DSC\\\"}],\\\"page\\\":0,\\\"rows\\\":20}\"){page records searchDataColModelList {index name} searchDataRowModelList {id rowCellArray} total userData}}";
        ExecutionResult result = exe.execute(query);
        System.out.println("result :: " + result);
        Assert.assertNotNull(result.getData());
    }

    @Test
    @Order(4)
    public void update() {
        String random = StringTools.generateRandomString(RandomGenerationTypeEnum.CHARACTER_ALL, 5, false) + "updated";
        String query = "mutation {update(adminUserModel: { id:"+crudId+ " firstName: \"" + "Mostafa " + random + "\" lastName: \"" + "Motaharinia " + random + "\" username: \"" + "eng.motahari_" + random + "@gmail.com" + "\" password:\"123456\" defaultAdminUserContact_address: \"Tehran updated\" skillList: {} gender_id: 1 }) {id username}}";
        ExecutionResult result = exe.execute(query);
        System.out.println("result :: " + result);
        Assert.assertNotNull(result.getData());
    }

    @Test
    @Order(5)
    public void delete() {

        String query = "mutation {delete(id: 121) {id username}}";
        ExecutionResult result = exe.execute(query);
        System.out.println("result :: " + result);
        Assert.assertNotNull(result.getData());
    }

}

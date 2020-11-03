package ir.micser.login.graphql;

import graphql.Assert;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLRuntime;
import ir.micser.login.business.service.adminuser.AdminUserService;
import ir.micser.login.business.service.fso.FsoUploadedFileService;
import ir.micser.login.presentation.adminuser.AdminUserController;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminUserControllerTest {

    @Autowired
   AdminUserService adminUserService;

    @Autowired
    FsoUploadedFileService fsoUploadedFileService;

    GraphQLSchema schema =null;
    GraphQL exe = null;

    @BeforeEach
    public void beforeEach(){
        //AdminUserControllerایجاد اسکیما برای تولید دستورات گراف کیوال در کنترلر
        schema = new TestSchemaGenerator()
                .withOperationsFromSingleton(new AdminUserController(adminUserService, fsoUploadedFileService))
                .generate();
        exe = GraphQLRuntime.newGraphQL(schema).build();
    }

    @Test
    public void readById() {
        String query = "{common_adminUser_readById(id: 1) {username firstName lastName}}";
        ExecutionResult result = exe.execute(query);
        System.out.println("result :: " + result);
        Assert.assertNotNull(result.getData());
    }

    @Test
    public void create() {

        String query = "mutation {create(adminUserModel: {firstName: \"Maryam\" lastName: \"Azish\" username: \"hello123@gmail.com\" gender_id: 2 defaultAdminUserContact_city_id :1 }) {id username}}";
        ExecutionResult result = exe.execute(query);
        System.out.println("result :: " + result);
        Assert.assertNotNull(result.getData());
    }

    @Test
    public void update() {

        String query = "mutation {update(adminUserModel: { id:1 firstName: \"محمود\" lastName:\"آزیش\" username:\"sb2@gmail.com\" password:\"123456\" dateOfBirth: {day:20 month: 6 year: 1399} defaultAdminUserContact_address: \"Tehran NaziAbad\" skillList: {} gender_id: 1 }) {id username}}";
        ExecutionResult result = exe.execute(query);
        System.out.println("result :: " + result);
        Assert.assertNotNull(result.getData());
    }

    @Test
    public void delete() {

        String query = "mutation {delete(id: 121) {id username}}";
        ExecutionResult result = exe.execute(query);
        System.out.println("result :: " + result);
        Assert.assertNotNull(result.getData());
    }

    @Test
    public void readGrid() {
        //String query = "{readGrid(searchFilterModelJson: \"{\\\"searchRowView\\\":\\\"com.motaharinia.business.service.adminuser.SearchRowViewAdminUserBrief\\\",\\\"restrictionList\\\":[{\\\"fieldName\\\":\\\"firstName\\\",\\\"fieldOperation\\\":\\\"MATCH\\\",\\\"fieldValue\\\":\\\"mostafa\\\",\\\"nextConditionOperator\\\":\\\"AND\\\"},{\\\"fieldName\\\":\\\"defaultAdminUserContact.address\\\",\\\"fieldOperation\\\":\\\"MATCH\\\",\\\"fieldValue\\\":\\\"Shahrak Gharb\\\",\\\"nextConditionOperator\\\":\\\"AND\\\"}],\\\"sortList\\\":[{\\\"fieldName\\\":\\\"firstName\\\",\\\"type\\\":\\\"ASC\\\"},{\\\"fieldName\\\":\\\"lastName\\\",\\\"type\\\":\\\"DSC\\\"},{\\\"fieldName\\\":\\\"defaultAdminUserContact.address\\\",\\\"type\\\":\\\"DSC\\\"},{\\\"fieldName\\\":\\\"gender.value\\\",\\\"type\\\":\\\"DSC\\\"}],\\\"page\\\":0,\\\"rows\\\":20}\"){page records searchDataColModelList {index name} searchDataRowModelList {id rowCellArray} total userData}}";
        String query = "{readGrid(searchViewTypeEnum: ADMIN_USER_BRIEF searchFilterModelJson: \"{\\\"restrictionList\\\":[{\\\"fieldName\\\":\\\"firstName\\\",\\\"fieldOperation\\\":\\\"MATCH\\\",\\\"fieldValue\\\":\\\"mostafa\\\",\\\"nextConditionOperator\\\":\\\"AND\\\"},{\\\"fieldName\\\":\\\"defaultAdminUserContact.address\\\",\\\"fieldOperation\\\":\\\"MATCH\\\",\\\"fieldValue\\\":\\\"Shahrak Gharb\\\",\\\"nextConditionOperator\\\":\\\"AND\\\"}],\\\"sortList\\\":[{\\\"fieldName\\\":\\\"firstName\\\",\\\"type\\\":\\\"ASC\\\"},{\\\"fieldName\\\":\\\"lastName\\\",\\\"type\\\":\\\"DSC\\\"},{\\\"fieldName\\\":\\\"defaultAdminUserContact.address\\\",\\\"type\\\":\\\"DSC\\\"},{\\\"fieldName\\\":\\\"gender.value\\\",\\\"type\\\":\\\"DSC\\\"}],\\\"page\\\":0,\\\"rows\\\":20}\"){page records searchDataColModelList {index name} searchDataRowModelList {id rowCellArray} total userData}}";
        ExecutionResult result = exe.execute(query);
        System.out.println("result :: " + result);
        Assert.assertNotNull(result.getData());
    }
}

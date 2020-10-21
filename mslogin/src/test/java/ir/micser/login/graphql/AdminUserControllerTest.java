package ir.micser.login.graphql;

import graphql.Assert;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLRuntime;
import ir.micser.login.business.service.adminuser.AdminUserService;
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

    GraphQLSchema schema =null;
    GraphQL exe = null;

    @BeforeEach
    public void beforeEach(){
        schema = new TestSchemaGenerator()
                .withOperationsFromSingleton(new AdminUserController(adminUserService))
                .generate();
        exe = GraphQLRuntime.newGraphQL(schema).build();
    }

    @Test
    public void readById() {

        String query = "{readById(id: 1) {username firstName lastName}}";
        ExecutionResult result = exe.execute(query);

        System.out.println("result :: " + result);

        Assert.assertValidName("username");
    }

    @Test
    public void create() {

        String query = "mutation {create(adminUserModel: { firstName: \"شیرین\" lastName:\"یزرگی\" username:\"mm433@gmail.com\" password:\"123456\" dateOfBirth: {day:20 month: 6 year: 1399} defaultAdminUserContact_address: \"Tehran NaziAbad\" skillList: {title: \"نرم افزار \"} gender_id: 1 }) {id username}}";
        ExecutionResult result = exe.execute(query);

        System.out.println("result :: " + result);

        //Assert.assertTrue("Query result contains unexpected errors: " + result.getErrors(), result.getErrors().isEmpty());
    }

    @Test
    public void update() {

        String query = "mutation {update(adminUserModel: { id:24 firstName: \"محمود\" lastName:\"آزیش\" username:\"mm33@gmail.com\" password:\"123456\" dateOfBirth: {day:20 month: 6 year: 1399} defaultAdminUserContact_address: \"Tehran NaziAbad\" skillList: {} gender_id: 1 }) {id username}}";
        ExecutionResult result = exe.execute(query);

        System.out.println("result :: " + result);

        //Assert.assertTrue("Query result contains unexpected errors: " + result.getErrors(), result.getErrors().isEmpty());
    }

    @Test
    public void delete() {

        String query = "mutation {delete(id: 21) {id username}}";
        ExecutionResult result = exe.execute(query);

        System.out.println("result :: " + result);

        //Assert.assertTrue("Query result contains unexpected errors: " + result.getErrors(), result.getErrors().isEmpty());
    }

    @Test
    public void readGrid() {

        String query = "{readGrid(searchFilterModelJson: \"{\\\"searchRowView\\\":\\\"com.motaharinia.business.service.adminuser.SearchRowViewAdminUserBrief\\\",\\\"restrictionList\\\":[{\\\"fieldName\\\":\\\"firstName\\\",\\\"fieldOperation\\\":\\\"MATCH\\\",\\\"fieldValue\\\":\\\"mostafa\\\",\\\"nextConditionOperator\\\":\\\"AND\\\"},{\\\"fieldName\\\":\\\"defaultAdminUserContact.address\\\",\\\"fieldOperation\\\":\\\"MATCH\\\",\\\"fieldValue\\\":\\\"Shahrak Gharb\\\",\\\"nextConditionOperator\\\":\\\"AND\\\"}],\\\"sortList\\\":[{\\\"fieldName\\\":\\\"firstName\\\",\\\"type\\\":\\\"ASC\\\"},{\\\"fieldName\\\":\\\"lastName\\\",\\\"type\\\":\\\"DSC\\\"},{\\\"fieldName\\\":\\\"defaultAdminUserContact.address\\\",\\\"type\\\":\\\"DSC\\\"},{\\\"fieldName\\\":\\\"gender.value\\\",\\\"type\\\":\\\"DSC\\\"}],\\\"page\\\":0,\\\"rows\\\":20}\"){page records searchDataColModelList {index name} searchDataRowModelList {id rowCellArray} total userData}}";
        ExecutionResult result = exe.execute(query);

        System.out.println("result :: " + result);

        //Assert.assertTrue("Query result contains unexpected errors: " + result.getErrors(), result.getErrors().isEmpty());
    }
}

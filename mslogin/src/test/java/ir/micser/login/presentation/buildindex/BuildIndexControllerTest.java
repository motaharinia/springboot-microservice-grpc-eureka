package ir.micser.login.presentation.buildindex;


import com.motaharinia.msutility.json.PrimitiveResponse;
import ir.micser.login.business.service.adminuser.AdminUserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BuildIndexControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private AdminUserService adminUserService;

    @Test
    @Order(1)
    public void buildIndexTest(){
        try {
            String uri = "http://localhost:" + port + "/v1/buildIndexRun";
            Set<String> entitySet= new HashSet<>();
            entitySet.add("AdminUser");
            // build the request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity entity = new HttpEntity<>(entitySet, headers);;
            ResponseEntity response = this.restTemplate.exchange(uri, HttpMethod.PUT, entity, PrimitiveResponse.class);
            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);
            PrimitiveResponse primitiveResponse = (PrimitiveResponse) response.getBody();
            assertThat(primitiveResponse.getResponse()).isEqualTo(true);
            assertThat(adminUserService.hchCount()).isEqualTo(adminUserService.count());
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Test
    @Order(2)
    public void buildIndexAllTest(){
        try {
            String uri = "http://localhost:" + port + "/v1/buildIndexRun";
            // build the request
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity entity = new HttpEntity<>(new HashSet<>(), headers);;
            ResponseEntity response = this.restTemplate.exchange(uri, HttpMethod.PUT, entity, PrimitiveResponse.class);
            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);
            PrimitiveResponse primitiveResponse = (PrimitiveResponse) response.getBody();
            assertThat(primitiveResponse.getResponse()).isEqualTo(true);
            assertThat(adminUserService.hchCount()).isEqualTo(adminUserService.count());
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }
}

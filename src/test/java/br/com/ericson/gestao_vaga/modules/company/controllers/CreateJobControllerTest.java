package br.com.ericson.gestao_vaga.modules.company.controllers;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.ericson.gestao_vaga.modules.company.dto.CreateJobDTO;
import br.com.ericson.gestao_vaga.utils.TestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreateJobControllerTest {

        private MockMvc mvc;

        @Autowired
        private WebApplicationContext context;

        @Before
        public void setup() {
                mvc = MockMvcBuilders.webAppContextSetup(context)
                                .apply(SecurityMockMvcConfigurers.springSecurity())
                                .build();
        }

        @Test
        public void should_be_able_to_create_a_new_job() throws Exception {

                var createJobDTO = CreateJobDTO.builder()
                                .benefits("BENEFITS_TEST")
                                .description("DESCRIPTION_TEST")
                                .level("LEVEL_TEST")
                                .build();

                var result = mvc.perform(MockMvcRequestBuilders.post("/company/job/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(TestUtils.objectToJSON(
                                                createJobDTO))
                                .header("Authorization",
                                                TestUtils.generateToken(
                                                                UUID.fromString("b4e3a949-d016-451f-8ece-d8be594d4d29"),
                                                                "secret")))
                                .andExpect(MockMvcResultMatchers.status().isOk());

                System.out.println(result);
        }
}

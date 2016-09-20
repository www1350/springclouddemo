package com.absurd.hello.controller;

import com.absurd.Application;
import com.absurd.hello.eneity.Profit;
import com.absurd.hello.service.ProfitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * Created by Administrator on 2016/9/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//@WebMvcTest(ProfitController.class)
public class ProfitControllerTest {



    @Autowired
    private MockMvc mvc;

    @Autowired
    private TestRestTemplate restTemplate;



    @MockBean
    ProfitService profitService;

    @Test
    public void getProfitByNameTest()
            throws Exception {
        given(this.profitService.getByNameTest("cs"))
                .willReturn("grht");
        this.mvc.perform(get("/profit/namett/cs")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("grht"));

        String body = this.restTemplate.getForObject("/profit/namett/cs", String.class);
        assertThat(body).isEqualTo("grht");
    }


    @Test
    public void getProfitByName()
            throws Exception {
       List<Profit> list = new ArrayList<Profit>();
        list.add(new Profit("cs",972084L,"grht"));
        list.add(new Profit("cs",972084L,"grht"));
        ObjectMapper ob = new ObjectMapper();
        String json = ob.writeValueAsString(list);
        given(this.profitService.getByName("cs"))
                .willReturn(list);
        this.mvc.perform(get("/profit/name/cs")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }
}

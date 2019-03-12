package xyz.tumit.springbootdemo.echodemo.echo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


// refs: https://thepracticaldeveloper.com/2017/07/31/guide-spring-boot-controller-tests/
@RunWith(MockitoJUnitRunner.class)
public class EchoControllerStandaloneModeTest {

    private MockMvc mvc;

    @InjectMocks
    private EchoController controller;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void shouldEcho() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                get("/echo/tumit").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("Hello, tumit");
    }

    @Test
    public void shouldErrorNotFound() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                get("/echo").accept(MediaType.APPLICATION_JSON)).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString()).isEqualTo("");
    }
}

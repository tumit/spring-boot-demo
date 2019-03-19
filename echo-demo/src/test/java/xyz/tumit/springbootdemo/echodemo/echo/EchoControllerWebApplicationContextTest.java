package xyz.tumit.springbootdemo.echodemo.echo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@WebMvcTest(EchoController.class)
public class EchoControllerWebApplicationContextTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void shouldEcho() throws Exception {

        String url = "/echo";

        MockHttpServletResponse response = mvc.perform(get(url).accept(MediaType.APPLICATION_JSON))
                                              .andReturn()
                                              .getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString()).isEqualTo("");
    }
}

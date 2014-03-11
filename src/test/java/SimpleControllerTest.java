import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = SimpleControllerTestConfiguration.class)
public class SimpleControllerTest {
  @Autowired
  WebApplicationContext wac;

  MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc = webAppContextSetup(this.wac).build();
  }


  @Test
  public void testSimple() throws Exception {
    for (int i = 0; i < 1000; i++) {
      MvcResult mvcResult = mockMvc
              .perform(get("/"))
              .andExpect(request().asyncStarted())
              .andExpect(request().asyncResult(CoreMatchers.anything()))
              .andReturn();

      mockMvc.perform(asyncDispatch(mvcResult)).andExpect(content().string("result"));
    }
  }
}

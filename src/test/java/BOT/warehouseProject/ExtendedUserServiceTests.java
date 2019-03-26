package BOT.warehouseProject;

import BOT.warehouseProject.Authentication.Entities.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@AutoConfigureMockMvc

public class ExtendedUserServiceTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/user/0")).
                andDo(print())
                .andExpect(status().isOk());
//                .andExpect(content().string(containsString("Hello World")));11

        int z=3;
    }


}
//    @Autowired
//    public WebApplicationContext context;
//    public MockMvc mockMvc;
//    @Before
//    public void setUp()
//    {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//    @Test
//    public void verify_readAllUserLoadSuccessfully() throws Exception
//    {
//        ResultActions resultActions =  mockMvc.perform(MockMvcRequestBuilders.get("/user"));
////                .andExpect(status().isOk());
//
//        int z =3 ;
////    .andExpect((ResultMatcher) content().contentTypeCompatibleWith("List<User>")
//
////        try {
////            mockMvc.perform(MockMvcRequestBuilders.get("/stockplans/stocks/ABC/")) //line A
////                    .param("planId", "plan123") //line B
////                    .andExpect(status().isOk()) //line C
////                    .andExpect(content().contentType("application/json;-8")); //line D
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//    }
//}
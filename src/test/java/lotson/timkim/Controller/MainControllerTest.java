package lotson.timkim.Controller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class MainControllerTest {

    @Autowired
    private MainController mainController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(mainController).build();
    }

    @Test
    void testHomeController() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/")) // "/" 경로로 GET 요청을 보냄
                .andExpect(MockMvcResultMatchers.status().isOk()) // 응답 상태가 200 OK 인지 확인
                .andExpect(MockMvcResultMatchers.view().name("index")); // 뷰 이름이 "index"인지 확인
    }
    
}
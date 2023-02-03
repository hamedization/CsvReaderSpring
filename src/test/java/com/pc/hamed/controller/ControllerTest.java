package com.pc.hamed.controller;

import com.pc.hamed.dto.SampleDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@WebMvcTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ControllerTest {

    private static String CSV_CONTENT =
            "source,codeListCode,code,displayValue,longDescription,fromDate,toDate,sortingPriority\n" +
            "ZIB,ZIB001,271636001,Polsslag regelmatig,The long description is necessary,1/1/2019,,1\n" +
            "ZIB,ZIB001,61086009,Polsslag onregelmatig,,1/1/2019,,2\n" +
            "ZIB,ZIB001,Type 1,\"Losse harde keutels, zoals noten\",,1/1/2019,,\n" +
            "ZIB,ZIB002,Type 2,\"Als een worst, maar klonterig\",,1/1/2019,,\n" +
            "ZIB,ZIB002,Type 3,\"Als een worst, maar met barstjes aan de buitenkant\",,1/1/2019,,\n" +
            "ZIB,ZIB002,Type 4,\"Als een worst of slang, glad en zacht\",,1/1/2019,,\n" +
            "ZIB,ZIB002,Type 5,Zachte keutels met duidelijke randen,,1/1/2019,,\n" +
            "ZIB,ZIB002,Type 6,Zachte stukjes met gehavende randen,,1/1/2019,,\n" +
            "ZIB,ZIB002,Type 7,Helemaal vloeibaar,,1/1/2019,,\n" +
            "ZIB,ZIB003,307047009,Rectale temperatuur,,1/1/2019,,1";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mvc;

    @BeforeAll
    void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void upload_success() throws Exception {
        MockMultipartFile csvFileMocked = new MockMultipartFile("csvFile", "someCsv.csv", MediaType.TEXT_PLAIN_VALUE, CSV_CONTENT.getBytes());
        mvc.perform(MockMvcRequestBuilders.multipart("/upload")
                        .file(csvFileMocked))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void upload_no_records_failure() throws Exception {
        MockMultipartFile csvFileMocked = new MockMultipartFile("csvFile", "someCsv.csv", MediaType.TEXT_PLAIN_VALUE, "".getBytes());
        mvc.perform(MockMvcRequestBuilders.multipart("/upload")
                        .file(csvFileMocked))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

}

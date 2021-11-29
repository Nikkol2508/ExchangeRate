package nikkol21761.exchangerate.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ExchangeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ExchangeController exchangeController;

    @Test
    void testMainController() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string
                        (containsString("Добавте в строку поиска код валюты (RUB, EUR, GBP, CNY)")));
    }

    @Test
    void testGetGifByExchangeRate() throws Exception {

        this.mockMvc.perform(get("/rub"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void testGetGifByExchangeRateWrong() throws Exception {

        this.mockMvc.perform(get("/jkhglkg"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("https://docs.openexchangerates.org/docs/supported-currencies"));
    }

}

package com.example.core.transfer.api;

import com.example.core.IntegrationTestConfig;
import com.example.core.ObjectMapperHolder;
import com.example.core.transfer.api.dto.TransferTransactionDto;
import com.example.data.model.Player;
import com.example.data.repository.PlayerRepository;
import com.example.data.repository.TransferTransactionRepository;
import com.example.gateways.currency.CurrencyClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class TransactionControllerTest extends IntegrationTestConfig {

    private final static String URI = "/transaction/v1";

    @MockBean
    CurrencyClient currencyClient;

    @Autowired
    TransferTransactionRepository transactionRepository;

    @Autowired
    PlayerRepository playerRepository;

    @BeforeEach
    void setUp() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setSqlScriptEncoding("UTF-8");
        databasePopulator.addScript(new ClassPathResource("/sql/initPlayersAndTeams.sql"));
        databasePopulator.execute(dataSource);
    }

    @Test
    void crateGoodTransaction() throws Exception {
        when(currencyClient.getExchangeRate(any(Currency.class), any(Currency.class))).thenReturn(BigDecimal.valueOf(5.37));

        ResultActions out = mockMvc.perform(
            MockMvcRequestBuilders.post(URI).content(asJsonString(TransactionUtils.createPostTransaction())).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        MockHttpServletResponse response = out.andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        TransferTransactionDto transactionDto = ObjectMapperHolder.instance.readValue(response.getContentAsString(), TransferTransactionDto.class);

        assertEquals(1, transactionDto.getId());
        assertEquals("PLN", transactionDto.getBuyerCurrency());
        assertEquals("GBP", transactionDto.getSellerCurrency());
        assertEquals(3, transactionDto.getBuyerID());
        assertEquals(1, transactionDto.getSellerId());
        assertEquals(BigDecimal.valueOf(5.37), transactionDto.getExchangeRate());
        assertEquals(BigDecimal.valueOf(224790.67), transactionDto.getAmount());
        assertEquals(2, transactionDto.getPlayerId());

        Player player = playerRepository.getById(2L);
        assertTrue(player.getPlayerTeams().stream().anyMatch(team -> team.getId().equals(3L)));
    }
}
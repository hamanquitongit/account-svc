package com.account.app;

import com.account.app.controller.AcctSvcController;
import com.account.app.domain.constants.APIStatusCodes;
import com.account.app.domain.constants.AccountType;
import com.account.app.domain.requests.AccountCreationRequest;
import com.account.app.domain.responses.AccountCreationResponse;
import com.account.app.service.AccountService;
import com.account.app.service.CustomerInquiryService;
import com.account.app.service.ValidationService;
import com.account.app.service.exceptions.InvalidRequestException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(AcctSvcController.class)
public class AccountSvcTests {
    @MockBean
    private AccountService accountService;

    @MockBean
    private CustomerInquiryService inquiryService;

    @MockBean
    private ValidationService validationService;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testSuccess() throws Exception{
        AccountCreationRequest req = buildRequest();
        Mockito.when(accountService.createAccount(Mockito.any(AccountCreationRequest.class)))
                .thenReturn(buildResponse());

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(req);

        mockMvc.perform(post("/api/v1/account")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest) )
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testMissingEmail() throws Exception {
        AccountCreationRequest req = buildRequest();
        req.setCustomerEmail("");

        Mockito.doThrow(new InvalidRequestException(APIStatusCodes.BAD_REQUEST.getDesc()))
                .when(validationService).validateAccountCreationRequest(Mockito.any(AccountCreationRequest.class));

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonRequest = objectMapper.writeValueAsString(req);

        mockMvc.perform(post("/api/v1/account")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }


    private AccountCreationRequest buildRequest() {
        AccountCreationRequest request = new AccountCreationRequest();
        request.setCustomerName("testname");
        request.setCustomerMobile("0999999999");
        request.setCustomerEmail("test@email.com");
        request.setAddress1("testadd1");
        request.setAddress2("testadd2");
        request.setAccountType(AccountType.S);
        return request;
    }

    private AccountCreationResponse buildResponse() {
        AccountCreationResponse response = new AccountCreationResponse();
        response.setCustomerNumber("1");
        response.setTransactionStatusCode(APIStatusCodes.CREATED.getStatusCode());
        response.setTransactionStatusDescription(APIStatusCodes.CREATED.getDesc());

        return response;
    }
}

package com.challenge1.module.controllers;

import com.challenge1.module.configuration.TestConfig;
import com.challenge1.module.models.AddressRequest;
import com.challenge1.module.services.IAddressService;
import com.challenge1.module.services.impl.LobAddressServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.challenge1.module.utils.TestUtils.BAD_REQUEST_ERROR;
import static com.challenge1.module.utils.TestUtils.MOCKED_SUGGESTION_1;
import static com.challenge1.module.utils.TestUtils.MOCKED_SUGG_2;
import static com.challenge1.module.utils.TestUtils.MULTIPLE_RESULT_REQUEST;
import static com.challenge1.module.utils.TestUtils.ONE_RESULT_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {TestConfig.class, LobAddressController.class, LobAddressServiceImpl.class})
public class LobAddressControllerIntegrationTest {

    @Autowired
    IAddressService addressService;
    @Autowired
    LobAddressController controller;

    @Test
    public void testAutoCompleteAddressMockedSuggestionOneResult_Success() {
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setAddressPrefix(ONE_RESULT_REQUEST);

        ResponseEntity<String> response = controller.autoCompleteAddress(addressRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MOCKED_SUGGESTION_1, response.getBody());
    }

    @Test
    public void testAutoCompleteAddressMockedSuggestionMultipleResult_Success() {
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setAddressPrefix(MULTIPLE_RESULT_REQUEST);

        ResponseEntity<String> response = controller.autoCompleteAddress(addressRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MOCKED_SUGG_2, response.getBody());
    }

    @Test
    public void testAutoCompleteAddressEmptyPrefix_BadRequest() {
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setAddressPrefix("");

        ResponseEntity<String> response = controller.autoCompleteAddress(addressRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(BAD_REQUEST_ERROR, response.getBody());
    }

    @Test
    public void testAutoCompleteAddressNullPrefix_BadRequest() {
        AddressRequest addressRequest = new AddressRequest();

        ResponseEntity<String> response = controller.autoCompleteAddress(addressRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(BAD_REQUEST_ERROR, response.getBody());
    }
}

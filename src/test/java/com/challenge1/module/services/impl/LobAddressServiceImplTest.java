package com.challenge1.module.services.impl;

import com.challenge1.module.facade.IAutocompleteFacade;
import com.challenge1.module.models.AddressRequest;
import com.lob.api.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.challenge1.module.utils.TestUtils.BAD_REQUEST_ERROR;
import static com.challenge1.module.utils.TestUtils.MOCKED_SUGGESTION_1;
import static com.challenge1.module.utils.TestUtils.ONE_RESULT_REQUEST;
import static com.challenge1.module.utils.TestUtils.UNAUTHORIZED_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LobAddressServiceImplTest {

    @Mock
    private IAutocompleteFacade<String, AddressRequest> autocompleteFacade;

    @InjectMocks
    private LobAddressServiceImpl lobAddressService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAutoComplete_WithNonEmptyAddressPrefix_Success() throws ApiException {
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setAddressPrefix(ONE_RESULT_REQUEST);

        when(autocompleteFacade.autoComplete(addressRequest)).thenReturn(MOCKED_SUGGESTION_1);

        ResponseEntity<String> response = lobAddressService.autoComplete(addressRequest);

        verify(autocompleteFacade, times(1)).autoComplete(addressRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MOCKED_SUGGESTION_1, response.getBody());
    }

    @Test
    public void testAutoComplete_WithEmptyAddressPrefix_BadRequest() throws ApiException {
        lobAddressService = new LobAddressServiceImpl(autocompleteFacade);
        AddressRequest addressRequest = new AddressRequest();

        ResponseEntity<String> response = lobAddressService.autoComplete(addressRequest);

        verify(autocompleteFacade, times(0)).autoComplete(addressRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(BAD_REQUEST_ERROR, response.getBody());
    }

    @Test
    public void testAutoComplete_WithBadApiClient_Unauthorized() throws ApiException {
        lobAddressService = new LobAddressServiceImpl(autocompleteFacade);
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setAddressPrefix(ONE_RESULT_REQUEST);

        when(autocompleteFacade.autoComplete(addressRequest)).thenThrow(
                new ApiException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Missing authentication"));

        ResponseEntity<String> response = lobAddressService.autoComplete(addressRequest);

        verify(autocompleteFacade, times(1)).autoComplete(addressRequest);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(UNAUTHORIZED_ERROR, response.getBody());
    }
}

package com.challenge1.module.facade.impl;

import com.challenge1.module.configuration.TestConfig;
import com.challenge1.module.models.AddressRequest;
import com.lob.api.ApiException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.challenge1.module.utils.TestUtils.MOCKED_SUGGESTION_1;
import static com.challenge1.module.utils.TestUtils.MOCKED_SUGG_2;
import static com.challenge1.module.utils.TestUtils.MULTIPLE_RESULT_REQUEST;
import static com.challenge1.module.utils.TestUtils.ONE_RESULT_REQUEST;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = TestConfig.class)
public class LobAutocompleteImplIntegrationTest {

    @Autowired
    LobAutocompleteImpl lobAutocomplete;

    @Test
    public void testAutoCompleteAddressMockedSuggestionOneResult_Success() throws ApiException {
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setAddressPrefix(ONE_RESULT_REQUEST);
        String result = lobAutocomplete.autoComplete(addressRequest);
        assertEquals(MOCKED_SUGGESTION_1, result);
    }

    @Test
    public void testAutoCompleteAddressMockedSuggestionMultipleResult_Success() throws ApiException {
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setAddressPrefix(MULTIPLE_RESULT_REQUEST);
        String result = lobAutocomplete.autoComplete(addressRequest);
        assertEquals(MOCKED_SUGG_2, result);
    }

    @Test
    public void testAutoCompleteAddressNullAddressPrefix_BadRequest() {
        AddressRequest addressRequest = new AddressRequest();
        assertThatThrownBy(() -> lobAutocomplete.autoComplete(addressRequest)).isInstanceOf(ApiException.class);
    }

    @Test
    public void testAutoCompleteAddressEmptyAddressPrefix_BadRequest() {
        AddressRequest addressRequest = new AddressRequest();
        addressRequest.setAddressPrefix("");
        assertThatThrownBy(() -> lobAutocomplete.autoComplete(addressRequest)).isInstanceOf(ApiException.class);
    }
}

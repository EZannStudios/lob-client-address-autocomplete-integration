package com.challenge1.module.facade;

import com.lob.api.ApiException;
import org.springframework.stereotype.Component;

@Component
public interface IAutocompleteFacade<T, S> {
    T autoComplete(S s) throws ApiException;
}

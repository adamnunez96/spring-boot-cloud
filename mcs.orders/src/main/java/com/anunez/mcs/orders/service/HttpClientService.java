package com.anunez.mcs.orders.service;

import com.anunez.mcs.orders.dto.BaseResponse;

public interface HttpClientService {
    BaseResponse sendRequest(Object request);
}

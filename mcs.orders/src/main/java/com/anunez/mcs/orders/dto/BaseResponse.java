package com.anunez.mcs.orders.dto;

import java.util.Arrays;

public record BaseResponse(String[] errors) {

    public boolean hasError() {
        return this.errors != null && this.errors.length > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseResponse that)) return false;
        return Arrays.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(errors);
    }

    @Override
    public String toString() {
        return "BaseResponse{" +
                "errors=" + Arrays.toString(errors) +
                '}';
    }
}

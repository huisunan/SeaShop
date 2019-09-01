package com.rsdtm.supermarket.common.exception;

import com.rsdtm.supermarket.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PtException extends RuntimeException {
    private ExceptionEnum exceptionEnum;
}

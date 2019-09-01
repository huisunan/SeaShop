package com.rsdtm.supermarket.common.advice;


import com.rsdtm.supermarket.common.exception.PtException;
import com.rsdtm.supermarket.common.vo.ExceptionResult;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @Classname CommonExceptionHandler
 * @Description 全局异常处理器
 * @Date 2019/6/21 16:04
 * @Created by huisunan
 */
@ControllerAdvice
public class CommonExceptionHandler {
    @ExceptionHandler(PtException.class)
    public ResponseEntity<ExceptionResult> handleException(PtException e) {
        val em = e.getExceptionEnum();
        return ResponseEntity.status(em.getCode()).body(new ExceptionResult(em));
    }
}

package com.rsdtm.supermarket.common.vo;

import com.rsdtm.supermarket.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.MissingServletRequestParameterException;

/**
 * @Classname ExceptionResult
 * @Description 异常处理结果
 * @Date 2019/6/21 16:06
 * @Created by huisunan
 */
@Data
public class ExceptionResult {
    private int status;
    private String message;
    private Long timestamp;
    public ExceptionResult(ExceptionEnum exceptionEnum) {
        this.status = exceptionEnum.getCode();
        this.message = exceptionEnum.getMsg();
        this.timestamp = System.currentTimeMillis();
    }

}

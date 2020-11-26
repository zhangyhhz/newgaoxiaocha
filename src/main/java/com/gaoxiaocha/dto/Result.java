package com.gaoxiaocha.dto;

import lombok.Data;

/**
 * restful
 *
 * @author zyh
 * @date 2020/5/6
 */
@Data
public class Result<T> {
    private String  msg;
    private boolean isSuccess = false;
    private T       data  = null;

}

package com.example.crmgo.register.utils.resultUtil;

import com.example.crmgo.register.Enum.ResultEnum;
import com.example.crmgo.register.results.Result;

/**
 * @author nantian
 */
public class ResultUtil {
    /** 成功且带数据 **/
    public static Result<Object> success(Object object){
        Result<Object> result = new Result<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    /** 成功且不带数据 **/
    public static Result<Object> success(){
        return success(null);
    }

    /** 失败 **/
    public static Result<Object> error(Integer code,String msg){
        Result<Object> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}

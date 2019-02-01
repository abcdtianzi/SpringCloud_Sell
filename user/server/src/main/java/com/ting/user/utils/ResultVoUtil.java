package com.ting.user.utils;


import com.ting.user.enums.ResultEnum;
import com.ting.user.vo.ResultVo;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: ting
 * Date: 2018-05-19
 * Time: 下午8:35
 */
public class ResultVoUtil {
    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setData(object);
        resultVo.setMsg(ResultEnum.LOGIN_SUCCESS.getMessage());
        resultVo.setCode(ResultEnum.LOGIN_SUCCESS.getCode());
        return resultVo;
    }
    public static ResultVo success(){
        ResultVo resultVo = new ResultVo();
        resultVo.setMsg(ResultEnum.LOGIN_SUCCESS.getMessage());
        resultVo.setCode(ResultEnum.LOGIN_SUCCESS.getCode());
        return resultVo;
    }
    public static ResultVo error(ResultEnum resultEnum){
        ResultVo resultVo = new ResultVo();
        resultVo.setMsg(resultEnum.getMessage());
        resultVo.setCode(resultEnum.getCode());
        return resultVo;
    }
}

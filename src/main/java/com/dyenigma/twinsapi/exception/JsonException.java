package com.dyenigma.twinsapi.exception;

import com.alibaba.fastjson.JSONObject;
import com.dyenigma.twinsapi.util.ErrorEnum;
import com.dyenigma.twinsapi.util.JsonUtil;

/**
 * twins/com.dyenigma.twinsapi.exception
 *
 * @Description :
 * @Author : dingdongliang
 * @Date : 2018/3/11 19:28
 */
public class JsonException extends RuntimeException {
    private JSONObject resultJson;

    /**
     * @param errorEnum 以错误的ErrorEnum做参数
     * @return
     * @Description : 调用时可以在任何代码处直接throws这个Exception,都会统一被拦截,并封装好json返回给前台
     * @author dingdongliang
     * @date
     */
    public JsonException(ErrorEnum errorEnum) {
        this.resultJson = JsonUtil.errorJson(errorEnum);
    }

    public JsonException(JSONObject resultJson) {
        this.resultJson = resultJson;
    }

    public JSONObject getResultJson() {
        return resultJson;
    }
}

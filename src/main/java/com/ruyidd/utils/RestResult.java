package com.ruyidd.utils;

import java.util.HashMap;

public class RestResult extends HashMap<String, Object> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static RestResult success(){
        return success("成功");
    }
    public static RestResult success(String message){
        RestResult restResponse = new RestResult();
        restResponse.setSuccess(true);
        restResponse.setMessage(message);
        return restResponse;
    }

    public static RestResult failure(String message){
        RestResult restResponse = new RestResult();
        restResponse.setSuccess(false);
        restResponse.setMessage(message);
        return restResponse;
    }


    public RestResult setSuccess(Boolean success) {
        if (success != null) put("success", success);
        return this;
    }

    public RestResult setMessage(String message) {
        if (message != null) put("message", message);
        return this;
    }

    public RestResult setData(Object data) {
        if (data != null) put("data", data);
        return this;
    }

    public RestResult setPage(Integer page) {
        if (page != null) put("page", page);
        return this;
    }
    
    public RestResult setCurrentPage(Integer currentPage){
    	if (currentPage != null) put("currentPage", currentPage);
        return this;
    }

    public RestResult setLimit(Integer limit) {
        if (limit != null) put("limit", limit);
        return this;
    }

    public RestResult setTotal(Long total) {
        if (total != null) put("total", total);
        return this;
    }

    public RestResult setAny(String key, Object value) {
        if (key != null && value != null) put(key, value);
        return this;
    }
}

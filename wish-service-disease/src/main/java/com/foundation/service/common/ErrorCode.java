package com.foundation.service.common;

public enum ErrorCode {
    UNKNOWN(-1, "Unknown"),
    SUCCESS(200, "Success"),
    PARAM_FORMAT(1000, "Input parameter format is wrong"),
    QUERY_NOTFOUND(1001, " query not found"),
    JOSN_ERROR(1002, " JSON error"),
    EXCEPTION_ERROR(1003, " Exception json"),
    QUERY_FAILED(2000, "failed to query data from DB"),
    INSERT_FAILED(2001, "failed to insert data to DB"),
    UPDATE_FAILED(2002, "failed to update data record"),
    DELETE_FAILED(2003, "failed to delete record from DB"),
	EXCEPTION_SQL(2004, "failed to delete record from DB"),
    DATA_EXISTED(2005, "The Data has existed");

    private int code;
    private String msg;

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
    
    public static ErrorCode valueOf(int code) {
        for (ErrorCode type : ErrorCode.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }
}
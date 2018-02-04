package com.foundation.common;

/**
 * 系统常量类
 * Created by fqh on 2015/12/11.
 */
public interface Contants {

    //RESULT TIP
    String NEEDLOGIN="{\"data\":{},\"tip\":\"NOTLOGGEDIN\"}";
    String MISMATCHINGPARAM="{\"data\":{},\"tip\":\"参数不齐整\"}";
    String UNAUTHORIZED="{\"data\":{},\"tip\":\"未授权访问\"}";
    String HAVETAMPER ="{\"data\":{},\"tip\":\"不允许访问\"}";

    enum  AllowSystem{
        yjxt("yujian","token_yungu@2016$@#$@!#$#%#sdsfd0-12312fcfe","孕检系统");

        AllowSystem(String systemId,String token,String systemName){
            this.systemId=systemId;
            this.token=token;
            this.systemName=systemName;
        }

        private String systemId;
        private String token;
        private String systemName;

        public String getToken() {
            return token;
        }

        public String getSystemId() {
            return systemId;
        }

        public String getSystemName() {
            return systemName;
        }

        /**
         * 根据系统id获取系统Token
         * @param systemId
         * @return
         */
        public static String getSystemToken(String systemId){
            for(AllowSystem system:AllowSystem.values()){
                if(system.getSystemId().equals(systemId)){
                    return system.getToken();
                }
            }
            return null;
        }

        public static Boolean isContainsSystem(String systemId){
            for(AllowSystem system:AllowSystem.values()){
                if(system.getSystemId().equals(systemId)){
                    return true;
                }
            }
            return false;
        }
    }

}

package com.foundation.service.util;

/**
 * Created by fanqinghui on 2016/9/7.
 * 1第翼信息 2梦网短信，3漫道短信 4亿美软通 5建周短信 6美联软通
 */
public enum SMSProvider {

    DYZX(1, "第翼信息","qinghui","1EE8C65BEEC35298BC809F2FAC04","http://web.1xinxi.cn/asmx/smsservice.aspx?",1),
    MWDX(2, "2梦网短信","","","",0),
    MDDX(3, "漫道短信","","","",0),
    YMRT(4, "亿美软通","","","",0),
    JZDX(5, "建周短信","","","",0),
    MLRT(6, "美联软通","","","",0);

    SMSProvider(Integer id,String providerName,String name,String pwd,String url,Integer status) {
        this.id = id;
        this.providerName=providerName;
        this.name = name;
        this.pwd=pwd;
        this.url=url;
        this.status=status;
    }

    private String providerName;
    private String name;
    private String pwd;
    private Integer id;
    private String url;
    private Integer status;//状态 1可用 0不可用


    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getProviderName() {
        return providerName;
    }

    public String getPwd() {
        return pwd;
    }

    public String getUrl() {
        return url;
    }

    public Integer getStatus() {
        return status;
    }

    public static SMSProvider getProvider(Integer id){
       for(SMSProvider provider:SMSProvider.values()){
           if(provider.getId()==id&&provider.getStatus()==1){
               return provider;
           }
       }
        return null;
    }

    /**
     * 默认第翼咨询
     * @return
     */
    public static SMSProvider getDefault(){
        return SMSProvider.DYZX;
    }
}

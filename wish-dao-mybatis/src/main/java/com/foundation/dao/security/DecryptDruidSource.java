package com.foundation.dao.security;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * 用来解密配置中的密文(重点配置，在这里扩展用户名的解密)
 * setUsername(name) 方法对应xml中的一个property属性，password默认加密不需要重写，
 * 还可以加密url 重写setUrl(url)
 *
 * @fqh
 */
public class DecryptDruidSource {

    public static void main(String[] arugs) {
        //jdbc.username=bNVOqb7WKLX5Bjnw+LMv92taj25KOxDimXxILPQjw42wgv+1lHzOH8kr97xDwWdhpY67QuYCS7sWN4W46YbkFA==
        // jdbc.password=Biyu5YzU+6sxDRbmWEa3B2uUcImzDo0BuXjTlL505+/pTb+/0Oqd3ou1R6J8+9Fy3CYrM18nBDqf6wAaPgUGOg==
        try {
            String userName = ConfigTools.encrypt("fxpgweb");
            String password = ConfigTools.encrypt("XWC5rVXAz5fStyJe");
            System.out.println(userName);
            System.out.println(password);
            //解密
           String JuserName = ConfigTools.decrypt("TbQ2WKvN9UTwkRTnY7szK5bBZYBL5wO/9wcUapgYrlaYOu8DB6gJKy3ErRDWnTG6DfkQ25cAbLAayJF86Yhrdg==");
            String Jpassword = ConfigTools.decrypt("X1CiNU6rdXKubBZbSiI7m9IWI0h5+MncMRnKoFdQT2sGOayD20kMLJVBUIbiisUrb3JpfdiVw2bpv0HChTgHgg==");
            System.out.println(JuserName);
            System.out.println(Jpassword);
//            String pass = ConfigTools.decrypt("d28ca6ba276fe970df2f35ed604780f983a85f995cf4fecce394dcbf");
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
package com.bioeh.sp.hm.wx.common.constant;

/**
 * 数据库字段标识
 * <li>用于引用系统中数据库字段的名称，使其保持一致</li>
 * 
 * @author  CaoHang
 * @version  [版本号, 2015年6月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface DBConstants {
    /** 公共模块 */
    interface Common {
        
        /** 主键ID */
        String PK_ID = "pk_id";

        String ID = "id";
        
        String CREATE_TIME = "create_time";
        
        String UPDATE_TIME = "update_time";
        
        String STATUS = "status";
        
        String CODE = "code";

        String OPEN_ID = "openid";

        String MAC = "mac";

        String DEVICE_ID = "device_id";

        String MOBILE = "mobile";

        String USER_ID = "userid";

        String WX_DEVICE_ID = "wxdeviceid";


    }
    
}

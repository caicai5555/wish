package com.foundation.dao.entity.indicatorSys;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: IndicatorInfoTreeDO
 * @Description: 指标信息父子结构实体
 * @author wangsen
 * @date 2016/11/24 11:26
 * @version V1.0
 */
public class IndicatorTreeDO extends Indicator {

    /**
     * 指标列表
     */
    private List<Indicator> leafIndicator = new ArrayList<Indicator>();

    public List<Indicator> getLeafIndicator() {
        return leafIndicator;
    }

    public void setLeafIndicator(List<Indicator> leafIndicator) {
        this.leafIndicator = leafIndicator;
    }
}
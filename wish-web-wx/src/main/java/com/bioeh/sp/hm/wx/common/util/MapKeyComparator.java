package com.bioeh.sp.hm.wx.common.util;

import java.util.Comparator;

/**
 * Created by zl on 2016/8/16.
 * desc:
 */
public class MapKeyComparator implements Comparator<String> {

    @Override
    public int compare(String str1, String str2) {

        return str1.compareTo(str2);
    }
}
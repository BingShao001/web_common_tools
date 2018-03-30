package com.netcommon.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public final class ArrayUtil {

    private ArrayUtil() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * 按入参size拆分集合
     *
     * @param datas
     * @param splitSize
     * @param <T>
     * @return
     */
    public static <T> List<List<T>> arrayChunk(List<T> datas, int splitSize) {
        if (datas == null || splitSize < 1) {
            return Collections.emptyList();
        }
        int totalSize = datas.size();
        int count = (totalSize % splitSize == 0) ? (totalSize / splitSize) : (totalSize / splitSize + 1);

        List<List<T>> rows = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            List<T> cols = datas.subList(i * splitSize, (i == count - 1) ? totalSize : splitSize * (i + 1));
            rows.add(cols);
        }
        return rows;
    }


    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(JSONObject obj) {
        return obj == null || obj.isEmpty();
    }

}
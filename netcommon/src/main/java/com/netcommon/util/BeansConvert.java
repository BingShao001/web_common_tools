package com.netcommon.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

public class BeansConvert {

    private static final Logger log = LoggerFactory.getLogger(BeansConvert.class);
    /**
     * 实体对象复制
     * @param source 被复制对象
     * @param target 目标对象
     * @param <T>
     * @return
     */
    public static <T> T beansCopy(Object source, T target) {
        // source(被复制对象), target(目标对象)
        if (source != null && target != null) {
            BeanUtils.copyProperties(source, target);
        }else {
            log.error("source(被复制对象), target(目标对象) : 不为空！");
        }
        return target;
    }

}

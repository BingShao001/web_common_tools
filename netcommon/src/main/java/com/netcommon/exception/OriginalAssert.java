package com.netcommon.exception;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

/***
 * 断言扩展自定义
 * @author bing.zhang
 */
public class OriginalAssert extends Assert {
    public static final String BUSINESS_EXCEPTION = "BusinessException";
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(BUSINESS_EXCEPTION);
    /***
     * 当出入的逻辑判断为true时抛出异常符合中国人思维
     * @param expression
     * @param errorMsg
     */
    public static void isRealTrue(boolean expression,ErrorMsg errorMsg) throws BusinessException {
        if (expression) {
            throw new BusinessException(errorMsg.getErrorCode(), errorMsg.getErrorMsg());
        }
    }

    /***
     * 当出入的逻辑判断为true时抛出异常符合中国人思维
     * @param expression
     * @param errorMsg
     * @param businessErrorGroup
     */
    public static void isRealTrue(boolean expression,ErrorMsg errorMsg,String businessErrorGroup) throws BusinessException {
        if (expression) {
            throw new BusinessException(errorMsg.getErrorCode(), errorMsg.getErrorMsg(), businessErrorGroup);
        }
    }

    /***
     * 当出入的逻辑判断为false时抛出异常符合中国人思维
     * @param expression
     * @param errorMsg
     */
    public static void isRealFalse(boolean expression, ErrorMsg errorMsg) throws BusinessException {
        if (!expression) {
            throw new BusinessException(errorMsg.getErrorCode(), errorMsg.getErrorMsg());
        }
    }

    /***
     * 当出入的逻辑判断为false时抛出异常符合中国人思维
     * @param expression
     * @param errorMsg
     * @param businessErrorGroup
     */
    public static void isRealFalse(boolean expression, ErrorMsg errorMsg, String businessErrorGroup) throws BusinessException {
        if (!expression) {
            throw new BusinessException(errorMsg.getErrorCode(), errorMsg.getErrorMsg(),businessErrorGroup);
        }
    }

    /***
     * 当出入的对象判断为null时抛出异常符合中国人思维
     * @param object
     * @param errorMsg
     */
    public static void isRealNull(Object object, ErrorMsg errorMsg) throws BusinessException {
        if (object == null) {
            throw new BusinessException(errorMsg.getErrorCode(), errorMsg.getErrorMsg());
        }
    }
    /***
     * 当出入的对象判断为null时抛出异常符合中国人思维
     * @param object
     * @param errorMsg
     * @param businessErrorGroup
     */
    public static void isRealNull(Object object, ErrorMsg errorMsg,String businessErrorGroup) throws BusinessException {
        if (object == null) {
            throw new BusinessException(errorMsg.getErrorCode(), errorMsg.getErrorMsg(),businessErrorGroup);
        }
    }

    /***
     * 当出入的集合判断为null时抛出异常符合中国人思维
     * @param collection
     * @param errorMsg
     */
    public static void isEmpty(Collection<?> collection,ErrorMsg errorMsg) throws BusinessException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BusinessException(errorMsg.getErrorCode(), errorMsg.getErrorMsg());
        }
    }
    /***
     * 当出入的集合判断为null时抛出异常符合中国人思维
     * @param collection
     * @param errorMsg
     */
    public static void isEmpty(Collection<?> collection, ErrorMsg errorMsg,String businessErrorGroup) throws BusinessException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BusinessException(errorMsg.getErrorCode(), errorMsg.getErrorMsg(),businessErrorGroup);
        }
    }

    /**
     * 判断字符串是否为空
     * @param string
     * @param errorMsg
     * @throws BusinessException
     */
    public static void isStringEmpty(String string, ErrorMsg errorMsg) throws BusinessException {
        if (StringUtils.isBlank(string)) {
            throw new BusinessException(errorMsg.getErrorCode(), errorMsg.getErrorMsg());
        }
    }
    /**
     * 判断字符串是否为空
     * @param string
     * @param errorMsg
     * @throws BusinessException
     */
    public static void isStringEmpty(String string, ErrorMsg errorMsg, String businessErrorGroup) throws BusinessException {
        if (StringUtils.isBlank(string)) {
            throw new BusinessException(errorMsg.getErrorCode(), errorMsg.getErrorMsg(), businessErrorGroup);
        }
    }

    /**
     * 记录日志并抛出业务异常
     * @param functionName
     * @param e
     * @throws BusinessException
     */
    public static void throwBusinessException(String functionName, Exception e) throws BusinessException {
        logger.info(functionName + " Exception:{}", JSON.toJSON(e));
        String businessName = e.getClass().getSimpleName();
        //e.printStackTrace();
        if (BUSINESS_EXCEPTION.equals(businessName)){
            throw (BusinessException)e;
        }
        throw new BusinessException(ErrorMsg.SYSTEM_ERROR.getErrorCode(), ErrorMsg.SYSTEM_ERROR.getErrorMsg());
    }
}


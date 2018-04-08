package com.netcommon.util;


import java.util.Random;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
* 主键生成工具
* @author bing
* @create 2018/3/16
* @version 1.0
**/
public class KeyGenerate {
    public static final int MIN_NUM = 10000;
    public static final int BOUND = 99999;
    private static Lock lock = new ReentrantLock();
    /***
     * UUID
     * @return
     */
    public String generateStringKey() {
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        uuidStr = uuidStr.replace("-", "");
        return uuidStr;
    }

    /**
     * 数据值key
     * @return
     */
    public static String generateLongKey() {
        return Long.toString(System.currentTimeMillis()+getRandomNum());
    }
    private static long getRandomNum() {
        Random random = new Random();
        int randomNum = 0;
        try {
            lock.lock();
            randomNum = random.nextInt(BOUND);
            if (randomNum < MIN_NUM) {
                randomNum = randomNum + MIN_NUM;
            }
        }finally {
            lock.unlock();
        }
        return randomNum;
    }
}

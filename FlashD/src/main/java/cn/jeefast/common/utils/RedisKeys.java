package cn.jeefast.common.utils;

/**
 *
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }
}

package com.junfly.water;

/**
 * 全局常用信息
 *
 * @Author: pq
 * @Description:
 * @Date: 2017/3/17 13:52
 */
public class Constant {

    public static final String PRE_KEY = "env_admin:";

    public static final String SEPARATOR = ":";
    /**
     * 水厂名称KEY
     */
    public static final String WATER_WORK_KEY = PRE_KEY + SEPARATOR + "water_work:";
    /**
     * 水位告警KEY
     */
    public static final String WATER_WARN_KEY = PRE_KEY + SEPARATOR + "water_warn:";
    /**
     * 最后一次告警KEY
     */
    public static final String LAST_WARN_TIME_KEY = PRE_KEY + SEPARATOR + "last_warn_time:";
    /**
     * 微信告警模版CODE
     */
    public static final String WEICHAT_TEMPLATE_CODE = "water_level";
    /**
     * 水位col
     */
    public static final String WATER_COL_1 = "water_level_1";
    public static final String WATER_COL_2 = "water_level_2";
    public static final String WATER_COL_3 = "water_level_3";
    public static final String WATER_COL_4 = "water_level_4";
    public static final String WATER_COL_5 = "water_level_5";
    public static final String WATER_COL_6 = "water_level_6";
    public static final String WATER_COL_7 = "water_level_7";
    public static final String WATER_COL_8 = "water_level_8";
    public static final String WATER_COL_9 = "water_level_9";
    public static final String WATER_COL_10 = "water_level_10";
    public static final String WATER_COL_11 = "water_level_11";
    public static final String WATER_COL_12 = "water_level_12";
    public static final String WATER_COL_13 = "water_level_13";
    public static final String WATER_COL_14 = "water_level_14";
    public static final String WATER_COL_15 = "water_level_15";
    public static final String WATER_COL_16 = "water_level_16";
    public static final String WATER_COL_17 = "water_level_17";
    public static final String WATER_COL_18 = "water_level_18";
    public static final String WATER_COL_19 = "water_level_19";
    public static final String WATER_COL_20 = "water_level_20";
    /**
     * 超时KEY
     */
    public static final String EXPIRE_TIME_KEY = PRE_KEY + SEPARATOR + "expire_time";

    public static final String LAST_TRAIL = PRE_KEY + SEPARATOR + "last_trail";

    /**
     * 默认报文
     */
    public static final String DEFAULT_MESSAGE = "00000000000000000000000000000000";

    public enum SwitchTypeEnum {
        VIRTUAL_SWITCH("3", "虚拟阀门类型"), NORMAL_SWITCH("1", "普通阀门类型"), ELECTRIC_SWITCH("2", "电动机阀门类型");

        private String key;
        private String value;

        SwitchTypeEnum(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }
}

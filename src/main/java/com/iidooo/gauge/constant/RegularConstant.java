package com.iidooo.gauge.constant;

public class RegularConstant {

    public static final String REGEX_NUMBER = "^[0-9]*$"; // 数字组合的字符串正则表达式.

    public static final String REGEX_MOBILE = "^(1[0-9]{10})$";
    
    // The regular expression of email
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    // The regular expression of half English
    public static final String REGEX_ENGLISH = "^[a-zA-Z]*";
    // The regular expression of half English and number
    public static final String REGEX_ENGLISH_NUMBER = "^[A-Za-z0-9]+$";
}

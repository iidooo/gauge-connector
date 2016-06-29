package com.iidooo.gauge.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.iidooo.gauge.constant.RegularConstant;

public final class ValidateUtil {

    private static Logger logger = Logger.getLogger(ValidateUtil.class);

    /**
     * 是否是纯数字组合的字符串验证.
     *
     * @param str 对象文字列
     * @return true:数字组合字符串 false:非数字组合字符串
     */
    public static boolean isNumber(String str) {
        try {
            Pattern pattern = Pattern.compile(RegularConstant.REGEX_NUMBER);
            boolean result = pattern.matcher(str).matches();
            return result;
        } catch (Exception e) {
            logger.fatal(e);
            return false;
        }
    }

    /**
     * 金额类数据字符串验证（可带小数点的金额类数据）.
     *
     * @param str 对象文字列
     * @return true false
     */
    /*
     * public static boolean isNumeric(final String str) {
     * 
     * logger.debug("The begin of the method ValidatorUtil.isNumeric"); if (isEmptyIgnoreSpace(str)) { return false; } Pattern pattern =
     * Pattern.compile(Constants.REGEX_NUMERIC_STR); boolean flg = pattern.matcher(str).matches();
     * logger.debug("The end of the method ValidatorUtil.isNumeric"); return flg; }
     */

    public static boolean isEnglish(final String str) {
        try {
            Pattern pattern = Pattern.compile(RegularConstant.REGEX_ENGLISH);
            return pattern.matcher(str).matches();
        } catch (Exception e) {
            e.printStackTrace();
            logger.fatal(e);
            return false;
        }
    }

    public static boolean isEnglishNumber(final String str) {
        try {
            Pattern pattern = Pattern.compile(RegularConstant.REGEX_ENGLISH_NUMBER);
            return pattern.matcher(str).matches();
        } catch (Exception e) {
            e.printStackTrace();
            logger.fatal(e);
            return false;
        }
    }

    /**
     * 字符特定长度验证.
     *
     * @param str 对象文字列
     * @param length 文字列的长度
     * @return true:字符位数正确 false:字符位数不正
     */
    /*
     * public static boolean strLength(final String str, final int length) {
     * 
     * logger.debug("The begin of the method ValidatorUtil.strLength"); if (isEmptyIgnoreSpace(str)) { return false; } boolean flg = false;
     * if (str.length() == length) { flg = true; } logger.debug("The end of the method ValidatorUtil.strLength"); return flg; }
     *//**
     * 字符特定长度范围验证.
     *
     * @param str 对象文字列
     * @param minLength 最小长度
     * @param maxLength 最大长度
     * @return true:字符位数范围正确 false:字符位数范围不正
     */
    /*
     * public static boolean strLengthRanger(final String str, final int minLength, final int maxLength) {
     * 
     * logger.debug("The begin of the method ValidatorUtil.strLengthRanger"); if (isEmptyIgnoreSpace(str) || minLength > maxLength) { return
     * false; } int strlength = str.length(); boolean flg = false; if (strlength >= minLength && strlength <= maxLength) { flg = true; }
     * logger.debug("The end of the method ValidatorUtil.strLengthRanger"); return flg; }
     *//**
     * 全角字符验证（是否为全角字符）.
     *
     * @param str 判定的文字列
     * @return true:验证正确 false:验证不正
     */
    /*
     * public static boolean checkWideChar(final String str) {
     * 
     * logger.debug("The begin of the method ValidatorUtil.checkWideChar"); boolean flg = false; try { if (isNotEmpty(str)) { byte[] abyte =
     * str.getBytes(Constants.EUC_JP); for (int i = 0; i < abyte.length; i++) { byte be = abyte[i]; if (be >= 0 && be <=
     * Constants.NUMBER_127 || be == Constants.NUMBER_MINUS_114) { if (be == Constants.NUMBER_63) { for (int j = 0; j < str.length(); j++) {
     * char c = str.charAt(j); if (c == Constants.CHAR_QUESTION) { flg = false; break; } } } else { flg = false; break; } } } } } catch
     * (UnsupportedEncodingException exception) { flg = false; } logger.debug("The end of the method ValidatorUtil.checkWideChar"); return
     * flg; }
     *//**
     * 半角字符验证（是否为半角字符）.
     *
     * @param str 判定的文字列
     * @return true:验证正确 false:验证不正
     */
    /*
     * public static boolean checkHalfChar(final String str) {
     * 
     * logger.debug("The begin of the method ValidatorUtil.checkHalfChar"); boolean flg = true; if (isNotEmptyIgnoreSpace(str)) { char
     * cTemp; for (int i = 0; str.length() > i; i++) { cTemp = str.charAt(i); if (!((cTemp >= Constants.CHAR_EXCLAMATION && cTemp <=
     * Constants.CHAR_TILDE) || (cTemp >= Constants.CHAR_PERIOD && cTemp <= Constants.CHAR_UP_PERIOD) || (cTemp == Constants.CHAR_SPACE))) {
     * flg = false; break; } } } logger.debug("The end of the method ValidatorUtil.checkHalfChar"); return flg; }
     */

    /**
     * 验证是否是Email的格式
     *
     * @param email 该Email地址需要验证
     * @return 验证正确还是错误的结果
     */
    public static boolean isEmail(String email) {
        try {
            if (email == null || email.isEmpty()) {
                return false;
            }
            Pattern p = Pattern.compile(RegularConstant.REGEX_EMAIL);
            Matcher m = p.matcher(email);
            return m.matches();
        } catch (Exception e) {
            e.printStackTrace();
            logger.fatal(e);
            return false;
        }
    }

    /**
     * 网址URL格式验证.
     *
     * @param url 验证网址URL文字列
     * @return true:格式正确 false:格式不正
     */
    /*
     * public static boolean urlFormateValidate(final String url) {
     * 
     * logger.debug("The begin of the method ValidatorUtil.urlFormateValidate"); boolean flg = false; if (isNotEmptyIgnoreSpace(url)) {
     * Pattern p = Pattern.compile(Constants.REGEX_URL_FORMATE); Matcher m = p.matcher(url); flg = m.matches(); }
     * logger.debug("The end of the method ValidatorUtil.urlFormateValidate"); return flg; }
     */

    /**
     * Validate the date format
     *
     * @param date The input date
     * @param format The format of date
     * @return validate result
     */
    public static boolean validateDateFormat(String date, String format) {
        try {
            if (date == null || date.isEmpty()) {
                return false;
            }

            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date parsedDate = sdf.parse(date);
            String parsedDateStr = sdf.format(parsedDate);
            if (parsedDateStr.equals(date)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            logger.fatal(e);
            return false;
        }
    }

    /**
     * 验证字符串中是否含特殊字符(特殊字符数组(<,>,&,",')).
     *
     * @param str 验证文字列
     * @return true :含有特殊字符 false :不含有特殊字符
     */
    /*
     * public static boolean indexOfSpecialCharacter(final String str) {
     * 
     * logger.debug("The begin of the method ValidatorUtil.indexOfSpecialCharacter"); boolean flg = false; // 非空判断 if
     * (isNotEmptyIgnoreSpace(str)) { for (String chara : Constants.SPECIAL_CHARACTER) { if (str.indexOf(chara) >= 0) { flg = true; break; }
     * } } logger.debug("The end of the method ValidatorUtil.indexOfSpecialCharacter"); return flg; }
     *//**
     * 是否是中文验证.
     *
     * @param str 对象文字列
     * @return true:是中文字符串 false:非中文字符串
     */
    /*
     * public static boolean isChinese(final String str) {
     * 
     * logger.debug("The begin of the method ValidatorUtil.isChinese"); if (isEmptyIgnoreSpace(str)) { return false; } Pattern pattern =
     * Pattern.compile(Constants.REGEX_CHINESE_STR); boolean flg = pattern.matcher(str).matches();
     * logger.debug("The end of the method ValidatorUtil.isChinese"); return flg; }
     */
    
    public static void main(String[] args){

    }
}

package com.iidooo.gauge.util;

import java.util.Random;
import java.util.UUID;

import org.apache.log4j.Logger;

public class StringUtil {

    private static final Logger logger = Logger.getLogger(StringUtil.class);

    /**
     * 字符串为空判断.
     *
     * @param str check对象字符串
     * @return boolean true:null 空字符串、false:非空字符串
     */
    public static boolean isNotBlank(final String str) {
        try {
            boolean result = false;
            if (str != null && str.length() > 0) {
                result = true;
            }
            return result;
        } catch (Exception e) {
            logger.fatal(e);
            return false;
        }
    }

    public static boolean isBlank(final String str) {
        try {
            boolean result = false;
            if (str == null || str.length() <= 0) {
                result = true;
            }
            return result;
        } catch (Exception e) {
            logger.fatal(e);
            return true;
        }
    }

    /**
     * Replace the old string by the array of new strings. The old string's {1}, {2}... are the replace object.
     * 
     * @param old This old string should be replaced.
     * @param news These new strings will replace the old string's {1}, {2}
     * @return The result of the replace string.
     */
    public static String replace(String old, String... news) {
        try {
            String result = old;
            for (int i = 0; i < news.length; i++) {
                if (news[i] == null) {
                    continue;
                }
                result = result.replace("{" + i + "}", news[i]);
            }
            return result;
        } catch (Exception e) {
            logger.fatal(e);
            return old;
        }
    }

    public static String substring(String str, int start, int end) {
        try {
            if (str == null || str.isEmpty()) {
                return "";
            }

            String result = str;
            if (str.length() < (start + end)) {
                logger.warn("The input string is not long enough!");
                return str;
            }

            str.substring(start, end);
            return result;
        } catch (Exception e) {
            logger.fatal(e);
            return str;
        }
    }

    public static String getRandomStr(int length) {
        try {
            int A = 'A';
            int Z = 'Z';

            Random rand = new Random();
            StringBuilder sb = new StringBuilder();
            while (sb.length() < length) {
                int number = rand.nextInt(Z + 1);
                if (number >= A) {
                    sb.append((char) number);
                }
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            logger.fatal(e);
            throw e;
        }
    }

    /**
     * 得到随机数字符串
     * 
     * @param length 字符串的长度
     * @param rank 每个数字的范围
     * @return 返回得到的随机数字符串
     */
    public static String getRandomNumber(int length, int rank) {
        try {
            Random rand = new Random();
            StringBuilder sb = new StringBuilder();
            while (sb.length() < length) {
                int number = rand.nextInt(rank + 1);
                sb.append(number);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            logger.fatal(e);
            throw e;
        }
    }

    /**
     * 得到请求的基础路径，如：http://www.iidooo.com:8080
     * 
     * @param requestURL
     * @param servletPath
     * @return
     */
    public static String getRequestBaseURL(String requestURL, String servletPath) {
        try {
            return requestURL.replace(servletPath, "");
        } catch (Exception e) {
            logger.fatal(e);
            return "";
        }
    }

    public static String getGUID() {
        try {
            String result = UUID.randomUUID().toString();
            result = result.replace("-", "");
            return result;
        } catch (Exception e) {
            logger.fatal(e);
            return "";
        }
    }

    public static void main(String[] args) {

        System.out.println(StringUtil.getGUID());
        System.out.println(StringUtil.getGUID().length());
    }
}

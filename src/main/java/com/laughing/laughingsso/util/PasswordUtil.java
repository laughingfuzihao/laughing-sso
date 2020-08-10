package com.laughing.laughingsso.util;

/**
 * @author Fu zihao
 * @version 1.0
 * @Description:
 * @date 20202020/8/10 11:55
 */
public class PasswordUtil {

    // 1.用户名最多25个字符，不能包含空格，单双引号，问号等特殊符号
    public static boolean isCorrectUserName(String userName) {
        boolean isCorrect = true;
        if (userName == null) {
            return false;
        }
        if (userName.length() > 25) {
            return false;
        }
        /*定义非法的字符数组，单引号要用\'表示*/
        char[] unvaildChar = {' ', '\'', '"', '?', '*', '~', ','};
        for (char ch : unvaildChar) {
            if (userName.contains(Character.toString(ch))) {
                System.out.println("存在非法字符");
                isCorrect = false;
                break;

            }
        }
        System.out.println("账号输入正确");
        return isCorrect;
    }

    // 密码要6到18位，只能包含字母数字，特殊符号
    public static boolean isCorrectPassword(String password) {
        boolean isCorrect = true;
        if (password == null) {
            return false;
        }
        if (password.length() < 6) {
            return false;
        }
        if (password.length() > 18) {
            return false;
        }
        String part = "@&#$,.";
        for (int i = 0; i < password.length(); i++) {
            /*如果不为字母和数字*/
            if (!(Character.isLetterOrDigit(password.charAt(i)))) {
                if (!(part.contains(Character.toString(password.charAt(i))))) {
                    return false;
                }
            }
        }
        return isCorrect;
    }

    /*2.密码要6到18位，只能包含字母数字，特殊符号
     * 纯数字，纯字母时，密码强度为弱
     * 数字加字母，数字加符号，字母加符号时，密码强度为中
     *数字加字母加符号时，密码强度为强 */
    public static String vaildatePassword(String password) {
        /*密码强度*/
        String power = "";
        if (password == null) {
            return power;
        }
        if (password.length() < 6 || password.length() > 18) {
            return power;
        }
        /*合法的组成部分*/
        //String[]collectPart= {"_","@","&","#","$","."};
        String part = "@&#$,.";
        for (int i = 0; i < password.length(); i++) {
            /*如果不为字母和数字*/
            if (!(Character.isLetterOrDigit(password.charAt(i)))) {
                if (!(part.contains(Character.toString(password.charAt(i))))) {
                    System.out.println("密码不合法");
                    return power;
                }
            }

        }
        /*纯数字或纯字母*/
        if (isDigit(password) || isletter(password)) {
            System.out.print("密码强度为");
            power = "★★☆☆☆☆";
        } else if (isDigitAndLetter(password)) {
            System.out.print("密码强度为");
            power = "★★★★☆☆";
        } else if (isContainssymble(password)) {
            System.out.println("密码强度为");
            power = "★★★★★★";
        }
        return power;
    }

    /*判断密码强度*/
    /*判断字符串是不是纯数字*/
    private static boolean isDigit(String value) {

        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /*判断字符串是不是纯字母*/
    private static boolean isletter(String value) {

        for (int i = 0; i < value.length(); i++) {
            if (!Character.isLetter(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    /*有没有包含特殊符号*/

    private static boolean isContainssymble(String value) {

        String part = "@&#$,.";
        for (int i = 0; i < value.length(); i++) {
            if (part.contains(Character.toString(value.charAt(i)))) {
                return true;
            }

        }
        return false;
    }

    /*是否包含数字和字母*/
    private static boolean isDigitAndLetter(String value) {
        for (int i = 0; i < value.length(); i++) {
            if (!(Character.isLetterOrDigit(value.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
    /*是否包含数字字母和特殊符号*/


}

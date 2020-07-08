package leetcode.wyj;

/**
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * <p>
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 * <p>
 * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
 * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
 * <p>
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 * <p>
 * 提示：
 * <p>
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 * <p>
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 * <p>
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 * <p>
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * 示例 5:
 * <p>
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 *      因此返回 INT_MIN (−231) 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode8 {
    public static void main(String[] args) {
        System.out.println(myAtoi(" -1010023630o4"));
    }

    public static int myAtoi(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        str = str.trim();
        if (str.length() == 0) {
            return 0;
        }
        //判断第一个非空字符是否为数字或者-,+
        char firstChar = str.charAt(0);
        if ((!isNum(firstChar)) && (!isSigned(firstChar))) {
            return 0;
        }

        int result = 0;
        int signed = 1;

        if (firstChar == 45) {
            //如果是-
            signed = -1;
        } else if (firstChar == 43) {
            //如果是+
            signed = 1;
        } else {
            result = firstChar - 48;
        }

        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if (isNum(c)) {
                result = result * 10 + (c - 48);
            } else {
                break;
            }
            //判断是否越界
            if (i < str.length() - 1) {
                if (!isNum(str.charAt(i + 1))) {
                    break;
                }
                if (signed == 1 && (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && str.charAt(i + 1) > 55))) {
                    return Integer.MAX_VALUE;
                }
                if (signed == -1 && (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && str.charAt(i + 1) > 56))) {
                    return Integer.MIN_VALUE;
                }
            }
        }
        return result * signed;

    }

    private static boolean isNum(char c) {
        if (c >= 48 && c <= 57) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isSigned(char c) {
        if (c == 45 || c == 43) {
            return true;
        } else {
            return false;
        }
    }
}

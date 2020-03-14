package leetcode_inta.leetcode151_200;

/**
 * @author inta
 * @date 2019/12/21
 * @describe 比较两个版本号 version1 和 version2。
 * 如果 version1 > version2 返回 1，如果 version1 < version2 返回 -1， 除此之外返回 0。
 *
 * 你可以假设版本字符串非空，并且只包含数字和 . 字符。
 *
 *  . 字符不代表小数点，而是用于分隔数字序列。
 *
 * 例如，2.5 不是“两个半”，也不是“差一半到三”，而是第二版中的第五个小版本。
 *
 * 你可以假设版本号的每一级的默认修订版号为 0。
 * 例如，版本号 3.4 的第一级（大版本）和第二级（小版本）修订号分别为 3 和 4。其第三级和第四级修订号均为 0。
 *  
 *
 * 示例 1:
 *
 * 输入: version1 = "0.1", version2 = "1.1"
 * 输出: -1
 * 示例 2:
 *
 * 输入: version1 = "1.0.1", version2 = "1"
 * 输出: 1
 * 示例 3:
 *
 * 输入: version1 = "7.5.2.4", version2 = "7.5.3"
 * 输出: -1
 * 示例 4：
 *
 * 输入：version1 = "1.01", version2 = "1.001"
 * 输出：0
 * 解释：忽略前导零，“01” 和 “001” 表示相同的数字 “1”。
 * 示例 5：
 *
 * 输入：version1 = "1.0", version2 = "1.0.0"
 * 输出：0
 * 解释：version1 没有第三级修订号，这意味着它的第三级修订号默认为 “0”。
 *  
 *
 * 提示：
 *
 * 版本字符串由以点 （.） 分隔的数字字符串组成。这个数字字符串可能有前导零。
 * 版本字符串不以点开始或结束，并且其中不会有两个连续的点。
 *
 */
public class Q165compareVersion {
    public int compareVersion(String version1, String version2) {
        int res = 0;
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int len1 = v1.length;
        int len2 = v2.length;
        int index = 0;
        while (index < len1 && index < len2) {
            if (Integer.valueOf(v1[index]) > Integer.valueOf(v2[index])) {
                res = 1;
                break;
            } else if (Integer.valueOf(v1[index]) < Integer.valueOf(v2[index])) {
                res = -1;
                break;
            }
            index ++;
        }
        if (res == 0) {
            while (index < len1) {
                if (Integer.valueOf(v1[index]) > 0) {
                    res = 1;
                    break;
                }
                index ++;
            }
            while (index < len2) {
                if (Integer.valueOf(v2[index]) > 0) {
                    res = -1;
                    break;
                }
                index ++;
            }
        }
        return res;
    }

    //大神解法中，语句思路更清晰，而且还提供了数据范围超int类型情况下的解法
    public int compareVersion2(String version1, String version2) {
        int res = 0;
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int index = 0;
        //当不考虑溢出的情况
//        while (index < v1.length || index < v2.length) {
//            String n1_str = index < v1.length ? rimZero(v1[index]) : "";
//            String n2_str = index < v2.length ? rimZero(v2[index]) : "";
//            int n1 =  n1_str.equals("") ? 0 : Integer.parseInt(n1_str);
//            int n2 =  n2_str.equals("") ? 0 : Integer.parseInt(n2_str);
//            //如果存在大小不一，直接可以考虑返回
//            if (n1 != n2) {
//                res = n1 > n2 ? 1 : -1;
//                break;
//            }
//            index ++;
//        }
        //如果想适用于超长字符，可以使用字符串比较
        while (index < v1.length || index < v2.length) {
            String n1 = index < v1.length ? rimZero(v1[index]) : "";
            String n2 = index < v2.length ? rimZero(v2[index]) : "";
            //先简单长度判断
            if (n1.length() > n2.length()) {
                res = 1;
            } else if (n1.length() < n2.length()) {
                res = -1;
            } else if (n1.length() == n2.length()) {
                res = compareStr(n1, n2);
            }
            //当比较出不同的结果时，直接返回即可
            if (res != 0) break;
            index ++;
        }

        return res;
    }
    //给字符串前零去掉
    private String rimZero(String str) {
        int index;
        for (index = 0; index < str.length(); index ++) {
            if (str.charAt(index) != '0') {
                break;
            }
        }
        return str.substring(index);
    }

    private int compareStr(String str1, String str2) {
        int res = 0;
        //这个时候的字符串提前已经处理过头零问题了,并且长度一致
        for (int i = 0; i < str1.length(); i ++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                res = str1.charAt(i) > str2.charAt(i) ? 1 : -1;
                break;
            }
        }
        return res;
    }


    //还有大神的超级简洁实现
    public int compareVersion3(String version1, String version2) {
        int res = 0;
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int len1 = v1.length;
        int len2 = v2.length;
        int max_len = Math.max(len1, len2);
        for (int i = 0; i < max_len; i ++) {
            int num1 = i < len1 ? Integer.valueOf(v1[i]) : 0;
            int num2 = i < len2 ? Integer.valueOf(v2[i]) : 0;

            if (num1 != num2) {
                res = num1 > num2 ? 1 : -1;
                break;
            }
        }
        return res;
    }
}

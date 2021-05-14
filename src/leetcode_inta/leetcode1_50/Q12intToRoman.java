package leetcode_inta.leetcode1_50;

/**
 * @author inta
 * @date 2019/11/24
 * @describe 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。
 * 27 写做  XXVII, 即为 XX + V + II 。
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，
 * 例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
 * 同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 * 示例 1:
 * 输入: 3
 * 输出: "III"
 * 示例 2:
 * 输入: 4
 * 输出: "IV"
 * 示例 3:
 * 输入: 9
 * 输出: "IX"
 * 示例 4:
 * 输入: 58
 * 输出: "LVIII"
 * 解释: L = 50, V = 5, III = 3.
 * 示例 5:
 * 输入: 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 */
public class Q12intToRoman {
    //自己写的对比大神的，太啰嗦了，大神使用了数组保存数值可能情况 和字符数组保存了罗马字符情况，一一对应的
    //然后从大到小遍历数组索引，当num大于索引对于数组元素，就添加罗马字符数组对于元素，并消减num直到num小于，变动索引
    public String intToRoman(int num) {
        //用来存储结果
        StringBuilder sb = new StringBuilder();
        int len = 0;
        int temp = num;
        //找到num的长度
        while (temp != 0) {
            len ++;
            temp /= 10;
        }
        //根据长度判断
        while (len != 0) {
            int count = 0;
            if (len == 4) {
                count = num / 1000;
                num %= 1000;
            } else if (len == 3) {
                count = num / 100;
                num %= 100;
            } else if (len == 2) {
                count = num / 10;
                num %= 10;
            } else if (len == 1) {
                count = num;
            }
            if (count < 4) {
                if (len == 4) {
                    for (int i = 0; i < count; i ++) {
                        sb.append("M");
                    }
                } else if (len == 3) {
                    for (int i = 0; i < count; i ++) {
                        sb.append("C");
                    }
                } else if (len == 2) {
                    for (int i = 0; i < count; i ++) {
                        sb.append("X");
                    }
                } else if (len == 1) {
                    for (int i = 0; i < count; i ++) {
                        sb.append("I");
                    }
                }
            } else if (count > 4 && count < 9) {
                if (len == 4) {
                    sb.append("?");
                } else if (len == 3) {
                    sb.append("D");
                    for (int i = 5; i < count; i ++) {
                        sb.append("C");
                    }
                } else if (len == 2) {
                    sb.append("L");
                    for (int i = 5; i < count; i ++) {
                        sb.append("X");
                    }
                } else if (len == 1) {
                    sb.append("V");
                    for (int i = 5; i < count; i ++) {
                        sb.append("I");
                    }
                }
            } else if (count == 4 || count == 9) {
                if (len == 4) {
                    sb.append("?");
                } else if (len == 3) {
                    if (count == 4) {
                        sb.append("CD");
                    } else {
                        sb.append("CM");
                    }
                } else if (len == 2) {
                    if (count == 4) {
                        sb.append("XL");
                    } else {
                        sb.append("XC");
                    }
                } else if (len == 1) {
                    if (count == 4) {
                        sb.append("IV");
                    } else {
                        sb.append("IX");
                    }
                }
            }
            //每遍历一次都要缩减长度
            len --;
        }
        return sb.toString();
    }

    public String intToRoman2(int num) {
        StringBuilder sb = new StringBuilder();
        while (num >= 1000) {
            sb.append('M');
            num -= 1000;
        }
        if (num >= 900) {
            sb.append("CM");
            num %= 900;
        }
        if (num >= 500) {
            sb.append("D");
            num -= 500;
        }
        if (num >= 400) {
            sb.append("CD");
            num %= 400;
        }
        while (num >= 100) {
            sb.append("C");
            num -= 100;
        }
        if (num >= 90) {
            sb.append("XC");
            num %= 90;
        }
        if (num >= 50) {
            sb.append("L");
            num -= 50;
        }
        if (num >= 40) {
            sb.append("XL");
            num %= 40;
        }
        while (num >= 10) {
            sb.append("X");
            num -= 10;
        }
        if (num >= 9) {
            sb.append("IX");
            num %= 9;
        }
        if (num >= 5) {
            sb.append("V");
            num -= 5;
        }
        if (num >= 4) {
            sb.append("IV");
            num %= 4;
        }
        while (num >= 1) {
            sb.append("I");
            num--;
        }
        return sb.toString();
    }

    public String intToRoman3(int num) {
        int[] nums = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] strs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nums.length; i++) {
            while (num >= nums[i]) {
                sb.append(strs[i]);
                num -= nums[i];
            }
        }
        return sb.toString();
    }
}

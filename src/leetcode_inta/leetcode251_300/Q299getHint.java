package leetcode_inta.leetcode251_300;

/**
 * @author inta
 * @date 2020/1/30
 * @describe 你正在和你的朋友玩 猜数字（Bulls and Cows）游戏：你写下一个数字让你的朋友猜。每次他猜测后，你给他一个提示，告诉他有多少位数字和确切位置都猜对了（称为“Bulls”, 公牛），有多少位数字猜对了但是位置不对（称为“Cows”, 奶牛）。你的朋友将会根据提示继续猜，直到猜出秘密数字。
 *
 * 请写出一个根据秘密数字和朋友的猜测数返回提示的函数，用 A 表示公牛，用 B 表示奶牛。
 *
 * 请注意秘密数字和朋友的猜测数都可能含有重复数字。
 *
 * 示例 1:
 *
 * 输入: secret = "1807", guess = "7810"
 *
 * 输出: "1A3B"
 *
 * 解释: 1 公牛和 3 奶牛。公牛是 8，奶牛是 0, 1 和 7。
 * 示例 2:
 *
 * 输入: secret = "1123", guess = "0111"
 *
 * 输出: "1A1B"
 *
 * 解释: 朋友猜测数中的第一个 1 是公牛，第二个或第三个 1 可被视为奶牛。
 * 说明: 你可以假设秘密数字和朋友的猜测数都只包含数字，并且它们的长度永远相等。
 *
 */
public class Q299getHint {
    //使用map来保存数字信息k/v 然后遍历若非对应位置但存在于map中为B数量-1 不存在就跳过 对应位置存在为A数量-1
    public String getHint(String secret, String guess) {
        //使用map按照之前的经验告诉我，效率会低，使用数组来保存信息0-9
        int[] counts = new int[10];
        char[] sec = secret.toCharArray();
        char[] gue = guess.toCharArray();
        boolean[] visited = new boolean[sec.length];
        for (char s : sec) {
            counts[s - '0'] ++;
        }
        StringBuilder sb = new StringBuilder();
        int A = 0, B = 0;
        //优先找所有A
        for (int i = 0; i < gue.length; i ++) {
            if (sec[i] == gue[i]) {
                A ++;
                counts[gue[i] - '0'] --;
                visited[i] = true;
            }
        }
        //再找B
        for (int i = 0; i < gue.length; i ++) {
            int index = gue[i] - '0';
            //如果该字符存在于字典中，即数量>0,之前已经判断过所有A了，我们找的是B了
            if (!visited[i] && counts[index] > 0) {
                B ++;
                counts[index] --;
            }
        }
        sb.append(A).append('A').append(B).append('B');
        return sb.toString();
    }
}

package tencent.exam_main.leetcode_exam2020;

/**
 * @author inta
 * @date 2020/1/18
 * @describe
 */
public class D0118_1 {

    public boolean canVillagersWin(String[] players, int[] credibility) {
        //用一个数组存储是否是好人，一个数组存储是否存活
        int[] isGood = new int[12];
        int[] isAlive = new int[12];
        //潜在的怀疑对象,分别对应熊左和右位置
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;

        for (int i = 0; i < 12; i ++) {
            String play = players[i];
            if (!play.equals("ww")) {
                //如果不是狼，那就是好人
                isGood[i] = 1;
            }
            //并且都为存活状态
            isAlive[i] = 1;
        }
        //设定一个Boolean 判断熊有无公布身份
        boolean open = false;
        //标记熊存活状态，提高效率
        boolean bearLive = true;
        //当游戏不能进行的时候，直接返回结果
        int end = hasEnd(isAlive, isGood);
        while (end == 0) {
            for (int num : isAlive) {
                System.out.print(num + " -");
            }
            System.out.println();

            //狼人杀一个分值最高且存活的好人，或者杀暴露身份且存活的熊
            //如果熊找到了，还活着,并且暴露身份了
            if (bearLive && open) {
                //熊所在索引位置
                int bearNum = findBear(players);
                //如果是暴露状态，必须死
                isAlive[bearNum] = 0;
                bearLive = false;
            } else {
                //找到分值最高的好人的编号
                int killNum = findMax(isAlive, isGood, credibility);
                //杀之
                if (players[killNum].equals("hunter")) {
                    isAlive[killNum] = 0;
                    //带走一个人
                    isAlive[findMin(isAlive, credibility)] = 0;
                } else {
                    isAlive[killNum] = 0;
                    //死的时候，分值最高化
                    credibility[killNum] = 100;
                    //并且需要考虑是否为怀疑对象，是就排除怀疑
                    if (killNum == left || killNum == right) {
                        if (killNum == left && credibility[right] != 100) {
                            credibility[right] = 0;
                        } else if (killNum == right && credibility[left] != 100) {
                            credibility[left] = 0;
                        }
                    }
                }

            }


            //熊如果在，开始判断有无狼，是否要咆哮
            if (bearLive && isAlive[findBear(players)] == 1) {
                open = true;
                //给熊分值上去
                credibility[findBear(players)] = 100;
                int bearNum = findBear(players);
                //因为要找左右两边的，我干脆弄成三倍体，从左和从右找第一个活着的
                int[] copy = new int[36];
                for (int i = 0; i < 36; i ++) {
                    copy[i] = isAlive[i % 12];
                }
                for (int i = bearNum + 11; i >= 0; i --) {
                    if (copy[i] == 1) {
                        left = i % 12;
                        break;
                    }
                }
                for (int i = bearNum + 13; i < 36; i ++) {
                    if (copy[i] == 1) {
                        right = i % 12;
                        break;
                    }
                }
                //判断是否好人，是否降分
                if (isGood[left] == 0 || isGood[right] == 0) {
                    //有一个坏人，就全部降分
                    credibility[left] = credibility[left] == 1 ? 1 : credibility[left] / 2;
                    credibility[right] = credibility[right] == 1 ? 1 : credibility[right] / 2;
                } else {
                    //否则，铁好人
                    credibility[left] = 100;
                    credibility[right] = 100;
                }
            }

            //票选环节，找到分值最少且活着的一人出局
            int minNum = findMin(isAlive, credibility);
            if (players[minNum].equals("idiot")) {
                //如果是白痴，不死,分值最高化，进入下一夜
                credibility[minNum] = 100;
                //并且需要考虑是否为怀疑对象，是就排除怀疑
                if (minNum == left || minNum == right) {
                    if (minNum == left && credibility[right] != 100) {
                        credibility[right] = 0;
                    } else if (minNum == right && credibility[left] != 100) {
                        credibility[left] = 0;
                    }
                }

            } else if (players[minNum].equals("hunter")) {
                isAlive[minNum] = 0;
                //铁好人
                credibility[minNum] = 100;
                //并且需要考虑是否为怀疑对象，是就排除怀疑
                if (minNum == left || minNum == right) {
                    if (minNum == left && credibility[right] != 100) {
                        credibility[right] = 0;
                    } else if (minNum == right && credibility[left] != 100) {
                        credibility[left] = 0;
                    }
                }
                //带走一个人
                int hunterKill = findMin(isAlive, credibility);
                isAlive[hunterKill] = 0;
                System.out.println("hunter" + minNum + "号被票出，并带走了" + hunterKill + "号玩家");
            } else {
                isAlive[minNum] = 0;
            }

            end = hasEnd(isAlive, isGood);
            System.out.println(end);
        }
        //最终返回2，才说明是好人阵营赢了，否则就是狼人赢
        return end == 2;
    }
    //判断游戏是否结束的方法 0 表示没结束 1表示狼人赢了 2表示好人赢了
    private int hasEnd(int[] isAlive, int[] isGood) {
        int persons = 0;
        int wolves = 0;
        for (int i = 0; i < 12; i ++) {
            if (isAlive[i] == 1) {
                if (isGood[i] == 1) {
                    //如果是存活着的好人
                    persons ++;
                } else {
                    wolves ++;
                }
            }
        }
        //全部遍历完，考虑人数差异
        if (wolves == 0) return 2;
        return persons > wolves ? 0 : 1;
    }

    //找寻一个分值最高且在场的好人,注意优先从左往右找
    private int findMax(int[] isAlive, int[] isGood, int[] credibility) {
        int res = 0;
        int cre = Integer.MIN_VALUE;
        for (int i = 0; i < 12; i ++) {
            if (isAlive[i] == 1 && isGood[i] == 1 && credibility[i] > cre) {
                res = i;
                cre = credibility[i];
            }
        }
        return res;
    }

    //找到熊所在索引 -1代表熊死了，其余为其索引,open代表熊公布身份了
    private int findBear(String[] players) {
        for (int i = 0; i < 12; i ++) {
            if (players[i].equals("bear")) {
                return i;
            }
        }
        return -1;
    }

    private int findMin(int[] isAlive, int[] credibility) {
        int res = 0;
        int cre = Integer.MAX_VALUE;
        for (int i = 0; i < 12; i ++) {
            if (isAlive[i] == 1 && credibility[i] < cre) {
                res = i;
                cre = credibility[i];
            }
        }
        return res;
    }
}

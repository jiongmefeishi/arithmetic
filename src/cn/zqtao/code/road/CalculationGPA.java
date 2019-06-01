package cn.zqtao.code.road;

import java.util.Scanner;

/**
 * @auther: zqtao
 * @description: The calculation of GPA
 * <p>
 * 每学期的期末，大家都会忙于计算自己的平均成绩，这个成绩对于评奖学金是直接有关的。国外大学都是计算GPA(grade point average) 又称GPR(grade point ratio)，即成绩点数与学分的加权平均值来代表一个学生的成绩的。那么如何来计算GPA呢？ 
 * <p>
 * 一般大学采用之计分法 
 * <p>
 * A90 - 100 4 点 
 * B80 - 89 3 点 
 * C70 - 79 2 点 
 * D60 - 69 1 点 
 * E0 - 59 0 点 
 * <p>
 * 例如：某位学生修习三门课，其课目、学分及成绩分别为： 
 * 英文：三学分、92 分；化学：五学分、80 分；数学：二学分、60分，则GPA的算法如下： 
 * <p>
 * 科目 学分 分数 点数 分数×点数  
 * 英文  3    92    4     12 
 * 化学  5    80    3     15 
 * 数学  2    60    1      2 
 * 合计  10   29 
 * 29/10=2.9 
 * 2.9即为某生的GPA 
 * 下面有请你写一个用于计算GPA的程序。 
 * Input
 * <p>
 * 包含多组数据，每组数据的第一行有一个数N，接下来N行每行表示一门成绩。每行有两个实型的数 s,p，s表示这门课的学分,p表示该学生的成绩（百分制）。如果p=-1则说明该学生这门课缺考，是不应该计算在内的。 
 * Output
 * <p>
 * 对每组数据输出一行，表示该学生的GPA，保留两位小数。如果GPA不存在，输出-1。 
 * Sample Input
 * <p>
 * 3
 * 3 92
 * 5 80
 * 2 60
 * Sample Output
 * <p>
 * 2.90
 * @version: 1.0
 */
public class CalculationGPA {

    private void solution() {
        Scanner in = new Scanner(System.in);

        int N = 0; // N 行输入
        double s = 0; // 这门课的学分
        double p = 0; // 该学生的成绩（百分制）

        double sSum = 0; // 学分总分数
        double sum = 0; // 该学生总点数

        while (in.hasNext()) {
            N = in.nextInt();
            for (int i = 0; i < N; i++) {
                s = in.nextDouble();
                p = in.nextDouble();

                // 根据 成绩p 换算出点数
                if (p == -1)
                    continue;
                sSum += s;
                if (p >= 90)
                    sum += 4;
                else if (p >= 80 && p <= 89)
                    sum += 3;
                else if (p >= 70 && p <= 79)
                    sum += 2;
                else if (p >= 60 && p <= 69)
                    sum += 1;
                else if (p >= 0 && p <= 59)
                    sum += 0;
            }

            if (sum <= 0 || sSum <= 0) // 排除 -1 分情况
                continue;
            System.out.println(String.format("%.2f", sum / sSum));
        }

    }
}

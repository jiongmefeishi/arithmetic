package cn.zqtao.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @auther: zqtao
 * @description: 输出模板
 * @version: 1.0
 */
public class ArithmeticOutputModel {

    public static void main(String[] args) {

        // 这里0指一位数字，#指除0以外的数字(如果是0，则不显示),四舍五入.
        NumberFormat formatter = new DecimalFormat("000000");
        String s = formatter.format(-1234.567); // -001235
        System.out.println(s);

//        formatter = new DecimalFormat("##");
//        s = formatter.format(-1234.567); // -1235
//        System.out.println(s);
//        s = formatter.format(0); // 0
//        System.out.println(s);
//        formatter = new DecimalFormat("##00");
//        s = formatter.format(0); // 00
//        System.out.println(s);
//

        // 保留两位小数
        System.out.format("%.2f", (2.333)).println();

//        formatter = new DecimalFormat(".00");
//        s = formatter.format(-.567); // -.57
//        System.out.println(s);
        formatter = new DecimalFormat("0.00");
        s = formatter.format(-.567); // -0.57
        System.out.println(s);


//        formatter = new DecimalFormat("#.#");
//        s = formatter.format(-1234.567); // -1234.6
//        System.out.println(s);
//        formatter = new DecimalFormat("#.######");
//        s = formatter.format(-1234.567); // -1234.567
//        System.out.println(s);
//        formatter = new DecimalFormat(".######");
//        s = formatter.format(-1234.567); // -1234.567
//        System.out.println(s);
//        formatter = new DecimalFormat("#.000000");
//        s = formatter.format(-1234.567); // -1234.567000
//        System.out.println(s);
//
//        formatter = new DecimalFormat("#,###,###");
//        s = formatter.format(-1234.567); // -1,235
//        System.out.println(s);
//        s = formatter.format(-1234567.890); // -1,234,568
//        System.out.println(s);
//
//        // The ; symbol is used to specify an alternate pattern for negative
//        // values
//        formatter = new DecimalFormat("#;(#) ");
//        s = formatter.format(-1234.567); // (1235)
//        System.out.println(s);
//
//        // The ' symbol is used to quote literal symbols
//        formatter = new DecimalFormat(" '# '# ");
//        s = formatter.format(-1234.567); // -#1235
//        System.out.println(s);
//        formatter = new DecimalFormat(" 'abc '# ");
//        s = formatter.format(-1234.567); // - abc 1235
//        System.out.println(s);
//
//        formatter = new DecimalFormat("#.##%");
//        s = formatter.format(-12.5678987);
//        System.out.println(s);
    }
}

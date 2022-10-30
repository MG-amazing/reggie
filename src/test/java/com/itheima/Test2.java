package com.itheima;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

import static org.apache.coyote.http11.Constants.a;

public class Test2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BigDecimal a = new BigDecimal(input.next());
        BigDecimal b = new BigDecimal(input.next());
        BigDecimal c = new BigDecimal(input.next());
        BigDecimal sum=a.add(b).add(c);
        BigDecimal d= BigDecimal.valueOf(3);
        System.out.println(sum.setScale(6,RoundingMode.HALF_UP));
        System.out.println(sum.divide(d,6,BigDecimal.ROUND_HALF_EVEN));

    }
}

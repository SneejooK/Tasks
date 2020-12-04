package com.task3;

import java.math.BigInteger;

public class Task3 {

    public static void main(String[] args) {

        //find factorial and write it in BigInteger 
        BigInteger fact = BigInteger.valueOf(1);
        for (int i = 1; i <= 865; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        //create a new array of BigInteger, and add all the numbers
        int answer = 0;
        String[] numbers = fact.toString().split("");
        for (int i = 0; i < numbers.length; i++) {
            answer = answer + Integer.parseInt(numbers[i]);
        }
        //we get the answer
        System.out.println(answer);
    }
}

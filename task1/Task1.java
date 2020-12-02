package com.task1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Task1 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the number of brackets:");
        System.out.println("Combinations with closed brackets - " + correctParentheses(scanner.nextInt()));
    }

    static int correctParentheses(int count) {
        if (count <= 0) {
            return 0;
        }
        //array of possible combinations with '()'
        String[] combinations = new String[]{"()", ")(", "((", "))"};
        List<String> strings = new ArrayList<>(Arrays.asList(combinations));
        int newCycle = 0;
        int answer = 0;
        //add all possible combinations to the List
        while (count > 1) {
            int size = strings.size();
            for (int i = newCycle; i < size; i++) {
                for (int j = 0; j < combinations.length; j++) {
                    strings.add(strings.get(i) + combinations[j]);
                }
            }
            newCycle = size;
            count--;
        }
        //cheking array with the right amount '()' for open parentheses
        for (String s : strings.subList(newCycle, strings.size())) {
            if (closedBrackets(s)) {
                System.out.println(s);
                answer++;
            }
        }
        return answer;
    }

    //cheking a string for open parentheses
    static boolean closedBrackets(String str) {
        int opened = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                opened++;
            } else if (str.charAt(i) == ')') {
                if (opened == 0) {
                    return false;
                }
                opened--;
            }
        }
        return opened == 0;
    }
}

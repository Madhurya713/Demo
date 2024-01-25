package com.example.demo.service;

import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test {
    public static void main(String[] args) {
        String str= "madhurya kumar";
        Map<Character, Long> collect = str.chars().mapToObj(value -> (char) value).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for (Map.Entry<Character, Long> s:collect.entrySet()) {
            System.out.println(s);
        }
    }
}

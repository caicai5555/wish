package com.foundation.dao.util;

/**
 * Created by lcy on 2016/12/7.
 */
public class test {
//    public static void main(String[] args) {
//        System.out.print(fact(3));
//    }
//
//    static  int fact(int n){
//        if(n<=1)
//            return  1;
//        else
//            return  n*fact(n-1);
//    }

    public static void main(String args[]) {
        System.out.println(test.test());
        int x = 3;
        x = x<<3;
        System.out.println(x);
    }

    public static String test(){
        try {
            throw new Exception();
        } catch(Exception e) {
            System.out.println("catch");
            return "return";
        } finally {
            System.out.println("finally");
            return "return in finally";
        }
    }





}

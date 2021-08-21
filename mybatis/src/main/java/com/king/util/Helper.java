package com.king.util;




import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @program: leetcode
 * @description:
 * @author: King
 * @create: 2021-07-07 21:04
 */
public class Helper {
    public static int[] getArrays(int... nums) {
        return nums;
    }

    public static String[] getArrays(String... s) {
        return s;
    }

    public static int[][] getArrays(int[]... arr) {
        return arr;
    }

    public static List<String> getList(String... s) {
        return Arrays.asList(s);
    }

    public static void printf(Object o) {
        System.out.print(o);
    }

    public static void print() {
        printf('\n');
    }
    /**
     * https://www.cnblogs.com/fjdingsd/p/5272242.html
     *
     * @param o
     */
    public static void print(Object o) {

        if (isArray(o)) {
            List<Object> coll = new ArrayList<>();
            int length = Array.getLength(o);
            for (int i = 0; i < length; i++) {
                Object value = Array.get(o, i);
                if (isArray(value)) {
                    print(value);
                } else {
                    coll.add(value);
                }
            }
            if (!coll.isEmpty()) {
                Helper.print(coll);
            }
        } else {
            System.out.println(o);
        }

    }

//    public static void print(int[]... s) {
//        print(Arrays.deepToString(s));
//    }

    public static void print(Object o, int n) {
        if (isArray(o)) {
            Collection<Object> coll = new ArrayList<>();
            int length = Array.getLength(o);
            for (int i = 0; i < length; i++) {
                Object value = Array.get(o, i);
                coll.add(value);
            }
            print(toString(coll.toArray(), n));
        } else {
            print("这不是数组 !");
            print(o);
        }
    }

    public static void print(Object[] o, int l) {

        for (int i = 1; i < o.length; ++i) {
            System.out.print(o[i - 1] + ", ");
            if (i % l == 0) {
                System.out.print('\n');
            }
        }
        System.out.println(o[o.length - 1]);

    }


    public static void print(Object[]... o) {
        print(Arrays.deepToString(o));
    }


    public static void print(String[]... s) {
        print(Arrays.deepToString(s));
    }

    public static void print(char[]... chars) {
        print(Arrays.deepToString(chars));
    }

    public static void print(List<List<Integer>> o) {
        for (List<Integer> l : o) {
            System.out.println(l);
        }
    }

    public static void printList(List<List<Integer>> l) {

        for (Object o : l) {
            if (o.getClass().getSimpleName().endsWith("List")) {
                printList((List<List<Integer>>) o);
            } else {
                System.out.print(o + " ");
            }
        }
        System.out.println();
    }

    /**
     * 返回类中的所有变量名
     *
     * @param obj
     */
    public static void getName(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields)
            System.out.println("变量名：" + field.getName());
    }

    public static void printFieldNames(Object obj, Object... foos) {
        List<Object> fooList = Arrays.asList(foos);
        for (Field field : obj.getClass().getFields()) {

            System.out.println(field.getName());

        }
    }

    /**
     * 对象是否为数组对象
     *
     * @param obj 对象
     * @return 是否为数组对象，如果为{@code null} 返回空指针
     */
    public static boolean isArray(Object obj) {
        if (null == obj) {
            throw new NullPointerException("Object check for isArray is null");
        }
//        反射 获得类型
        return obj.getClass().isArray();
    }

    /**
     * 数组分页
     *
     * @param o 数组
     * @param l 每页数据量
     * @return
     */
    private static String toString(Object[] o, int l) {
        if (o == null)
            return "null";
        int iMax = o.length - 1;
        if (iMax == -1)
            return "[]";
        StringBuilder b = new StringBuilder();
        b.append("[");
        for (int i = 1; i < o.length; ++i) {
            b.append(o[i - 1]).append(", ");
            if (i % l == 0) {
                b.append('\n');
            }
        }
        b.append(o[iMax]).append("]").append("\n");
        return b.toString();
    }

    /**
     * 获取变量数据类型
     *
     * @param o
     * @return
     */
    public static String getType(Object o) {
        return o.getClass().getSimpleName();
    }

    public static void main(String[] args) {
        print(getType("aa"));
    }

}

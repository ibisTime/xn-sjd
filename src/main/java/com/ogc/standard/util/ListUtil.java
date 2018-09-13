package com.ogc.standard.util;

import java.util.LinkedHashSet;
import java.util.List;

public class ListUtil {

    public static List<String> removeDuplicate(List<String> list) {
        LinkedHashSet<String> set = new LinkedHashSet<String>(list.size());
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }

    // public static void main(String[] args) {
    // List<String> test = new ArrayList<String>();
    // test.add("1");
    // test.add("2");
    // test.add("2");
    // test = removeDuplicate(test);
    // System.out.println(test);
    // }
}

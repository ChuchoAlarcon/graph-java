package test;

import data.structure.ListLinked;

public class TestListLinked {

    static void testAddHeadList() {
        ListLinked<Integer> list = new ListLinked<>();
        list.addHead(5);
        list.addHead(8);
        list.addHead(15);
        list.addHead(0);
        list.addHead(10);
        System.out.println(list);
    }

    static void testAddTailList() {
        ListLinked<Integer> list = new ListLinked<>();
        list.addTail(5);
        list.addTail(8);
        list.addTail(15);
        list.addTail(0);
        list.addTail(10);
        System.out.println(list);
    }

    static void testAddListStrings() {
        ListLinked<String> list = new ListLinked<>();
        list.add("Jesus");
        list.add("Janneth");
        list.add("Dilema");
        list.add("Gaby");
        list.add("Indiana");
        System.out.println(list);
    }

    static void testAddListBooleans() {
        ListLinked<Boolean> list = new ListLinked<>();
        list.add(true);
        list.add(false);
        list.add(true);
        list.add(false);
        list.add(false);
        System.out.println(list);
    }

    public static void main(String[] args) {
        // testAddHeadList();
        // testAddTailList();
        // testAddListStrings();
        testAddListBooleans();
    }
}

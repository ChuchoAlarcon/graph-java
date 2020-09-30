package test;

import data.structure.ListLinked;

public class TestListLinked {

    static void testAddHeadList() {
        ListLinked list = new ListLinked();
        list.addHead(5);
        list.addHead(8);
        list.addHead(15);
        list.addHead(0);
        list.addHead(10);
        System.out.println(list);
    }
    static void testAddTailList() {
        ListLinked list = new ListLinked();
        list.addTail(5);
        list.addTail(8);
        list.addTail(15);
        list.addTail(0);
        list.addTail(10);
        System.out.println(list);
    }

    public static void main(String[] args) {
        //testAddHeadList();
        testAddTailList();
    }
}

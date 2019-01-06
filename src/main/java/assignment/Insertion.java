package assignment;

import core.marker.Assignment;
import core.marker.AssignmentTest;
import work.MyList;
import work.MyListImpl;

import static core.util.Asserts.assertEquals;

@Assignment(name = "Проверка вставки")
public class Insertion {

    @AssignmentTest(score = 2, name = "Вставка меньшего числа элементов, чем было создано")
    public void insertLessThanCreated() {
        //Set
        MyList<String> myList = new MyListImpl<>(4);
        myList.add("abc", 0);
        myList.add("cde", 1);
        myList.add("efg", 2);
        //Verify
//        boolean abc = myList.get(0).equals("abc");
//        boolean cde = myList.get(1).equals("cde");
//        boolean efg = myList.get(2).equals("efg");
        assertEquals(myList.get(0), "abc");
        assertEquals(myList.get(1), "cde");
        assertEquals(myList.get(2), "efg");
    }
    @AssignmentTest(score = 5, name = "Вставка большего числа элементов, чем было создано")
    public void insertMoreThanCreated() {
        //Set
        MyListImpl<String> myList = new MyListImpl<>(2);
        myList.add("abc", 0);
        myList.add("cde", 1);
        myList.add("efg", 2);
        //Verify
        assertEquals(myList.get(0), "abc");
        assertEquals(myList.get(1), "cde");
        assertEquals(myList.get(2), "efg");
    }

}

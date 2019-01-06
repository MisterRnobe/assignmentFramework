package assignment;

import core.marker.Assignment;
import core.marker.AssignmentTest;
import work.MyList;
import work.MyListImpl;

import static core.util.Asserts.assertNull;

@Assignment(name = "Удаление")
public class Deletion {
    @AssignmentTest(score = 4, name = "Удаление одного элемента")
    public void shouldDelete(){
        //Setup
        MyList<String> list = new MyListImpl<>(4);
        list.add("123", 2);
        list.remove(2);
        //Verify
        assertNull(list.get(2));
    }
}

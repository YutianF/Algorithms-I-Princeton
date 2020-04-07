package list;

import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;
import java.util.Arrays;

/* for test purpose */ 
public class Test{
    public static void main(String[] args){
        List<Integer> list = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7));
        ListIterator<Integer> listIterator = list.listIterator();
        listIterator.next();
        listIterator.add(new Integer(99));
        System.out.println(list);

        

    }
}
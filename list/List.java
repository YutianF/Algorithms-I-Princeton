package list;

import java.util.Collection;
import java.util.Iterator;
import java.util.ListIterator;

public interface List<Anytype> extends Collection<Anytype>{
    Anytype get(int idx);
    Anytype set(int idx, Anytype newVal);
    void add(int idx, Anytype x);
    void remove(int idx);
    ListIterator<Anytype> ListIterator(int pos);

    /* 将一个表中所有具有偶数值的项删除 */
    public static void removeEvensVer3(List<Integer> lst){
        Iterator<Integer> itr = lst.iterator();
        while(itr.hasNext()){
            if(itr.next()%2==0){
                itr.remove();
            }
        }
    }
    
}

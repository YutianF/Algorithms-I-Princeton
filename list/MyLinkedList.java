/**
 * java的队列和栈都是把它们封装在了linkedlist里面
 */
package list;
import java.util.Queue;
import java.util.Deque;
import java.util.LinkedList;
class MyLinkedList{
    /**
     * 队列 先进先出
     */
    
    public void queueDemo(){
        Queue<String> q1 = new LinkedList<>();
        q1.offer("aa");
        q1.offer("bb");
        q1.offer("cc");

        /**
         * q1.peek() 取出队列头部的元素，但并不移除
         * q1.poll() 取出队列头部的元素，并从队列中移除
         */
        while(q1.peek()!=null){
            System.out.println(q1.poll());
        }

    }

    /**
     * 栈 先进后出 
     * 过时 shiStack<T> s = new Stack<T>(); 
     */ 
    public void stackDemo(){
        Deque<String> s1 = new LinkedList<>();
        s1.push("aa");
        s1.push("bb");
        s1.push("cc");
        while(s1.peek()!=null){
            System.out.println(s1.poll());
        }

    }

    public static void main(String[] args){
        new MyLinkedList().queueDemo();
    }
}
public class LinkedListStack<E> implements Stack<E> {

    // 私有链表对象
    private LinkedList<E> list;

    // 由于是链表栈，底层实现链表没有容基的概念，一个构造函数就够了
    public LinkedListStack() {
        list = new LinkedList<>();
    }

    // 栈里面一共有多少个元素
    @Override
    public int getSize() {
        return list.getSize();
    }

    // 看一下栈是否为空
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    // 向栈中添加一个元素，入栈
    @Override
    public void push(E e) {
        list.addFirst(e);
    }

    // 拿出栈顶的元素，出栈
    @Override
    public E pop() {
        return list.removeFirst();
    }

    // 看一下栈顶的元素是谁
    @Override
    public E peek() {
        return list.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: top [");
        res.append(list);
        res.append("]");
        return res.toString();
    }

}

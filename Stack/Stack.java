public interface Stack<E> {

    void push(E e);       // 向栈中添加一个元素，入栈

    E pop();              // 拿出栈顶的元素，出栈

    E peek();             // 看一下栈顶的元素是谁

    int getSize();        // 看一下栈里面一共有多少个元素

    boolean isEmpty();    // 看一下栈是否为空

}

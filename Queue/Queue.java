// 队列接口依旧支持泛型
public interface Queue<E> {
    void enqueue(E e);   // 入队

    E dequeue();      // 出队

    E getFront();      // 查看队首

    int getSize();       // 查看大小

    boolean isEmpty();   // 是否为空
}

public class LinkedListQueue<E> implements Queue<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node head, tail; // 头节点，尾节点
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    // 查看大小
    @Override
    public int getSize() {
        return size;
    }

    // 是否为空
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 入队
    @Override
    public void enqueue(E e) {
        // 先判断链表尾部是否为空
        // 为空的时候 头和尾在一个节点上
        if (tail == null) {
            tail = new Node(e);
            // 链表只有一个节点时，头和尾也是一个节点
            head = tail;
        } else {
            tail.next = new Node(e);
            // 新的尾节点需要维护
            tail = tail.next;
        }
        size++;
    }

    // 出队
    @Override
    public E dequeue() {
        // 首先看队列能否出队
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queueu.");
        }
        Node retNode = head;
        head = head.next;
        // 方便回收，将原始的头节点断开
        retNode.next = null;
        if (head == null) {
            // 这是原始链表只有一个元素的情况下，也需要维护一下tail
            tail = null;
        }
        size--;
        return retNode.e;
    }

    // 查看队首
    @Override
    public E getFront() {
        // 先判断队列不能为空
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }
        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Queue: front [");
        Node cur = head;
        while (cur != null) {
            res.append(cur + "-->");
            cur = cur.next;
        }
        res.append("NULL] tail");
        return res.toString();
    }

}

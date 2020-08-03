// 支持泛型
public class LinkedList<E> {
    // 节点设计成链表类中的内部类
    // 设计私有类，只有在链表数据结构内才可以访问到Node
    private class Node {
        // 设计成public 在LinkedList中可以随意访问操作 不需要设置get，set方法
        public E e;   // 存放元素
        public Node next;  // 指向Node的引用

        public Node(E e, Node next) {
            // 将用户传来的数据交给节点
            this.e = e;
            this.next = next;
        }

        // 用户只传来e
        public Node(E e) {
            this(e, null);
        }

        // 用户什么都不传
        public Node() {
            this(null, null);
        }

        // 对每一个节点设置toString方法
        @Override
        public String toString() {
            // 每一个节点，直接打印e所对应的toString
            return e.toString();
        }
    }

    // 修改为dummyHead设置虚拟头节点
    private Node dummyHead;
    // 用户不能在外部直接修改size
    private int size;  // 来记录链表中有多少个元素

    // 链表构造函数
    public LinkedList() {
        // 对于一个空的链表来说，它是存在一个节点的，这个节点就是唯一的虚拟头节点
        dummyHead = new Node(null, null);
        size = 0;
    }

    // 获取链表中元素的个数
    public int getSize() {
        return size;
    }

    // 返回链表是否为空
    public boolean isEmpty() {
        // size是否为0
        return size == 0;
    }

    // 使用虚拟头节点添加元素
    public void add(int index, E e) {
        // 需要先判断index的合法性
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed.Illegal index.");
        }

        // 此时dummyHead指向的是0元素之前一个的位置的节点
        Node prev = dummyHead;
        // 只需要遍历到index就可以了，因为是从dummyHead开始遍历的
        for (int i = 0; i < index; i++) {
            // 当前prev存的节点的下一个节点放进prev中
            // 遍历一直挪动prev位置存放下一个节点
            prev = prev.next;
        }

        prev.next = new Node(e, prev.next);
        // 插入之后维护size
        size++;
    }

    // 在链表头添加新的元素
    public void addFirst(E e) {
        add(0, e);
    }

    // 在链表的末尾添加一个新的元素e
    public void addLast(E e) {
        // 复用add()，只需要在size添加即可
        add(size, e);
    }

    // 查询操作
    public E get(int index) {
        // get之前先判断合法性
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed.Illegal index.");
        }

        // 遍历链表，是从索引为0开始的，从当前的开始遍历
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++)
            cur = cur.next;
        // 最终cur里存储的e就是需要查找的元素
        return cur.e;
    }

    // 获取链表第一个元素
    public E getFirst() {
        return get(0);
    }

    // 获取链表最后第一个元素
    public E getLast() {
        return get(size - 1);
    }

    // 链表的更新，修改
    public void set(int index, E e) {
        // 判断合法
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed.Illegal index.");
        }
        // 进行一次遍历，找到第index元素进行替换
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++)
            cur = cur.next;
        // 最终cur里存储的e等于新的e
        cur.e = e;
    }

    // 查找链表中是否存在元素e
    public boolean contains(E e) {
        // 设置cur从第一个节点开始
        Node cur = dummyHead.next;
        // 不知道循环多少次使用while
        // cur 节点不等他null的话，意味着当前cur节点是一个有效节点
        while (cur != null) {
            // cur.e是用户传来的e，返回true
            if (cur.e.equals(e))
                return true;
            // 否则就看下一个节点
            cur = cur.next;
            // 直到cur为空，说明把整个链表遍历了一遍
        }
        return false;
    }

    // 删除链表中index位置元素 返回删除的元素
    public E remove(int index) {
        // 判断合法
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed.Illegal index.");
        }

        // prev存了待删除节点之前的节点
        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        // 将删除的节点保存进retNode;
        Node retNode = prev.next;
        // 将当前的前一个元素的节点指向当前后一个节点
        prev.next = retNode.next;
        // 让删除元素的节点的指向为空
        retNode.next = null;
        // 维护size
        size--;
        // 将删除的元素返回
        return retNode.e;
    }

    // 删除第一个元素，返回删除的元素
    public E removeFirst() {
        return remove(0);
    }

    // 删除第一个元素，返回删除的元素
    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {
        // 需要遍历一边整个链表中的所有的元素
        StringBuilder res = new StringBuilder();
//        Node cur = dummyHead.next;
//        while (cur != null) {
//            res.append(cur + "->");
//            cur = cur.next;
//        }
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            res.append(cur.e + "->");
        }
        // 最后跟一个空表示达到了链表的结尾
        res.append("NULL");
        return res.toString();
    }

}


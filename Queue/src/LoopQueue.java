public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front, tail; // front指向队首索引，tail指向队列中最后一个元素的下一个位置
    private int size;  // 记录队列中元素的个数

    public LoopQueue(int capacity) {
        // 用户传进来的容基，因为循环队列需要浪费掉一个位置，为了确保期望值，capacity+1
        data = (E[]) new Object[capacity + 1];

        // 初始化成员变量
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        // 为capacity设置默认值
        this(10);
    }

    // 查看一下循环队列最多可以装在多少个元素
    public int getCapacity() {
        // 真正数组的容基是数组长度-1
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        // 当front和tail相等时为空
        return front == tail;
    }

    @Override
    public int getSize() {
        return size;
    }

    // 入队过程
    @Override
    public void enqueue(E e) {
        // 首先看一下队列是否是满的,
        if ((tail + 1) % data.length == front) {
            // 满则需要扩容
            resize(getCapacity() * 2);
        }
        // 存放一个元素
        data[tail] = e;
        // 由于是循环对了，维护tail需要取余
        tail = (tail + 1) % data.length;
        size++;
    }

    // 出队过程
    @Override
    public E dequeue() {
        // 首先看一下队列是否为空
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty dequeue.");
        }
        // 如果不为空，取出元素先保存一下
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        // 出队之后进行相应的缩容
        // size等于循环队列可以承载的容基的1/4 缩容的值不能等于0
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    // 查看队列中队首的元素是谁
    @Override
    public E getFront() {
        // 先判断队列不能为空
        if (isEmpty()) {
            throw new IllegalArgumentException("Queue is empty.");
        }
        return data[front];
    }

    private void resize(int newCapacity) {
        // 开辟一个新的数组空间 +1是因为时刻注意有意识浪费掉的一个元素
        E[] newData = (E[]) new Object[newCapacity + 1];
        // 将data中的元素放入newData中 循环队列中一共有size个元素
        for (int i = 0; i < size; i++) {
            // 原来队首的位置可能不是0，将队首放在新的空间0的位置
            // 这样newData[i]对应的不是data[i]，存咋一个font的偏移量
            // 防止越界要对length取余
            newData[i] = data[(i + front) % data.length];
        }
        data = newData; // 改变新的data，保证getCapacity的计算是正确的
        front = 0;
        // 索引从0开始，元素个数刚好是下一个可以存放位置的索引
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d,capacity = %d\n", size, getCapacity()));
        res.append("front [");
        // 遍历循环队列中的所有元素 与动态数组有很大不同
        // 第一个位置应该在front的位置
        // 队尾应该是tail-1的位置，由于是循环数组，tail可能比front小，所以i!=tail不能去到tail
        // 每一次i++ 需要变成循环的+1
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(data[i]);
            // 如果i不是最后一个元素，逗号隔开 (i + 1) % data.length不等于tail
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }
}

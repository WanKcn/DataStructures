public class ArrayQueue<E> implements Queue<E> {
    private Array<E> array;

    // 构造函数，可以让用户传进来一个容基
    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }

    // 构造函数，不传参数
    public ArrayQueue() {
        array = new Array<>();
    }


    @Override
    public int getSize() {
        // 直接返回数组的大小
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    public int getCapacity() {
        return array.getCapacity();
    }

    // 向队列中添加元素
    @Override
    public void enqueue(E e) {
        // 像数组的末尾添加元素即像队列中添加，从末尾开始
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        // 从队首取出元素，即删除数组的第一个元素
        // 取出前需要判断isEmpty()，动态数组中也已经实现
        // 可能会触发一些动态数组的缩容，不需要管，在动态数组中已经实现过
        return array.removeFirst();
    }

    // 查看队首是谁
    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d,capacity = %d\n", array.getSize(), getCapacity()));
        res.append("Queue: ");
        // 在打印输出的时候显示告诉用户队首的位置
        res.append("front [");
        for (int i = 0; i < array.getSize(); i++) {
            // 将array的每一个元素都添加到res中
            res.append(array.get(i));
            // 如果i不是最后一个元素，逗号隔开  getSize()获取元素中的个数
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        // 循环结束加上"]" 标注一个tail表示队列的末尾
        res.append("] tail");
        return res.toString();
    }
}

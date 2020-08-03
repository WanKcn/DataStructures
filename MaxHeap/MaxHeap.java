
// 规定了节点之间的可比较性
public class MaxHeap<E extends Comparable<E>> {
    // 使用了系统API 也可以使用自己写的Array类
    private Array<E> data;

    // 构造函数
    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    // heapify构造函数
    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        // 从最后一个非叶子节点开始进行下沉
        for (int i = parent(arr.length - 1); i >= 0; i--)
            siftDown(i);
    }

    // 返回堆中的元素个数
    public int size() {
        return data.getSize();
    }

    // 返回堆是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的父节点的索引
    private int parent(int index) {
        // 0索引没有父亲节点
        if (index == 0)
            throw new IllegalArgumentException("index-0 doesn't have parent");
        return (index - 1) / 2;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子点的索引
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子点的索引
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    // 辅助函数 交换索引i，j元素的位置
    public void swap(int i, int j) {
        // 索引越界处理
        if (i < 0 || i >= data.getSize() || j < 0 || j >= data.getSize())
            throw new IllegalArgumentException("Index is illegal.");
        E t = data.get(i);
        data.set(i, data.get(j));
        ;
        data.set(j, t);
    }

    // 向堆中添加元素
    public void add(E e) {
        // 在数组的末尾添加一个元素
        data.addLast(e);
        // 传入需要上浮的元素对应的索引
        siftUp(data.getSize() - 1);
    }

    private void siftUp(int k) {
        // k>0不到根节点且当前索引的比它的父亲节点还要大的情况下需要上浮
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            // 进行位置交换
            data.swap(k, parent(k));
//            swap(k, parent(k));
            // 下一轮循环的时候，当前k已经来到新的位置，即它的父节点的位置
            k = parent(k);
        }
    }

    // 看堆中的最大元素
    public E findMax() {
        // 如果堆为空，显示没有最大元素
        if (data.getSize() == 0)
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        return data.get(0);
    }

    // 取出堆中的最大值
    public E extractMax() {
        // 暂存堆中最大元素是谁
        E ret = findMax();
        // 删除堆中的最大元素
        // 将0与数组最后一个元素交换位置后删除数组末尾元素
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        // 此时最大堆性质已经破坏，需要进行下沉操作
        siftDown(0);
        return ret;
    }

    // 下沉操作
    private void siftDown(int k) {
        // 循环最极端的情况，k所处位置已经没有孩子了
        // k的左孩子所在索引比数组元素总数还要小的情况下循环
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);
            // 如果j+1小于数组大小，即存在右孩子 并且 比较左右孩子的值，右孩子较大
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0)
                j++; // 此时，j存储右孩子所对应的索引  j= rightChild(k);
            // 此时 data[j] 是左右孩子中的最大值

            // 判断k处节点与孩子中较大值进行比较，如果大于它，则结束循环
            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;
            // 否则交换位置
            data.swap(k, j);
            // 维护k进行下一轮循环，看新的k是否进行下沉
            k = j;
        }
    }

    // 取出堆中的最大元素，并替换成新的元素e
    public E replace(E e) {
        E ret = findMax();
        // 首先替换索引为0的元素为新的元素e
        data.set(0, e);
        // 接着进行下沉
        siftDown(0);
        return ret;
    }

}

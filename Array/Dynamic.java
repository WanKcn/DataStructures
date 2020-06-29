
/**
 * 将泛型改为动态数组
 */
public class Dynamic<E> {
    private E[] data;
    private int size;

    // 构造函数
    public Dynamic(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    // 无参构造，默认capacity=10
    public Dynamic() {
        this(10);
    }

    // 像数组最后添加一个新的元素
    public void addLast(E e) {
        add(size, e);
    }

    // 向整个数组头的位置插入一个新的元素
    public void addFirst(E e) {
        add(0, e);
    }

    // 在数组任意一个位置插入一个新的元素
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >=0 and index<=size.");
        if (size == data.length) {
            // 当data静态数组的空间已经满了，进行空间数组的扩容
            // 扩容的量等于现在数组的2倍
            resize(2 * data.length); // 扩容多少和当前数组是相关的，提高整体性能优势
        }
        // 如果数组满了先进行扩容，扩容之后再正常进行后面的逻辑
        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];
        data[index] = e;
        size++;
    }

    // 设计一个get方法来查看元素，取出索引为index的元素
    public E getData(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed.Index is illegal.");
        return data[index];
    }

    // 进行数组元素的更新 修改索引为index位置原素为e
    public void setData(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed.Index is illegal.");
        data[index] = e;
    }

    // 查找数组中是否包含某个元素e
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return true;
        }
        return false;
    }

    // 找到这个元素返回索引值，找不到的时候返回一个特殊的索引，-1
    public int find(E e) {  // 此处需要改动，添加的类型应该是E型
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }

    // 删除元素，设置为int，删除元素后，把删除的元素返回留给用户备用
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        E ret = data[index];  // 此处需要改动，添加的类型应该是E型
        for (int i = index + 1; i < size; i++)
            data[i - 1] = data[i];
        size--;
        data[size] = null;

        // 删除元素到一定程度，数组的空间容量缩小一半 使数组可以动态缩小
        if (size == data.length / 2) {
            resize(data.length / 2);
        }
        return ret;
    }

    // 从数组中删除掉第一个元素，返回删除的元素
    public E removeFirst() {
        return remove(0);
    }


    // 删除数组中的指定元素，只删除一个，因为在删除钱就已经知道需要删除的元素，不需要返回值
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(String.format("Array:size = %d ,capacity = %d\n", size, data.length));
        result.append('[');
        for (int i = 0; i < size; i++) {
            result.append(data[i]);
            if (i != size - 1)
                result.append(", ");
        }
        result.append(']');
        return result.toString();
    }

    //
    private void resize(int newCapacity) {
        // 首先声明一个newData的静态数组
        E[] newData = (E[]) new Object[newCapacity];
        // for循环把原来data里面存的数据都放进newData里
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        // 循环结束以后，让data指向newData
        data = newData;
    }
}

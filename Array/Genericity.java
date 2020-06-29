/**
 * 将Array数组改为支持泛型
 */
public class Genericity<E> {
    // E本质表示类型，可以随便命名 Array承放的数据类型是大E，具体的类型在具体使用时进行声明
    private E[] data; // E本质是数组中静态数组data的类型，int[]data应改为E[] data
    private int size;

    // 构造函数
    public Genericity(int capacity) {
        // 先new一个Object类型数组，然后使用E[]进行强制转换
        data = (E[]) new Object[capacity];  // 此处应该new一个E[capacity]，但是Java语法不支持泛型数组，
        size = 0;
    }

    // 无参构造，默认capacity=10
    public Genericity() {
        this(10);
    }

    // 获取数组中的元素个数
    public int getSize() {
        return size;
    }

    // 获取数组容量
    public int getCapacity() {
        return data.length;
    }

    // 返回数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 像数组最后添加一个新的元素
    public void addLast(E e) {  // 此处需要改动，添加的类型应该是E型
        add(size, e);
    }

    // 向整个数组头的位置插入一个新的元素
    public void addFirst(E e) {  // 此处需要改动，添加的类型应该是E型
        add(0, e);
    }

    // 在数组任意一个位置插入一个新的元素
    public void add(int index, E e) {  // 此处需要改动，添加的类型应该是E型
        if (size == data.length)
            throw new IllegalArgumentException("Add failed. Arrary is full.");
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >=0 and index<=size.");
        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];
        data[index] = e;
        size++;
    }

    // 设计一个get方法来查看元素，取出索引为index的元素
    public E getData(int index) {  // get函数的返回值应该是E型
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed.Index is illegal.");
        return data[index];
    }

    // 进行数组元素的更新 修改索引为index位置原素为e
    public void setData(int index, E e) {  // 此处需要改动，添加的类型应该是E型
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed.Index is illegal.");
        data[index] = e;
    }

    // 查找数组中是否包含某个元素e
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            // 需要注意的是 当传入的是E型，e和data[i]都是类对象，需要用equals进行两个类对象之间值的比较
            if (data[i].equals(e))
                return true;
        }
        return false;
    }

    // 找到这个元素返回索引值，找不到的时候返回一个特殊的索引，-1
    public int find(E e) {  // 此处需要改动，添加的类型应该是E型
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))  // 使用equals来对两个类对象之间的值进行比较
                return i;
        }
        return -1;
    }

    // 删除元素，设置为int，删除元素后，把删除的元素返回留给用户备用
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        E ret = data[index];  // 此处需要改动，添加的类型应该是E型
//        for (int i = index; i <= size; i++)
//            data[i] = data[i + 1];
        for (int i = index + 1; i < size; i++)
            data[i - 1] = data[i];
        size--;
        /*
        使用泛型的时候，数组中存储的都是一个个对象的引用
        当上述代码删除了指定元素之后，此时的data[size]还指着一个引用
        对于引用，本身存在一个空间释放的问题，Java中有自动的垃圾回收技术
        如果data[size]还存着一个对象引用的话，就不会被垃圾回收机制回收
        此时应该让data[size]等于null，
        原来的data[size]所指的对象就已经不在程序中关联任何其他的对象，自动回收机制就会快速回收
         */
        // 这种对象通常术语 loitering objects 闲散的，垃圾回收技术并不能回收，但是它本身已经没有用了
        // loitering objects != memory leak （内存泄漏）
        data[size] = null;  // 这句话不是必须的，当在添加一个新数据的时候，对象就会换为新的元素，原来的对象就会被垃圾回收
        return ret;
    }

    // 从数组中删除掉第一个元素，返回删除的元素
    public E removeFirst() {
        return remove(0);
    }

    // 从数组中删除掉最后一个元素，返回删除的元素
    public E removeLast() {
        return remove(size - 1);
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

}

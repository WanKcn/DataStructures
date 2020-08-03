public interface Set<E> {
    void add(E e); // 添加元素 不能添加重复元素

    void remove(E e);  // 删除元素

    boolean contains(E e); // 查看集合是否包含某个元素

    int getSize();  // 获得集合元素个数

    boolean isEmpty(); // 判断集合是否为空
}

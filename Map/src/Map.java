// 支持泛型，支持两个键和值
public interface Map<K, V> {
    void add(K key, V value);

    V remove(K key); // 删除后将key所对应的value返回给用户

    boolean contains(K key); // 查询是否包含

    V get(K key); // 获取键的值

    void set(K key, V newValue); // 修改键值对

    int getSize(); // 获取大小

    boolean isEmpty(); // 是否为空

}

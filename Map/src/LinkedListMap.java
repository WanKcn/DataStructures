import java.util.ArrayList;

public class LinkedListMap<K, V> implements Map<K, V> {
    // 定义节点的内部类
    private class Node {
        public K key;  // key与value成对放在node里
        public V value;
        public Node next;

        // 构造方法
        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key) {
            this(key, null, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + ":" + value.toString();
        }
    }

    private Node dummyHead; // 虚拟头节点
    private int size;

    // 构造函数
    public LinkedListMap() {
        // key,value都为null
        dummyHead = new Node();
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node getNode(K key) {
        // 指向整个链表的第一个节点
        Node cur = dummyHead.next;
        while (cur != null) {
            // 如果cur的键等于要找的键，找到jiedian
            if (cur.key.equals(key))
                return cur;
            cur = cur.next;
        }
        // 没有找到 返回null
        return null;
    }

    @Override
    public boolean contains(K key) {
        // 返回找到的节点不为null
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        // 先找到key所对应的节点
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void add(K key, V value) {
        // 首先确认一下当前的映射中是否已经有了key key应是唯一的
        Node node = getNode(key);
        if (node == null) {
            // 如果node为空，直接在链表头添加元素
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        }
        // 否则映射中已经包含数据对，更新已有key的value
        else
            node.value = value; // node的value等于用户传来的value
    }

    @Override
    public void set(K key, V newValue) {
        // 先寻找是否包含对应的数据对key
        Node node = getNode(key);
        if (node == null)
            // 如果不存在，报错key不存在
            throw new IllegalArgumentException(key + "doesn't exist.");
        // 如果存在，更新节点的新value
        node.value = newValue;
    }

    @Override
    public V remove(K key) {
        Node prev = dummyHead;
        // 通过循环找到要删除的节点进行标记，利用prev对待删除节点的前一个节点进行标记
        while (prev.next != null) {
            if (prev.next.key.equals(key))
                break;
            prev = prev.next;
        }
        // 将待删除节点存入delNode中，prev的next就是delNode的下一个节点，使delNode.next=null进行断链；
        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--; // 维护size；
            return delNode.value; // 删除后需要将删除节点的值返回去
        }
        // 没找到删除节点时返回null
        return null;
    }


    // 映射测试 词频统计
    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        // 存储书中所有的单词
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            // 先反馈一下书中一共有多少单词
            System.out.println("Total words:" + words.size());
            // key为单词String，每个单词对应频率值为int型
            LinkedListMap<String, Integer> map = new LinkedListMap<>();
            for (String word : words) {
                // 如果map中存在word，则给它的频率加1，获取到value+1
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    // 如果不存在，则将word添加进map，初始频率默认为1
                    map.add(word, 1);
            }
            // 映射中可以看书中一共多少单词
            System.out.println("Total different words:" + map.getSize());
            // 查看书中出现某一单词出现的词频是多少次
            System.out.println("Frequency of PRIDE：" + map.get("pride"));
            System.out.println("Frequency of PREJUDICE：" + map.get("prejudice"));
        }
    }
}

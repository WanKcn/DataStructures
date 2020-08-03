import java.util.ArrayList;

// 由于是二分搜索树，对于key来说必须时刻比较的
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        public K key;  // key与value成对放在node里
        public V value;
        public Node left, right;

        // 构造方法
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    // 包含一个根节点和size
    private Node root;
    private int size;

    // 初始化构造
    public BSTMap() {
        root = null;
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

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    // 向以node为根的二分搜索树中添加键值对(key,value)，递归算法
    // 返回插入新的键值对节点后二分搜索树的根
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }
        // 如果没有递归到底，只对当前node的key进行比较，小于0往左子树添加，大于0往右子树添加
        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0 这种情况下更新value
            node.value = value;

        return node;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key) {
        // node为null时，没有找到节点
        if (node == null)
            return null;

        // 如果等于0说明找到了key所在节点，直接返回该node
        if (key.compareTo(node.key) == 0)
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left, key); // 小于0去左子树找
        else //  if (key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null; // 如果有为true，无false
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + "doesn't exist.");
        node.value = newValue;
    }

    // 删除操作
    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        // 如果节点左子树为空，则将当前节点是最小值删除，它的右子树变为左子树
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    // 删除键所对应的节点同时返回键所对应的值
    @Override
    public V remove(K key) {
        // 先在二分搜索树中找一下是否存在该删除的节点
        Node node = getNode(root, key);
        // 如果找到调用递归函数删除节点
        if (node != null) {
            root = remove(root, key);
            // 删除完成之后返回key所对应的value
            return node.value;
        }
        // 否则删除的key不在映射中
        return null;
    }

    // 删除掉以node为根的二分搜索树中键为key的节点，递归算法
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, K key) {
        if (node == null)
            return null;
        // 比较key与node.key的值到具体的子树中进行删除
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        }
        else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {
            // key.compareTo(node.key) == 0
            // 待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            // 待删除节点右子树为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 待删除节点左右子树均不为空
            // 先找到比待删除节点大的最小节点，右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right); // 声明新的Node保存右子树最小节点
            successor.right = removeMin(node.right); // 后继节点的右子树等于右子树中删除掉最小节点
            successor.left = node.left; // 左子树等于待删除节点的左子树
            // 此时node节点已经没用了，将这颗节点与二分搜索树脱离关系
            node.left = node.right = null;
            // 这里不需要维护size--，因为在removeMin中已经维护过一次size--
            return successor; // 最后直接返回新的根是后继节点
        }
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        // 存储书中所有的单词
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            // 先反馈一下书中一共有多少单词
            System.out.println("Total words:" + words.size());
            // key为单词String，每个单词对应频率值为int型
            BSTMap<String, Integer> map = new BSTMap<>();
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

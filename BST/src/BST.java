import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 支持泛型，这个类型必须拥有可比较性
public class BST<E extends Comparable<E>> {
    // 对应的节点类
    private class Node {
        public E e;
        public Node left;
        public Node right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    // 需要一个根节点
    private Node root;
    // 记录二分搜索树存储多少个元素
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    // 当前存储了多少个元素
    public int getSize() {
        return size;
    }

    // 查看当前二分搜索树是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 向二分搜索树中添加一个新的元素
    public void add(E e) {
        root = add(root, e);
    }

    // 像以node为根的二分搜素树插入新的元素e
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, E e) {

        // 递归终止条件
        // node为空时，直接返回node,即对于一个空的二叉树来说，插入一个新节点，这颗二分搜素树的根是这个节点本身
        if (node == null) {
            size++;
            return new Node(e);
        }

        // 递归调用层

        // 当前的插入元素比node元素小，向node的左子树中插入元素e
        // 为了让整个二叉树发生改变，在node的左子树中插入元素e的结果可能是变化的，让node的左子树接住这个变化
        if (e.compareTo(node.e) < 0)
            // 如果node.left为空，则这一次add操作就会返回一个新节点，然后node.left赋值新的节点
            node.left = add(node.left, e);
        else if (e.compareTo(node.e) > 0)
            // 上述同理，当右侧为空，先返回一个新节点，然后让右子树等于这个新节点。
            node.right = add(node.right, e);

        // 插入新节点以后，二分搜索树的根还是node，根据函数定义的功能语意将其返回
        return node;
    }

    // 二分搜索树的查询是否包含元素e
    public boolean contains(E e) {
        return contains(root, e);
    }

    // 以node为根节点的二分搜索树中是否包含元素e，递归算法。
    private boolean contains(Node node, E e) {
        // 终止情况，node为空
        if (node == null)
            return false;

        // 找到返回true，未找到根据判断去左右子树寻找
        if (e.compareTo(node.e) == 0)
            return true;
        else if (e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else
            return contains(node.right, e);
    }

    // 二分搜索树的前序遍历 用户调用的不需要传参数
    public void preOrder() {
        // 用户的初始调用只需要对root进行调用
        preOrder(root);
    }

    // 前序遍历以node为根的二分搜索树，递归算法
    private void preOrder(Node node) {
        // 首先递归终止条件 如果node为空没得遍历
        if (node == null)
            return;

        // 将node中存储的元素打印输出出来
        System.out.print(node.e + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    // 前序遍历的非递归实现
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        // 初始的时候，将根节点压入栈
        stack.push(root);
        // 在循环中，每一次stack不为空时候，需要相应的访问节点
        while (!stack.isEmpty()) {
            // 声明当前访问的节点cur 把栈顶元素拿出来放进cur里
            Node cur = stack.pop();
            // 对于当前要访问的节点进行打印
            System.out.print(cur.e + " ");

            // 访问cur之后要依次访问cur的左子树和右子树
            // 由于整个栈是后入先出，所以压入先压入右子树
            // 压入之前需要判断是否为空，为空不需呀压入栈中
            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }


    // 中序遍历
    public void inOrder() {
        inOrder(root);
    }

    // 中序遍历以node为根的二分搜索树，递归算法
    private void inOrder(Node node) {
        // 首先递归终止条件 如果node为空没得遍历
        if (node == null)
            return;

        // 先访问左子树
        inOrder(node.left);
        // 将node中存储的元素打印输出出来
        System.out.print(node.e + " ");
        inOrder(node.right);
    }

    // 后序遍历
    public void posOrder() {
        posOrder(root);
    }

    // 后序遍历以node为根的二分搜索树，递归算法
    private void posOrder(Node node) {
        if (node == null)
            return;

        posOrder(node.left);
        posOrder(node.right);
        System.out.print(node.e + " ");
    }

    // 二分搜索时的层序遍历
    public void levelOrder() {
        // 借助队列进行
        // Java Queue本质是一个接口，真正实现需要选择一个底层的数据结构
        Queue<Node> q = new LinkedList<>();
        // 首先添加根节点
        q.add(root);
        while (!q.isEmpty()) {
            // 声明当前遍历节点 它是队列的出队元素
            Node cur = q.remove();
            System.out.print(cur.e + " ");

            // 左右孩子不为空添加进队列
            if (cur.left != null)
                q.add(cur.left);
            if (cur.right != null)
                q.add(cur.right);
        }
    }

    // 寻找二分搜索树的最小元素
    public E minimum() {
        // 如果整棵树元素个数为0
        if (size == 0)
            throw new IllegalArgumentException("BST is empty.");
        // 返回递归调用所对应的元素值e
        return minimum(root).e;
    }

    // 返回以node为根的二分搜索树的最小值的节点
    private Node minimum(Node node) {
        // 向左走不动的时候，就找到了最小值
        if (node.left == null)
            return node;
        // 如果不为空，看它的左孩子相应的最小值
        return minimum(node.left);
    }

    // 寻找二分搜索树的最大元素
    public E maximum() {
        // 如果整棵树元素个数为0
        if (size == 0)
            throw new IllegalArgumentException("BST is empty.");
        // 返回递归调用所对应的元素值e
        return maximum(root).e;
    }

    // 返回以node为根的二分搜索树的最大值的节点
    private Node maximum(Node node) {
        // 向右走不动的时候，就找到了最大值
        if (node.right == null)
            return node;
        // 如果不为空，看它的右孩子相应的最大值
        return maximum(node.right);
    }

    // 从二分搜索树中删除最小值并将其返回
    public E removeMin() {
        // 直接接收查找最小值的函数
        E ret = minimum();
        // 删除逻辑 从以root为根的二分搜索树中删除最小值，之后返回了新二分搜素树的根节点
        // 因为removeMin有返回值用root接收
        root = removeMin(root);
        return ret;
    }

    // 删除以node为根的二分搜索树的最小节点
    // 返回删除节点后新二分搜索树的根
    private Node removeMin(Node node) {
        // node.left为null说明是最小值的时候
        if (node.left == null) {
            // 先保存一下当前节点的右子树
            Node rightNode = node.right;
            // 即将删除的节点的right等于空 即将这颗节点从二叉树脱离
            node.right = null;
            size--;
            // rightNode变为新的根
            return rightNode;
        }

        // 删除掉对应的左子树所对应的最小值，
        // 让node.left等于结果才能真正改变二分搜索树的结构
        node.left = removeMin(node.left);
        return node;
    }

    // 从二分搜索树中删除最大值并将其返回
    public E removeMax() {
        // 直接接收查找最小值的函数
        E ret = maximum();
        // 删除逻辑 从以root为根的二分搜索树中删除最大值，之后返回了新二分搜素树的根节点
        // 因为removeMax有返回值用root接收
        root = removeMax(root);
        return ret;
    }

    // 删除以node为根的二分搜索树的最大节点
    // 返回删除节点后新二分搜索树的根
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    // 从二分搜索树中删除元素e的节点
    public void remove(E e) {
        // 删除掉元素e之后得到的新的二分搜索树的根返回
        root = remove(root, e);
    }

    // 递归算法删除以node为根的二分搜索树中元素为e的节点
    // 返回删除节点后新的二分搜索树的根
    private Node remove(Node node, E e) {
        // 没有找到节点
        if (node == null)
            return null;

        // 小于当前的node.e,继续去左子树找
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        }
        // 大于当前的node.e,继续去右子树找
        else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        }
        // e == node.e
        else {
            // 存在三种情况
            // 待删除节点的左子树为空的情况
            if (node.left == null) {
                // 先保存一下当前节点的右子树
                Node rightNode = node.right;
                // 即将删除的节点的right等于空 即将这颗节点从二叉树脱离
                node.right = null;
                size--;
                // rightNode变为新的根
                return rightNode;
            }
            // 待删除节点的右子树为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            // 待删除节点的左右子树都不为空的情况
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

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        // 根节点 - 左子树 - 右子树
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 递归函数传入三个参数，遍历的根节点、遍历的当前深度、字符串对象
    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        // 如果当前节点不为空，直接访问
        res.append(generateDepthString(depth) + node.e + "\n");
        // 递归的访问节点的左右子树
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);


    }

    // 表达深度的字符串 用"--"来表达深度
    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }


}

public class Main {

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for (int n : nums) {
            bst.add(n);
        }

        //         5
        //        / \
        //       3   6
        //      / \   \
        //     2   4   8

        // 前序遍历
        bst.preOrder();
        System.out.println();
        bst.preOrderNR();
        System.out.println();

        // 中序遍历
        bst.inOrder();
        System.out.println();

        // 后序遍历
        bst.posOrder();
        System.out.println();

        // 层序遍历
        bst.levelOrder();
        System.out.println();
        // 最大值，最小值
        int a = bst.maximum();
        System.out.println(a);
        bst.minimum();
        int b = bst.minimum();
        System.out.println(b);
        // 测试删除最小值
        bst.removeMin();
        bst.inOrder();
        System.out.println();
        // 测试删除最大值
        bst.removeMax();
        bst.inOrder();
        System.out.println();

        // 15,6,17,3,9,16,23,7,12,20,25,8,11,10,14
        //
        //              15
        //           /      \
        //          6       17
        //        /  \      /  \
        //       3    9    16   23
        //           / \        / \
        //          7   12     20  25
        //           \  / \
        //           8 11 14
        //             /
        //            10

        //         根节点
        //        /     \
        //      左子树  右子树
        BST<Integer> bst2 = new BST<>();
        int[] nums2 = {15, 6, 17, 3, 9, 16, 23, 7, 12, 20, 25, 8, 11, 10, 14};
        for (int n : nums2) {
            bst2.add(n);
        }
        bst2.preOrder();
        System.out.println();
        bst2.inOrder();
        System.out.println();
        bst2.posOrder();


    }
}

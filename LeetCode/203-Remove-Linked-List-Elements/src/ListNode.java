public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    public ListNode(int[] arr) {
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException("arr can not be empty");
        // val 等于第一个元素
        this.val = arr[0];

        // 把数组中元素创建成一个个新的ListNode接在前一个节点上
        ListNode cur = this;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;  // 挪位置
        }
        // 之后this就是用循环创建的链表的头节点
    }

    // 返回以当前节点为头节点的字符串
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            res.append(cur.val + "-->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();

    }
}

// 使用虚拟头节点
class Solution3 {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);  // 随便给一个，永远不会访问到
        dummyHead.next = head;  // 不需要对第一个节点进行特殊处理

        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val)
                prev.next = prev.next.next;
            else
                prev = prev.next;
        }
        return dummyHead.next;
    }
}
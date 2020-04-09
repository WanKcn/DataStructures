class Solution2 {
    public ListNode removeElements(ListNode head, int val) {

        // 如果头节点是待删除元素,需要删除
        // 使用一个循环删除，存在头节点后一个元素也是需要删除的
        while (head != null && head.val == val) {  // 头节点不能为空
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        // 从中间删,此时head一定不是待删除元素，head后一个节点可能是带删除元素
        ListNode prev = head;  // prev为待删除元素前一个元素
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                // 否则的话不用删除，往回偏移
                prev = prev.next;
            }
        }
        return head;
    }
}
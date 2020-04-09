// 不创建虚拟头节点
class Solution {
    public ListNode removeElements(ListNode head, int val) {

        // 如果头节点是待删除元素,需要删除
        // 使用一个循环删除，存在头节点后一个元素也是需要删除的
        while (head != null && head.val == val) {  // 头节点不能为空
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;  // 断开链接
        }

        if (head == null) {
            return null;
        }

        // 从中间删,此时head一定不是待删除元素，head后一个节点可能是带删除元素
        ListNode prev = head;  // prev为待删除元素前一个元素
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            } else {
                // 否则的话不用删除，往回偏移
                prev = prev.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 6, 3, 4, 5, 6};
        ListNode list = new ListNode(arr);
        System.out.println(list);

        ListNode res = (new Solution()).removeElements(list, 6);
        System.out.println(res);
    }
}


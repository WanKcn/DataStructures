public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> linkdelist = new LinkedList<>();
        // 为链表添加0-4这5个元素
        for (int i = 0; i < 5; i++) {
            linkdelist.addFirst(i);
            System.out.println(linkdelist);
        }

        // 测试2的位置添加100
        linkdelist.add(2,100);
        System.out.println(linkdelist);

        // 测试是否存在元素3
        boolean res = linkdelist.contains(3);
        System.out.println(res);

        // 测试删除元素
        linkdelist.remove(1);
        System.out.println(linkdelist);

        // 删除最后一个元素
        linkdelist.removeLast();
        System.out.println(linkdelist);

    }
}

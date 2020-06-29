public class DynamicTest {
    public static void main(String[] args) {
        // 实例化的时候不赋值，默认容量是10
        Dynamic<Integer> arr = new Dynamic<>();
        // 往数字中添加10个元素先填充满
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);

        // 在索引1的位置插入元素55
        arr.add(3, 55);
        // 当添加55添加以后，此时数组将会有11个元素，超出了容量范围，进行扩容，此时的数组容量是20
        System.out.println(arr);

        // 删除元素4
        arr.removeElement(4);
        System.out.println(arr);

        // 删除第一个元素
        arr.removeFirst();
        // 此时删除了两个元素之后还剩下9个，数组容量减一半缩容，此时为10
        System.out.println(arr);


    }
}

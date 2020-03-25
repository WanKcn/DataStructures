public class Main {

    public static void main(String[] args) {
        // write your code here
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        // 声明数组的时候具有初始值
        int[] scores = new int[]{100, 99, 66};
        for (int i = 0; i < scores.length; i++) {
            System.out.println(scores[i]);
        }

        scores[0] = 102;  // 可以通过索引的方式修改数组
        // foreach形式 在数组中把每一个数字都当作score打印出来
        // 数组拥有可遍历可迭代的能力
        for (int score : scores) {
            System.out.println(score);
        }
    }
}
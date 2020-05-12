import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words1 = new ArrayList<>();
        // 调用文件类将傲慢与偏见的文本单词都存进words1中
        if (FileOperation.readFile("pride-and-prejudice.txt", words1)) {
            // 打印这本书一共有多少个单词
            System.out.println("Total words:" + words1.size());

            BSTSet<String> set1 = new BSTSet<>();
            for (String words : words1)
                set1.add(words); // 底层的二分搜索树忽略重复，所以重复单词不会添加进集合中
            System.out.println("Total different words:" + set1.getSize());
        }

        System.out.println(); // 换行

        System.out.println("A Tale Of Two Cities");
        ArrayList<String> words2 = new ArrayList<>();
        // 调用文件类将双城记的文本单词都存进words1中
        if (FileOperation.readFile("a-tale-of-two-cities.txt", words2)) {
            // 打印这本书一共有多少个单词
            System.out.println("Total words:" + words2.size());

            BSTSet<String> set2 = new BSTSet<>();
            for (String words : words2)
                set2.add(words); // 底层的二分搜索树忽略重复，所以重复单词不会添加进集合中
            System.out.println("Total different words:" + set2.getSize());
        }
    }
}

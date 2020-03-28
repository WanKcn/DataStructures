/**
 * 自定义一个学生类进行测试
 */
public class Student {
    private String name;
    private int score;

    public Student(String stuName, int stuScore) {
        this.name = stuName;
        this.score = stuScore;
    }

    @Override
    public String toString() {
        return String.format("Student(name:%s,score:%d)", name, score);
    }

    public static void main(String[] args){
        Genericity<Student> arr = new Genericity<>();
        // 添加三个学生的信息并打印
        arr.addLast(new Student("WenRuo",60));
        arr.addLast(new Student("FuHong",100));
        arr.addLast(new Student("JiangLH",89));

        System.out.println(arr);
    }
}

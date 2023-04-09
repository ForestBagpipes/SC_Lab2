package P2;
public class Person {
    private String name;

    // Abstraction function:
    //   TODO name: 表示人的名字，用String类型
    // Representation invariant:
    //   TODO name：name不可和别人重复
    // Safety from rep exposure:
    //   TODO 使用防御性拷贝，防止表示泄露,并用private修饰name

    public Person(String name){
        this.name = name;
    }

    public String getName(){
        String a = new String(name);
        return a;
    }
}
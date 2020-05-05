public class Test {
    public static void main(String args[]) {
        Person person = new Person("R. Johnson");
        System.out.println(person.getNextId());
    }
}

class Person {
    private static long nextId = 1;

    long id;
    String name;

    public Person(String name) {
        this.id = nextId;
        this.name = name;
        this.nextId++;
    }

    public long getNextId() {
        return nextId;
    }
}

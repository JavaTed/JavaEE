public class ClassWithFields {
    @Save
    private int a;
    @Save
    private long b_b;
    @Save
    private double c;
    @Save
    private String name;

    public ClassWithFields() {
    }

    public ClassWithFields(int a, long b, double c, String name) {
        this.name = name;
        this.a = a;
        this.b_b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        return "ClassWithFields{" +
                "a=" + a +
                ", b_b=" + b_b +
                ", c=" + c +
                ", name='" + name + '\'' +
                '}';
    }
}

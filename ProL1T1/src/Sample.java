
public class Sample {

    public void someOtherMethod(int a, int b){
        System.out.println(b + ", " + a);
    }

    @SampleAnnotation(a=11,b=22)
    public void twoParamsMethod(int a, int b){
        System.out.println(a + ", " + b);
    }
}

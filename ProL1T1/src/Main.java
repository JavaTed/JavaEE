import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
    private static SampleAnnotation sa;
    public static void invokeSampleMethod(Method t, Object obj)    {
        try {
            t.invoke(obj,(sa = t.getAnnotation(SampleAnnotation.class)).a(),sa.b());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Sample obj = new Sample();

        Arrays.stream(obj.getClass().getDeclaredMethods())
                .filter(t -> t.isAnnotationPresent(SampleAnnotation.class))
                .findFirst()
                .ifPresent(t -> invokeSampleMethod(t,obj));

    }
}

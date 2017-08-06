import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class TCSaver {

    private static void tryToInvoke(Object obj, Method m, String par){
        try {
            System.out.println("Method name="+m.getName());
            m.invoke(obj,par);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static void doSave(Object obj){
        Class<?> ctc = obj.getClass();
        System.out.println("Class "+ctc.getName());
        if (!ctc.isAnnotationPresent(SaveTo.class)){
            System.out.println("Saving is impossible. Annotation SaveTo is absent for class "+ctc.getName());
            return;
        }
        System.out.print("Annotation SaveTo is present");

        String fileName = ctc.getAnnotation(SaveTo.class).fileName();
        System.out.println(" with parameter fileName = "+fileName);

        Arrays.stream(ctc.getMethods())
                .filter(t->t.isAnnotationPresent(Saver.class))
                .findFirst()
                .ifPresent(t->tryToInvoke(obj,t,fileName));
    }
}

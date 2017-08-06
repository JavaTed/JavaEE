import java.lang.annotation.*;
@Target(value=ElementType.METHOD)
@Retention(value= RetentionPolicy.RUNTIME)
public @interface SampleAnnotation {
    int a();
    int b();
}

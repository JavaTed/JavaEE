import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class FieldSerializationService<T> {
    private StringBuilder buffer = new StringBuilder();

    public StringBuilder getBuffer() {
        return buffer;
    }

    private void serializeField(T obj,Field f){
        try {
            System.out.println(f.getType()+" "+f.getName()+" = "+f.get(obj));
            buffer.append(f.getName()+"=");

            if (f.getType() == int.class) {
                buffer.append(f.getInt(obj));
            } else if (f.getType() == double.class){
                buffer.append(f.getDouble(obj));
            } else if (f.getType() == java.lang.String.class){
                buffer.append(((String)f.get(obj)).replaceAll("([;])","\\\\$1"));
            } else if (f.getType() == long.class){
                buffer.append(f.getLong(obj));
            }
            buffer.append(";");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void setFieldAccessible(Field ff){
        if (Modifier.isPrivate(ff.getModifiers())) {ff.setAccessible(true);}
    }

    public void serialize(T obj){
        buffer = new StringBuilder("");

        Arrays.stream(obj.getClass().getDeclaredFields())
                .filter(t -> t.isAnnotationPresent(Save.class))
                .peek(this::setFieldAccessible)
                .forEach(t-> serializeField(obj,t));
    }

    private void deserializeField(T obj,String f,String value){
        try {
            Field of = obj.getClass().getDeclaredField(f);
            setFieldAccessible(of);
            if (of.getType() == int.class) {
                of.setInt(obj,Integer.parseInt(value));
            } else if (of.getType() == double.class){
                of.setDouble(obj,Double.parseDouble(value));
            } else if (of.getType() == java.lang.String.class){
                of.set(obj,value.replaceAll("\\\\;",";"));
            } else if (of.getType() == long.class){
                of.setLong(obj,Long.parseLong(value));
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public T deserialize(Class<? extends T> dcl) throws IllegalAccessException, InstantiationException {
         T obj = (T)dcl.newInstance();

         StringParser xx = new StringParser(buffer.toString());
         xx.getParList().forEach((k,t)-> deserializeField(obj,k,t));

        return (T)obj;

    }

}

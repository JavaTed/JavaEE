public class Main {
    private static FieldSerializationService<ClassWithFields> ofs = new FieldSerializationService<>();

    public static void main(String[] args) {
        ClassWithFields cl = new ClassWithFields(2,475555,8.54545,"Some; String;");

        ofs.serialize(cl);

        System.out.println("Buffer="+ofs.getBuffer());

        try {
            System.out.println(ofs.deserialize(cl.getClass()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }


    }
}

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XMLService<T> {
    private String path = "c:\\JavaCourse\\";
    private JAXBContext jaxbContext;
    private File xmlFile;

    public XMLService(Class<? extends T> cl) {
        try {
            this.jaxbContext = JAXBContext.newInstance(cl);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    private boolean isResourcePrepared(String fn, boolean checkIfExists){
        xmlFile = new File(path+fn);
        if (!xmlFile.exists()&&checkIfExists){
            System.out.println("File "+fn+" is absent");
            xmlFile = null;
            return false;
        }
        return true;
    }
    public T unmarshall(String fn){
        if (!isResourcePrepared(fn,true))
            return null;
        try {
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            T lst = (T) unmarshaller.unmarshal(xmlFile);
            System.out.println("Loaded");
            return lst;
        } catch (JAXBException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void marshall(T list, String fn){
        if (!isResourcePrepared(fn,false))
            return;
        try {
            Marshaller unmarshaller = jaxbContext.createMarshaller();
            unmarshaller.marshal(list,xmlFile);
            System.out.println("Saved");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}

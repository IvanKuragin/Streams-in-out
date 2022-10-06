import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopXmlReader {
    protected static Document configDoc;
    protected static boolean loadBoolean;
    protected static boolean saveBoolean;
    protected static boolean logBoolean;

    public static void docReader () throws SAXException, ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        try {
            configDoc = builder.parse(new File("shop.xml"));
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

    public static void loadConfig () {
        NodeList nodeList = configDoc.getElementsByTagName("load");
        String text = nodeList.item(0).getTextContent();
        String[] text1 = text.split("\n");
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(text1));
        if (list.get(1).equals("        true")) {
            loadBoolean = true;
        }
    }

    public static void saveConfig () {
        NodeList nodeList = configDoc.getElementsByTagName("save");
        String text = nodeList.item(0).getTextContent();
        String[] text1 = text.split("\n");
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(text1));
        if (list.get(1).equals("        true")) {
            saveBoolean = true;
        }
    }

    public static void logConfig () {
        NodeList nodeList = configDoc.getElementsByTagName("log");
        String text = nodeList.item(0).getTextContent();
        String[] text1 = text.split("\n");
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(text1));
        if (list.get(1).equals("        true")) {
            logBoolean = true;
        }
    }
}

package csci2020u.client.subMenu;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class About {

    /*
     * Function inspired by Module 6 'URLOpenData - BikeShare: Downloading and parsing an XML file' Tutorial
     *
     * -Modified to parse through a File instead of a URL Connection, and add its contents
     *  to a GridPane.
     *
     * @param f: the file that is being parsed
     * @param b: the BorderPane that the file's contents is being added to
     * */
    public void parseXML(File f, BorderPane b){

        //Initializing String to be displayed in About Scene
        String aboutResult = "";

        try {
            // Building the Document
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            Document document = docBuilder.parse(f);
            document.getDocumentElement().normalize();

            // Information for each Student
            NodeList studentItemNodes = document.getElementsByTagName("student");

            for(int i = 0; i < studentItemNodes.getLength(); i++){

                Element itemElement = (Element) studentItemNodes.item(i);

                // reading the properties of each of student object
                String name = "Name: " + getTagValue("name", itemElement) + "\n";
                String id = "Student ID: " + getTagValue("id",itemElement) + "\n";
                String email = "Email: "+getTagValue("email", itemElement) + "\n\n";

                //adding strings to final String
                aboutResult += name + id + email;
            }

            //Information in General
            NodeList infoNode = document.getElementsByTagName("info");

            for(int i = 0; i < infoNode.getLength(); i++){

                Element itemElement = (Element) infoNode.item(i);

                //reading the software Description
                String softwareDescription = getTagValue("software-description",itemElement) + "\n";

                //adding strings to final String
                aboutResult += softwareDescription;
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        //Adding Final String to Center of BorderPane
        b.setCenter(new Label(aboutResult));
    }

    /*
     * Function derived from Module 6 'URLOpenData - BikeShare: Downloading and parsing an XML file' Tutorial
     * */
    private static String getTagValue(String tagName, Element e){
        NodeList tags = e.getElementsByTagName(tagName);
        if(tags.getLength() > 0){
            return tags.item(0).getTextContent();
        }
        return null;
    }
}
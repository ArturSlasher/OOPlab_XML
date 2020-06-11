package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Gistagramm");
        Group root = new Group();
        primaryStage.setScene(new Scene(root, 600, 300));
        primaryStage.show();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new File("src/MyXml"));

        Element element = document.getDocumentElement();
        printElements(element.getChildNodes(), root);
    }

    static void printElements(NodeList nodeList, Group root){
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (nodeList.item(i) instanceof Element) {
                if(((Element) nodeList.item(i)).hasAttribute("label")) {
                    Rectangle rectangle = new Rectangle();
                    rectangle.setX(50+i*11);
                    rectangle.setY(50);
                    rectangle.setWidth(20);
                    rectangle.setHeight(10*Double.parseDouble(((Element) nodeList.item(i)).getAttribute("label")));
                    rectangle.setFill(Color.RED);
                    root.getChildren().addAll(rectangle);
                }
            }
            if (nodeList.item(i).hasChildNodes()){
                printElements(nodeList.item(i).getChildNodes(), root);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

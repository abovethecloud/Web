package tests;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TestXML {

	public static void main(String[] args) {
		
		File fileXML = new File("books.xml");
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = dbFactory.newDocumentBuilder();
			
			Document doc = builder.parse(fileXML);
			
			NodeList bookList = doc.getElementsByTagName("book");
			
			for (int i = 0; i < bookList.getLength(); i++) {
				
				Node book = bookList.item(i);
				NodeList bookInfo = book.getChildNodes();
				
				for (int j = 0; j < bookInfo.getLength(); j++) {
					Node info = bookInfo.item(j);
					
					String nodeName = bookInfo.item(j).getNodeName();
					String textContext = info.getTextContent();
					
					System.out.println(nodeName+" ["+textContext+"]");
				}
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Verificare documentazione excetption
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package no.komplett.solidify;

import java.io.File;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

public class XmlParser {
	
	public static void main(String [] args){
		try {
			
			String inputFile = "SalesData";
			
			Document doc = getDocument("src/no/komplett/solidify/data/" + inputFile + ".xml");
			
			System.out.println(new XMLOutputter().outputString(doc));
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Document getDocument(String absolutePath) throws JDOMException, IOException{

		System.out.println("current dir: " + new File(".").getAbsolutePath());

		try {
			return new SAXBuilder().build(new File(absolutePath));
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
}

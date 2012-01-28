package no.komplett.solidify;

import java.io.File;
import java.io.FileNotFoundException;
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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Document getDocument(String path) throws JDOMException, IOException{

		System.out.println("Document path: " + path);

		try {
			return new SAXBuilder().build(new File(path));
		} catch (JDOMException e) {
			throw e;
		} catch (IOException e) {
			throw new FileNotFoundException("File " + path + " not found");
		}
	}
}

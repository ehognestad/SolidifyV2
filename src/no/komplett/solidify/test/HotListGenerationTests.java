package no.komplett.solidify.test;

import java.io.IOException;

import no.komplett.solidify.HotlistGenerator;
import no.komplett.solidify.service.DataHelper;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.testng.Assert;

public class HotListGenerationTests {

	private static String hotlistDocPath = "%s_Hotlist_%s_Category_%s.xml";

	public static void main(String[] args){
		try {
			testHotlistResults();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void testHotlistResults() throws JDOMException, IOException{
		String path = String.format(hotlistDocPath, "310", "Month", "1");
		Document doc = DataHelper.getDocument("output/hotlists/" + path);
		Assert.assertEquals(doc.getRootElement().getChildren().size() == HotlistGenerator.TOP_NUMBER_OF_PRODUCTS, true, "This result list should have " + HotlistGenerator.TOP_NUMBER_OF_PRODUCTS + " elements");

		System.out.println("testHotlistResults test ok");
	}

}

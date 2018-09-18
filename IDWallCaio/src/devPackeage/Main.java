package devPackeage;

import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		
		/*
		String text = DEFAULT_INPUT_TEXT;
		int limit = DEFAULT_LIMIT;
		Boolean justify = DEFAULT_JUSTIFY;
		String firstTest = firstTest( text, limit, justify);
		*/
		CaioCrawler crawler = new CaioCrawler();
		crawler.getSubsInfo("askreddit;worldnews;cats");
		
	
	}
	
	public static String firstTest ( String text, int limit, Boolean justify ) {
		
		CaioFormater cf = new CaioFormater();
		String formatedText = cf.formatText(text, limit, justify);
		
		// Print input data for display on console.
        System.out.println("Inputs: ");
        System.out.println("Text: " + text);
        System.out.println("Limit: " + limit);
        System.out.println("Should justify: " + justify);
        System.out.println("=========================");
		System.out.println();
		System.out.println(formatedText);
		return formatedText;
	}
	
	
}
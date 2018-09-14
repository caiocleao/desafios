package devPackeage;

import java.io.IOException;

public class Main {
	
	private static final String DEFAULT_INPUT_TEXT = "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.\n" +
            "\n" +
            "And God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day.";
    private static final Integer DEFAULT_LIMIT = 34;
    private static final Boolean DEFAULT_JUSTIFY = true;
	
	public static void main(String[] args) throws IOException {
		
		/*
		String text = DEFAULT_INPUT_TEXT;
		int limit = DEFAULT_LIMIT;
		Boolean justify = DEFAULT_JUSTIFY;
		String firstTest = firstTest( text, limit, justify);
		*/
		CaioCrawler crawler = new CaioCrawler();
		crawler.developmentMethod();
		
	
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
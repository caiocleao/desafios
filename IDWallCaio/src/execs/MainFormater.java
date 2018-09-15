package execs;

import formater.CaioFormater;

public class MainFormater {
	
	public static void main(String[] args) {
	
		CaioFormater formater = new CaioFormater();
		String text = "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.\n" +
	            "\n" +
	            "And God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day.";
		int limit = 40;
		boolean justify = true;
		
		String formatedText = formater.formatText(text, limit, justify);
		System.out.println(formatedText);
		
	}
		
}

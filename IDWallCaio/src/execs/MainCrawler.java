package execs;

import java.io.IOException;
import crawler.CaioCrawler;

public class MainCrawler {

	public static void main(String[] args) throws IOException {
		
		System.out.println("teste");
		
		CaioCrawler crawler =  new CaioCrawler();
		String subList = "askreddit;worldnews;cats";
		String subInfo = crawler.getSubsInfo(subList);
		
		System.out.println(subInfo);
		
	}
	
}

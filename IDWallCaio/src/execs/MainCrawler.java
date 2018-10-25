package execs;

import java.io.IOException;
import java.util.List;

import crawler.CaioCrawler;
import objects.Post;

public class MainCrawler {

	/* executable file created for method testing without Telegram integration. */
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("teste");
		
		CaioCrawler crawler =  new CaioCrawler();
		String subList = "askreddit;worldnews;cats";
		List<Post>[] subInfo = crawler.getSubsInfo(subList);
		
		for ( List<Post> sub : subInfo ) {
			
			for (int i = 0; i < sub.size(); i++ ) {
				System.out.println(sub.get(i).getFormatedInfo());
			}
						
		}
		
		
	}
	
}

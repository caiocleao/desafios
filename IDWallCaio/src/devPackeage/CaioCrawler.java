package devPackeage;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CaioCrawler {

	final String redditLink = "https://old.reddit.com/r/";
	
	public void getSubsInfo ( String listOfSubs ) throws IOException {
		
		String[] subs = listOfSubs.split(";");
		for ( String sub : subs ) {
			getRedditInfo(sub);
			// Print to better devide info on subs.
			System.out.println("\n############################################################# \n ");
		}
		
	}
	
	
	public void getRedditInfo( String subReddit ) throws IOException {
		
		String subLink = redditLink + subReddit;
		Document doc = Jsoup.connect(subLink).get();
		String subInfo = subReddit;
		Element siteTable = doc.getElementById("siteTable");
		Elements posts = siteTable.children();
		
		// Needed:  upvotes, subreddit, título da thread, link para os comentários da thread, link da thread.
		
		for ( Element post : posts ) {
			
			if (  !post.className().equalsIgnoreCase("clearleft")) {
				
				Elements voteSection = post.getElementsByClass("midcol");
				Elements titleSection = post.getElementsByClass("entry");
				String numberOfVotes = getNumberOfUpVotes(voteSection);
				int votesInt = 0;
				
				// Routine to deal with posts without votes.
				try {
					votesInt = Integer.parseInt(numberOfVotes);
				} catch ( Exception e ) {
					votesInt = 0;
				}
				//String subInfo = getSubReddit(post);
				
				// Since we have the current sub-reddit passed as a parameter, we don't need to get it from the HTML.
				// Method build in case it is needed.
				String titleName = getPostTitle(titleSection);
				String linkToThread = getThreadLink(titleSection);
				String linkToThreadComments = getCommentLink(titleSection);
				
				if ( votesInt >= 5000 ) {
				
					System.out.println("Subreddit: " + subInfo);
					System.out.println("Number of upvotes: " + numberOfVotes);
					System.out.println("Thread title: " + titleName);
					System.out.println("Link to thread: " + linkToThread);
					System.out.println("Link to thread Comments: " + linkToThreadComments);
				
					System.out.println("\n-------------------------------------------------------------\n");
					
				}
				
			}
			
		}
		
	}
	
	public String getCommentLink ( Elements titleSection ) {
		
		String commentsLink = "";
		
		for ( Element topMatter : titleSection ) {
			
			// topMatter and tittle.
			Elements topMatterElements =  topMatter.getElementsByClass("flat-list");
			commentsLink = topMatterElements.select("a").first().attr("href");
			
			
		}
		
		return commentsLink;
	}
	
	public String getThreadLink ( Elements titleSection ) {
		
		String threadLink = "";

		for ( Element topMatter : titleSection ) {
			
			// topMatter and tittle.
			Elements topMatterElements =  topMatter.getElementsByClass("title");
			threadLink = topMatterElements.select("a").first().attr("href");
			
		}
		
		return threadLink;
	}
	
	public String getSubReddit( Element post ) {
		
		String subReddit = post.attr("data-subreddit-prefixed");
		return subReddit;
	}
	
	public String getPostTitle( Elements titleSection ) {
		
		String titleName = "";
		for ( Element topMatter : titleSection ) {
			
			// topMatter and tittle.
			Elements topMatterElements =  topMatter.getElementsByClass("title");
			titleName = topMatterElements.text();
			
		}
		return titleName;
	}
	
	public String getNumberOfUpVotes ( Elements voteSection ) {
		
		int innerCounter = 0;
		String numberOfUpVotes = "";
		for ( Element parts : voteSection ) {
			
			Elements votes = parts.getElementsByClass("score unvoted");
			for ( Element typeVote : votes ) {
				numberOfUpVotes = typeVote.attr("title");	
			}
			
			if( innerCounter > 3 ) {
				break;
			}
			innerCounter++;
		}
		
		return numberOfUpVotes;
	}
	
}

package crawler;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CaioCrawler {

	final String redditLink = "https://old.reddit.com/r/";
	
	public String getSubsInfo ( String listOfSubs ) throws IOException {
		
		String[] subs = listOfSubs.split(";");
		String formatedInfo = "";
		for ( String sub : subs ) {
			formatedInfo += getRedditInfo(sub);
			// Dividing info on subs.
			formatedInfo += ("#############################################################\n\n");
		}
		
		return formatedInfo;
		
	}
	
	
	public String getRedditInfo( String subReddit ) throws IOException {
		
		String formatedInfo = "";
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
				
				// Since we have the current sub-reddit passed as a parameter, we don't need to get it from the HTML.
				// Method build in case it is needed.
				//String subInfo = getSubReddit(post);
				
				String titleName = getPostTitle(titleSection);
				String linkToThread = getThreadLink(titleSection);
				String linkToThreadComments = getCommentLink(titleSection);
				
				if ( votesInt >= 5000 ) {
				
					formatedInfo += ("Subreddit: " + subInfo + "\n");
					formatedInfo +=  ("Number of upvotes: " + numberOfVotes + "\n");
					formatedInfo += ("Thread title: " + titleName + "\n");
					formatedInfo += ("Link to thread: " + linkToThread + "\n");
					formatedInfo +=("Link to thread Comments: " + linkToThreadComments + "\n");
					formatedInfo += ("\n------------------------------------------------------------- \n\n");
					
				}
				
			}
			
		}
	
		return formatedInfo;
		
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

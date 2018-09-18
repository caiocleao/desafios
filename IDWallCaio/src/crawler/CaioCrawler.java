package crawler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import objects.Post;

public class CaioCrawler {

	final String redditLink = "https://old.reddit.com/r/";
	
	public List<Post>[] getSubsInfo ( String listOfSubs ) throws IOException {
		
		String[] subs = listOfSubs.split(";");
		int subsLength = subs.length;
		List<Post>[] posts = new LinkedList[subsLength];
		int subIndex = 0;
		for ( String sub : subs ) {
			
			posts[subIndex] = getRedditInfo(sub);
			//formatedInfo += ("#############################################################\n");
			subIndex++;
			
		}
		
		return posts;
		
	}
	
	/** Returns a List of Posts contaning over 5000 upvotes **/
	public List<Post> getRedditInfo( String subReddit ) throws IOException {
		
		String formatedInfo = "";
		String subLink = redditLink + subReddit;
		Document doc = Jsoup.connect(subLink).get();
		String subInfo = subReddit;
		Element siteTable = doc.getElementById("siteTable");
		Elements posts = siteTable.children();
		// Needed:  upvotes, subreddit, título da thread, link para os comentários da thread, link da thread.
		List<Post> postList =  new LinkedList<Post>();
		
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
				
					// Formats the information for ease of use.
					formatedInfo += ("Subreddit: " + subInfo + "\n");
					formatedInfo +=  ("Number of upvotes: " + numberOfVotes + "\n");
					formatedInfo += ("Thread title: " + titleName + "\n");
					formatedInfo += ("Link to thread: " + linkToThread + "\n");
					formatedInfo +=("Link to thread Comments: " + linkToThreadComments + "\n");
					formatedInfo += ("\n");
					
					/* 
					Creates and sets object containing information formatted and 
					stand-alone and adds it to the list.
					 */
					Post currentPost = new Post();
					currentPost.setSubReddit(subInfo);
					currentPost.setThreadComments(linkToThreadComments);
					currentPost.setThreadLink(linkToThread);
					currentPost.setThreadTitle(titleName);
					currentPost.setUpvotes(numberOfVotes);
					currentPost.setFormatedInfo(formatedInfo);
					postList.add(currentPost);
					formatedInfo = "";
					// Once we have array/list of posts returning, remove this String setup.
					
				}
				
			}
			
		}
		
		return postList;
		
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
			
			// For reddit posts containing links to other reddit posts, we need to add the base URL.
			if ( !threadLink.contains("http://") && !threadLink.contains("https://") ) {
				threadLink = "https://old.reddit.com" + threadLink;
			}
			
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
		
		String numberOfUpVotes = "";
		
		for ( Element parts : voteSection ) {
			
			Elements votes = parts.getElementsByClass("score unvoted");
			for ( Element typeVote : votes ) {
				numberOfUpVotes = typeVote.attr("title");	
			}
			
		}
		
		return numberOfUpVotes;
	}
	
}

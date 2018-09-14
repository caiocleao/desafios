package devPackeage;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CaioCrawler {

	public void developmentMethod () throws IOException {
		
		System.out.println("Hello Crawler");
		String redditLink = "https://old.reddit.com/";
		
		Document doc = Jsoup.connect(redditLink).get();
		String title = doc.title();
		
		Element siteTable = doc.getElementById("siteTable");
		// HTML Structure is Post - Div for identation - Post - Div for identation ...
		// Always skip one div to get posts.
		Elements posts = siteTable.children();
		int counter = 0;
		int innerCounter = 0;
		
		// Needed:  upvotes, subreddit, título da thread, link para os comentários da thread, link da thread.
		// class="midcol unvoted"
		
		for ( Element post : posts ) {
			
			Elements postParts = post.children();
			Elements voteSection = post.getElementsByClass("midcol");
			Elements titleSection = post.getElementsByClass("entry");
			String numberOfVotes = getNumberOfUpVotes(voteSection);
			String subReddit = getSubReddit(post);
			String titleName = getPostTitle(titleSection);
			String linkToThread = getThreadLink(titleSection);
			String linkToThreadComments = getCommentLink(titleSection);
			
			System.out.println( numberOfVotes + " " + subReddit + " " + titleName + " " + linkToThread + " " + linkToThreadComments);
			
			
			if ( counter > 3 ) {
				break;
			}
			counter++;
			innerCounter = 0;
		}
		
		
		System.out.println("breakpoint");
		
		
	}
	
	public String getCommentLink ( Elements titleSection ) {
		
		String commentsLink = "";
		
		for ( Element topMatter : titleSection ) {
			
			// topMatter and tittle.
			Elements topMatterElements =  topMatter.getElementsByClass("flat-list");
			commentsLink = topMatterElements.select("a").first().attr("href");
			
			
		}
		
		System.out.println("teste: " + commentsLink);
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
			
			System.out.println(parts.className());
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

package execs;

import java.io.IOException;
import java.util.List;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import crawler.CaioCrawler;
import objects.Post;

public class MainTelegramBot extends TelegramLongPollingBot  {

	/*
	 
	  Bot information:
	  Name:      CaioCrawlerBot
	  Username:  redditCrawlerCaioBot
	  HTTP API Token: 678126396:AAGOPqxtCHnKEWVFY3SIcejqBT1oob98rvg
	 */
	
	public static void main(String[] args) {
	
		System.out.println("Hello Bot.");
		
		ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        
        try {
           
        	telegramBotsApi.registerBot(new MainTelegramBot());
        	
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
		
	}
	
	@Override
	public String getBotUsername() { 
		return "redditCrawlerCaioBot";
	}
	
	@Override
	public String getBotToken() {
		return "678126396:AAGOPqxtCHnKEWVFY3SIcejqBT1oob98rvg";
	}
	
	@Override
	public void onUpdateReceived(Update update) {
	    // We check if the update has a message and the message has text
	    if (update.hasMessage() && update.getMessage().hasText()) {
	    	
	    	String command = update.getMessage().getText();
	    	CaioCrawler crawler = new CaioCrawler();
	    	String[] subNames = command.split(";");
	    	int indexSubNames = 0;
	    	List<Post>[] subInfo;
	    	
			try {
				subInfo = crawler.getSubsInfo(command);
				// Iterate over the subreddits
				for ( List<Post> sub : subInfo ) {
	    			// Iterate over each post with over 5k likes.
					
					if ( sub.size() == 0 ) {
						
						SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
	    		                .setChatId(update.getMessage().getChatId())
	    		                .setText("Sadly there are no hot posts on " +  subNames[indexSubNames] +" right now!");
	    			
						try {
							execute(message);
						} catch (TelegramApiException e) {
							// TODO Auto-generated catch block
	    					e.printStackTrace();
						}
					}
					
	    			for (int i = 0; i < sub.size(); i++ ) {
	    				
	    				Post currentPost = sub.get(i);
	    				SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
	    		                .setChatId(update.getMessage().getChatId())
	    		                .setText(currentPost.getFormatedInfo());
	    			
	    				try {
							execute(message);
						} catch (TelegramApiException e) {
							// TODO Auto-generated catch block
	    					e.printStackTrace();
						}
	    				
	    			}
	    			indexSubNames++;		
	    		}
				
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    	
	    	/*
	        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
	                .setChatId(update.getMessage().getChatId())
	                .setText(infoString);
	        */
	    }
	}
	
}

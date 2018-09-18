package crawler;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBot extends TelegramLongPollingBot  {

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
           
        	telegramBotsApi.registerBot(new TelegramBot());
        	
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
	        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
	                .setChatId(update.getMessage().getChatId())
	                .setText(update.getMessage().getText());
	        try {
	            execute(message); // Call method to send the message
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
}

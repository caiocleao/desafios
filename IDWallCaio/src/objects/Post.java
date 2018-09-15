package objects;

public class Post {

	private String subReddit;
	private String threadLink;
	private String threadComments;
	private String threadTitle;
	private int upvotes;
	private String formatedInfo;
	
	public String getFormatedInfo() {
		return formatedInfo;
	}

	public void setFormatedInfo(String formatedInfo) {
		this.formatedInfo = formatedInfo;
	}

	public String getSubReddit() {
		return subReddit;
	}
	
	public void setSubReddit(String subReddit) {
		this.subReddit = subReddit;
	}
	
	public String getThreadLink() {
		return threadLink;
	}
	
	public void setThreadLink(String threadLink) {
		this.threadLink = threadLink;
	}
	
	public String getThreadComments() {
		return threadComments;
	}
	
	public void setThreadComments(String threadComments) {
		this.threadComments = threadComments;
	}
	
	public String getThreadTitle() {
		return threadTitle;
	}
	
	public void setThreadTitle(String threadTitle) {
		this.threadTitle = threadTitle;
	}
	
	public int getUpvotes() {
		return upvotes;
	}
	
	public void setUpvotes( String upvotes ) {
		
		try {
			this.upvotes = Integer.parseInt(upvotes);
		} catch ( Exception e ) {
			this.upvotes = 0;
		}
		
	}
	
	
	
}

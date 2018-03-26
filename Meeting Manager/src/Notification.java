/**
 * The Notification class contains the person it was sent from as well as the notification's title and contents
 */
public class Notification {

	private Employee from;
	private String title;
	private String content;
	
	public Notification(Employee from, String title, String content) {
		super();
		this.from = from;
		this.title = title;
		this.content = content;
	}
	
	/**
	 * @return the from
	 */
	public Employee getFrom() {
		return from;
	}
	/**
	 * @param from the from to set
	 */
	public void setFrom(Employee from) {
		this.from = from;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	
}

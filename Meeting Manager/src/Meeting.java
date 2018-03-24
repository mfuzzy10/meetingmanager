import java.time.LocalDate;
import java.time.LocalTime;

public class Meeting {

	private String title;
	private String description;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;

	/**
	 * Blank Constructor for the Meeting class.
	 */
	public Meeting() {
		
		title = null;
		date = null;
		startTime = null;
		endTime = null;
		description = null;
	}
	
	/**
	 * Filled Constructor for the Meeting class.
	 * 
	 * @param date The date of the meeting.
	 * @param startTime The time the meeting starts.
	 * @param endTime The end time of the meeting.
	 * @param description A description of what the meeting is about.
	 */
	public Meeting(LocalDate date, LocalTime startTime, LocalTime endTime, String description, String title) {
		
		this.title = title;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.description = description;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}
	
	/**
	 * @param date the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	/**
	 * @return the startTime
	 */
	public LocalTime getStartTime() {
		return startTime;
	}
	
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * @return the endTime
	 */
	public LocalTime getEndTime() {
		return endTime;
	}
	
	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	
	/**
	 * @return a date that looks better for the user
	 */
	public String getRealDate() {
		return Integer.toString(date.getDayOfMonth()) + "/" + Integer.toString(date.getMonthValue()) + "/" + Integer.toString(date.getYear());
	}
}

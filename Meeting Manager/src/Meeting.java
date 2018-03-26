import java.time.LocalDateTime;
/**
 * The Meeting class contains all the information about a meeting such as its starting/ending times and the title/description of the meeting
 */
public class Meeting implements Comparable<Meeting> {

	private String title;
	private String description;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	public Meeting(String title, String description, LocalDateTime startTime, LocalDateTime endTime) {
		this.title = title;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public int compareTo(Meeting compareMeeting) {

		LocalDateTime compareStartTime = ((Meeting) compareMeeting).getStartTime();
		
		return this.startTime.compareTo(compareStartTime);
		
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
	 * @return the startTime
	 */
	public LocalDateTime getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public LocalDateTime getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	
	
	
}

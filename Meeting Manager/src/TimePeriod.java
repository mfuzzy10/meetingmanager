import java.time.LocalDateTime;
/**
 * The TimePeriod class contains two dates and can compare them
 */
public class TimePeriod implements Comparable<TimePeriod> {

	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	public TimePeriod(LocalDateTime startTime, LocalDateTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public int compareTo(TimePeriod compareTimePeriod) {

		LocalDateTime compareStartTime = ((TimePeriod) compareTimePeriod).getStartTime();
		
		return this.startTime.compareTo(compareStartTime);
		
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

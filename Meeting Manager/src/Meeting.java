/**
 * A class to store all of the details about the meeting.
 * 
 * @version 1.0
 */

import java.time.*;
import java.util.Date;

public class Meeting {

		private Date date;
		private LocalTime startTime;
		private LocalTime endTime;
		private String description;
		
		/**
		 * Blank Constructor for the Meeting class.
		 */
		public Meeting() {
			
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
		public Meeting(Date date, LocalTime startTime, LocalTime endTime, String description) {
			
			date = this.date;
			startTime = this.startTime;
			endTime = this.endTime;
			description = this.description;
		}
}

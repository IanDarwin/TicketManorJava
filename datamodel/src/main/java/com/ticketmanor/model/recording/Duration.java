package com.ticketmanor.model.recording;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;


/**
 * This class describes a duration of time.  It contains
 * the hour, minutes and seconds.
 *
 * <pre>
 *   Usage Example:
 *
 *     Duration lunchDuration = new Duration(1, 0, 0); // 1 hr, 0 mins, 0 secs
 *     System.out.println(lunchDuration);
 *
 *     Duration breakDuration = new Duration(0, 15, 30); // 0 hrs, 15 mins, 30 secs
 *     System.out.println(breakDuration);
 *
 *     Duration labDuration = new Duration(2700); // 2700 seconds = 45 minutes
 *     System.out.println(labDuration);
 *
 * </pre>
 *
 * @author 570 Development Team
 */
@Embeddable
public class Duration implements java.io.Serializable {

	private static final long serialVersionUID = -6020955281760590352L;

	/**
	 * The number of hours
	 */
	private int hours;

	/**
	 * The number of minutes
	 */
	private int minutes;

	/**
	 * The number of seconds
	 */
	private int seconds;

	/**
	 * Creates a Duration object with 0 hours, minutes and seconds.
	 */
	public Duration() {
		this(0, 0, 0);
	}

	/**
	 * Creates a Duration object with given parameter values;
	 * the three values make up the duration.
	 * @param hours Number of hours long something is
	 * @param minutes Number of hours long something is
	 * @param seconds Number of hours long something is
	 */
	public Duration(int hours, int minutes, int seconds) {
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}

	/**
	 * Creates a Duration object with given parameter values
	 * @param totalSeconds The total number of seconds
	 */
	public Duration(int totalSeconds) {
		setTotalSeconds(totalSeconds);
	}
	
	/**
	 * Returns the hours portion of the duration
	 * @return the hours portion of the duration
	 */	
	@Transient
	public int getHours() {
		return hours;
	}
	
	/**
	 * Returns the minutes portion of the duration
	 * @return the minutes portion of the duration
	 */
	@Transient
	public int getMinutes() {
		return minutes;
	}

	/**
	 * Returns the seconds portion of the duration.
	 *
	 * Note:  This is NOT the total seconds.
	 *
	 * @return The seconds portion of the duration.
	 * @see #getTotalSeconds()
	 */
	@Transient
	public int getSeconds() {
		return seconds;
	}
	
	/**
	 * Returns the total seconds
	 * @return the total seconds
	 */
	@Column(name="duration")
	public int getTotalSeconds() {
		return seconds + (60 * (minutes + (60 * hours)));
	}
	
	/**
	 * This is needed because the Duration is stored
	 * as an Embedded field inside the Track, 
	 * containing the total seconds
	 * @param totalSeconds The running time of something
	 */
	public void setTotalSeconds(int totalSeconds) {
		hours = totalSeconds / 3600;
		int rem = totalSeconds - (hours * 3600);
		minutes = rem / 60;
		seconds = rem % 60;
	}

	/**
	 * Returns a new duration object that is the sum of the
	 * supplied Duration object and current object
	 * @param aDuration Another Duration
	 * @return The sum of the durations
	 */
	public Duration add(Duration aDuration) {
		int total = getTotalSeconds() + aDuration.getTotalSeconds();
		return new Duration(total);
	}

	/**
	 * Returns a new duration object that is the difference of the
	 * supplied Duration and current object.  The difference returned is
	 * the absolute difference, so the duration will always be positive.
	 * @param aDuration Another Duration
	 * @return The difference between the two durations
	 */
	public Duration subtract(Duration aDuration) {
		int diff = getTotalSeconds() - aDuration.getTotalSeconds();
		int total = Math.abs(diff);

		return new Duration(total);
	}


	/**
	 * Returns a string representation of the Duration object:
	 * <br>
	 * Format: hh:mm:ss
	 *
	 * @return String representation of the Duration object:
	 */
	public String toString() {
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

}

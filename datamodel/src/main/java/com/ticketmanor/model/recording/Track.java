package com.ticketmanor.model.recording;

import java.io.Serializable;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


/**
 *  This class represents a track (ie a song).  This class describes
 *  the track's title and duration.
 *
 *   <pre>
 *     Usage Example:
 *
 *     Duration firstDuration = new Duration(0, 4, 30);   // hr, min, sec
 *     Track firstTrack = new Track("Tooty Fruity", firstDuration);
 *
 *   </pre>
 *
 *  @author 570 Development Team
 */
@Entity
@Table(name="Tracks")
public class Track implements Serializable {

	private static final long serialVersionUID = 7412748348094654728L;

	private int id;
	
	/**
	 *  The track title
	 */
	private String title;

	/** The track duration or running time */
	private Duration duration;
	
	/** Recording this track is part of */
	private Recording recording;

	/**
	 *  Default constructor.  Simply creates an empty track.
	 */
	public Track() {
		title = "empty";
		duration = new Duration();
	}

  	/**
	 *  Creates a Track with the given parameter values
	 * @param aTitle The title for this Track
	 * @param aDuration The running time of this track
	 */
	public Track(String aTitle, Duration aDuration) {
		title = aTitle;
		duration = aDuration;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name="product_id")
	public Recording getRecording() {
		return recording;
	}

	public void setRecording(Recording recording) {
		this.recording = recording;
	}

	/**
	 *  Returns the title of the track
	 *  @return the title of the track
	 */
	public String getTitle() {
		return title;
	}

	/**
	 *  Sets the title of the track
	 * @param aTitle The new title
	 */
	public void setTitle(String	aTitle) {
		title = aTitle;
	}

	/**
	 *  Returns the duration of the track
	 *  @return The duration of the track
	 */
	@Embedded
	public Duration getDuration() {
		return duration;
	}

	/**
	 *  Sets the duration of the track
	 * @param aDuration The new Duration
	 */
	public void setDuration(Duration aDuration) {
		duration = aDuration;
	}

	/**
	 *  Returns a string representation of the Track.  It
	 *  includes the track title and running time. <p>
	 *
	 *  The string has the following format:
	 *
	 *   <pre>
	 *    title, duration
	 *   </pre>
	 * @return A string representatino of the Track
	 */
	public String toString() {

		int totalSeconds = duration.getTotalSeconds();
		int min = totalSeconds / 60;
		int secs = totalSeconds % 60;

		String secsStr = "";

		// pad the seconds w/ leading zero if single digit
		if (secs < 10) {
			secsStr = "0";
		}

		secsStr += Integer.toString(secs);

		return title + ",  " + min + ":" + secsStr;
	}
}

package com.ticketmanor.model.recording;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

/**
 * This class represents a music recording.  It contains additional
 * data members for the artist name and a list of tracks (ie songs).
 *
 *  <pre>
 *    Usage Example:
 *       MusicRecording myRecording = new MusicRecording("John Lennon", myTrackList, "Double Fantasy",
 *  </pre>
 *
 * @author 936 Development Team
 * @author Ian Darwin modifications for standalone demo.
 */
@Entity
@Table(name="MusicRecordings")
public class MusicRecording extends Recording {

	private static final long serialVersionUID = -2657285648284489986L;

	/** The name of the artist/band */
	private String artist;

	/** The list of tracks/songs */
	private Track tracks[] = new Track[0];

	/** The recording category */
	private String category;

	/**
	 * Default constructor
	 */
	public MusicRecording() {
		// set the data members using the setter methods
	}

	/**
	 * Creates a MusicRecording object with given parameter values
	 * Too complex; should use Builder pattern here.
	 * @param theArtist the Artist
	 * @param theTrackList The list of Tracks
	 * @param theTitle The title
	 * @param thePrice The price
	 * @param theCategory The Music Category
	 * @param theImageName Filename of an image to display
	 */
	public MusicRecording(String theArtist, Track[] theTrackList,
						  String theTitle, double thePrice,
						  String theCategory, String theImageName) {

		artist = theArtist;
		tracks = theTrackList;
		super.title = theTitle;
		setPrice(thePrice);
		this.category = theCategory;
	}

	/**
	 * Creates a MusicRecording object with given parameter values
	 * @param theArtist the Artist
	 * @param theTitle the Title
	 * @param thePrice The price
	 * @param theCategory the Music Category
	 * @param theImageName filename of an image to display
	 */
	public MusicRecording(String theArtist,
						  String theTitle, double thePrice,
						  String theCategory, String theImageName) {

		this(theArtist, null, theTitle, thePrice, theCategory, theImageName);
	}

	@Override
	public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(title).append(" - ");
        buffer.append(artist).append(" (");
        buffer.append(tracks.length).append(" tracks)");
        return buffer.toString();
    }

	/**
	 * Returns the artist name
	 * @return  The artist name
	 */
	@Column(name="artist_name")
	public String getArtist() {
		return artist;
	}

	/**
	 * Sets the artist name
	 * @param artist The artist name
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * Returns the recording duration.
	 * Iterates over the list of tracks and totals all the tracks' durations.
	 * @return the recording duration.
	 */
	@Transient @Override
	public Duration getDuration() {

		if (tracks == null) {
			return new Duration(0);
		}

		int total = 0;
		for (int i=0; i < getTracks().size(); i++) {
			total += tracks[i].getDuration().getTotalSeconds();
		}

		return new Duration(total);
	}

	/**
	 * {@inheritDoc}
	 */
	public int compareTo(Object object) {

		MusicRecording recording = (MusicRecording) object;
		String targetArtist = recording.getArtist();

		return artist.compareTo(targetArtist);
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@OneToMany(mappedBy="recording", cascade=CascadeType.ALL)
	@OrderColumn(name="index_number")
	public List<Track> getTracks() {
		if (tracks == null || tracks.length == 0) {
			return new ArrayList<Track>();
		}
		return Arrays.asList(tracks);
	}

	public void setTracks(List<Track> tracks) {
		Track[] target = new Track[tracks.size()];
		this.tracks = (Track[]) tracks.toArray(target);
	}
}

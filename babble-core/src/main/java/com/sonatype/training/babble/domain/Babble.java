package com.sonatype.training.babble.domain;

import java.util.Date;

/**
 * A Babble is an utterance of inarticulate or meaningless talk or sounds.
 * Babbles are uttered by babblers (@see Babbler).
 * 
 * @author johnsmart
 */
public class Babble implements Comparable<Babble> {

	/**
	 * The text uttered by the babbler.
	 */
	private String utterence;
	private Date time;
	private Babbler babbler;

	public Babble() {
	}

	public Babble(Babbler babbler, String utterence) {
		this.utterence = utterence;
		this.setBabbler(babbler);
		this.time = new Date();
	}

	public String getUtterence() {
		return utterence;
	}

	public void setUtterence(String utterence) {
		this.utterence = utterence;
	}

	public Date getTime() {
		return (Date) time.clone();
	}

	public void setTime(Date time) {
		this.time = (Date) time.clone();
	}

	public int compareTo(Babble that) {
		final int BEFORE = 1;
		final int EQUAL = 0;
		final int AFTER = -1;

		if (this == that)
			return EQUAL;

		if (this.time.before(that.time))
			return AFTER;
		else if (this.time.after(that.time))
			return BEFORE;
		else
			return EQUAL;
	}

	public void setBabbler(Babbler babbler) {
		this.babbler = babbler;
	}

	public Babbler getBabbler() {
		return babbler;
	}

}

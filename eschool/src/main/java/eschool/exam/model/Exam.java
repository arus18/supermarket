package eschool.exam.model;

public class Exam {
	private String subject;
	private java.sql.Timestamp dateTime;
	private int duration;
	private int year;
	private String section;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public java.sql.Timestamp getDateTime() {
		return dateTime;
	}
	public void setDateTime(java.sql.Timestamp dateTime) {
		this.dateTime = dateTime;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
}

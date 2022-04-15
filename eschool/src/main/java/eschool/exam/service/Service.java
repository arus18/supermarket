package eschool.exam.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import eschool.exam.model.Exam;
import eschool.util.DbUtil;

public class Service {
	private static final String SELECT_EXAMS = "SELECT * FROM exams";
	private static final String INSERT_EXAM  = "INSERT INTO exams(subject,dateAndTime,duration,class,section) VALUES(?,?,?,?,?)";
	private static final String UPDATE_EXAM = "UPDATE exams SET dateAndTime = ? ,duration = ? WHERE subject = ? AND dateAndTime = ? AND class = ? AND section = ?";
	private static final String DELETE_EXAM = "DELETE FROM exams WHERE subject = ? AND dateAndTime = ? AND class = ? AND section = ?";
	private static final String GET_EXAMS_BY_CLASS_SECTION_SUBJECT = "SELECT * FROM exams WHERE class = ? AND section = ? AND subject = ?";
	
	public static ArrayList<Exam> getExams(){
		Connection connection = DbUtil.getConnection();
		ArrayList<Exam> exams = new ArrayList<Exam>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EXAMS);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Exam exam = new Exam();
				String subject = rs.getString("subject");
				java.sql.Timestamp dateAndTime = rs.getTimestamp("dateAndTime");
				int duration = rs.getInt("duration");
				int year = rs.getInt("class");
				String section = rs.getString("section");
				exam.setDateTime(dateAndTime);
				exam.setDuration(duration);
				exam.setSection(section);
				exam.setSubject(subject);
				exam.setYear(year);
				exams.add(exam);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exams;
	}
	public static ArrayList<Exam> getExamsByClassSectionSubject(int yr,String sec,String sub){
		Connection connection = DbUtil.getConnection();
		ArrayList<Exam> exams = new ArrayList<Exam>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_EXAMS_BY_CLASS_SECTION_SUBJECT);
			preparedStatement.setInt(1, yr);
			preparedStatement.setString(2, sec);
			preparedStatement.setString(3, sub);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Exam exam = new Exam();
				String subject = rs.getString("subject");
				java.sql.Timestamp dateAndTime = rs.getTimestamp("dateAndTime");
				int duration = rs.getInt("duration");
				int year = rs.getInt("class");
				String section = rs.getString("section");
				exam.setDateTime(dateAndTime);
				exam.setDuration(duration);
				exam.setSection(section);
				exam.setSubject(subject);
				exam.setYear(year);
				exams.add(exam);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return exams;
	}
	public static void newExam(String subject,java.sql.Timestamp dateAndTime,int duration,int yr,String section) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EXAM);
			preparedStatement.setString(1, subject);
			preparedStatement.setTimestamp(2, dateAndTime);
			preparedStatement.setInt(3, duration);
			preparedStatement.setInt(4, yr);
			preparedStatement.setString(5, section);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void updateExam(java.sql.Timestamp newDateTime,String subject,java.sql.Timestamp oldDateTime,int yr,String section,int duration) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EXAM);
			preparedStatement.setTimestamp(1, newDateTime);
			preparedStatement.setInt(2, duration);
			preparedStatement.setString(3, subject);
			preparedStatement.setTimestamp(4, oldDateTime);
			preparedStatement.setInt(5,yr);
			preparedStatement.setString(6,section);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void deleteExam(String subject,java.sql.Timestamp dateAndTime,int yr,String section) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EXAM);
			preparedStatement.setString(1, subject);
			preparedStatement.setTimestamp(2, dateAndTime);
			preparedStatement.setInt(3, yr);
			preparedStatement.setString(4, section);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

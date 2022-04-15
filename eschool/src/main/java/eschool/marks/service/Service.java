package eschool.marks.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import eschool.marks.model.Marks;
import eschool.marks.model.MarksReportBySubject;
import eschool.student.model.StudentAttendanceReport;
import eschool.util.DbUtil;

public class Service {
	
	private static final String SELECT_MARKS = "SELECT * FROM marks";
	private static final String SELECT_MARKS_BY_STUDENT_NAME = "SELECT * FROM marks WHERE name = ?";
	private static final String INSERT_MARKS  = "INSERT INTO marks(studentID,class,section,subject,marks,name) VALUES(?,?,?,?,?,?)";
	private static final String UPDATE_MARKS = "UPDATE marks SET marks = ? WHERE studentID = ? AND class = ? AND section = ? AND subject = ?";
	private static final String DELETE_MARKS = "DELETE FROM marks WHERE studentID = ? AND class = ? AND section = ? AND subject = ?";
	private static final String GET_MARKS_BY_CLASS_SECTION_SUBJECT = "SELECT * FROM marks WHERE class = ? AND section = ? AND subject = ?";
	private static final String SUBJECT_MARKS_REPORT = "SELECT name,marks FROM marks WHERE subject = ? AND class = ? AND section = ?";
	
	public static ArrayList<MarksReportBySubject> marksReport(String subject,int yr,String section){
		Connection connection = DbUtil.getConnection();
		ArrayList<MarksReportBySubject> report = new ArrayList<MarksReportBySubject>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SUBJECT_MARKS_REPORT);
			preparedStatement.setString(1, subject);
			preparedStatement.setInt(2, yr);
			preparedStatement.setString(3, section);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				MarksReportBySubject mReport = new MarksReportBySubject();
			    String name = rs.getString("name");
			    int marks = rs.getInt("marks");
				mReport.setMarks(marks);
				mReport.setName(name);
				report.add(mReport);
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
		return report;
	}
	
	public static ArrayList<Marks> getMarks(){
		Connection connection = DbUtil.getConnection();
		ArrayList<Marks> marks = new ArrayList<Marks>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MARKS);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Marks mark = new Marks();
				int studentID = rs.getInt("studentID");
				String name = rs.getString("name");
				int year = rs.getInt("class");
				String section = rs.getString("section");
				String subject = rs.getString("subject");
				int mrks = rs.getInt("marks");
				mark.setMarks(mrks);
				mark.setSection(section);
				mark.setStudentID(studentID);
				mark.setYear(year);
				mark.setSubject(subject);
				mark.setName(name);
				marks.add(mark);
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
		return marks;
	}
	public static ArrayList<Marks> getMarksByStudentName(String name){
		Connection connection = DbUtil.getConnection();
		ArrayList<Marks> marks = new ArrayList<Marks>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MARKS_BY_STUDENT_NAME);
			preparedStatement.setString(1,name);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Marks mark = new Marks();
				int studentID = rs.getInt("studentID");
				String nme = rs.getString("name");
				int year = rs.getInt("class");
				String section = rs.getString("section");
				String subject = rs.getString("subject");
				int mrks = rs.getInt("marks");
				mark.setMarks(mrks);
				mark.setSection(section);
				mark.setStudentID(studentID);
				mark.setYear(year);
				mark.setSubject(subject);
				mark.setName(nme);
				marks.add(mark);
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
		return marks;
	}
	public static ArrayList<Marks> getMarksBySectionClassSubject(int yr,String sec,String sub){
		Connection connection = DbUtil.getConnection();
		ArrayList<Marks> marks = new ArrayList<Marks>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_MARKS_BY_CLASS_SECTION_SUBJECT);
			preparedStatement.setInt(1,yr);
			preparedStatement.setString(2, sec);
			preparedStatement.setString(3, sub);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Marks mark = new Marks();
				int studentID = rs.getInt("studentID");
				String nme = rs.getString("name");
				int year = rs.getInt("class");
				String section = rs.getString("section");
				String subject = rs.getString("subject");
				int mrks = rs.getInt("marks");
				mark.setMarks(mrks);
				mark.setSection(section);
				mark.setStudentID(studentID);
				mark.setYear(year);
				mark.setSubject(subject);
				mark.setName(nme);
				marks.add(mark);
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
		return marks;
	}
	public static void newMarks(String name,int studentID,String subject,int marks,String section,int year) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MARKS);
			preparedStatement.setInt(1, studentID);
			preparedStatement.setInt(2, year);
			preparedStatement.setString(3, section);
			preparedStatement.setString(4, subject);
			preparedStatement.setInt(5, marks);
			preparedStatement.setString(6, name);
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
	public static void updateMarks(int studentID,String subject,int marks,String section,int year) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MARKS);
			preparedStatement.setInt(1, marks);
			preparedStatement.setInt(2, studentID);
			preparedStatement.setInt(3, year);
			preparedStatement.setString(4, section);
			preparedStatement.setString(5, subject);
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
	public static void deleteMarks(int studentID,String subject,String section,int year) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MARKS);
			preparedStatement.setInt(1, studentID);
			preparedStatement.setInt(2, year);
			preparedStatement.setString(3, section);
			preparedStatement.setString(4, subject);
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

package eschool.student.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import eschool.attendance.model.AttendanceReport;
import eschool.student.model.Student;
import eschool.student.model.StudentAttendanceReport;
import eschool.student.model.StudentMarksReport;
import eschool.util.DbUtil;

public class Service {
	
	private static final String SELECT_STUDENTS = "SELECT * FROM students";
	private static final String SELECT_STUDENT_BY_NAME = "SELECT * FROM students WHERE name = ?";
	private static final String INSERT_STUDENT  = "INSERT INTO students(name,DOB,address,phoneNumber,class,section) VALUES(?,?,?,?,?,?)";
	private static final String UPDATE_STUDENT = "UPDATE students SET name = ?,DOB = ?,address = ?,phoneNumber = ?,class = ?,section = ? WHERE studentID = ?";
	private static final String DELETE_STUDENT = "DELETE FROM students WHERE studentID = ?";
	private static final String GET_STUDENT_BY_CLASS_SECTION = "SELECT * FROM students WHERE class = ? AND section = ?";
	private static final String GET_STUDENT_BY_ID = "SELECT * from students WHERE studentID = ?";
	private static final String MONTHLY_STUDENT_ATTENDANCE = "SELECT date,status FROM attendance WHERE studentID = ? AND MONTHNAME(date) = ?";
	private static final String STUDENT_MARKS = "SELECT name,subject,marks FROM marks WHERE studentID = ?";
	private static final String COUNT_STUDENTS = "SELECT COUNT(*) AS count FROM students";
	
	public static int studentCount() {
		Connection connection = DbUtil.getConnection();
		int count = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(COUNT_STUDENTS);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()) {
				count = rs.getInt("count");
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
		return count;
	}
	
	public static ArrayList<StudentAttendanceReport> attendanceReport(int studentID,String month){
		Connection connection = DbUtil.getConnection();
		ArrayList<StudentAttendanceReport> report = new ArrayList<StudentAttendanceReport>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(MONTHLY_STUDENT_ATTENDANCE);
			preparedStatement.setInt(1, studentID);
			preparedStatement.setString(2, month);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				StudentAttendanceReport sReport = new StudentAttendanceReport();
				java.sql.Date date = rs.getDate("date");
				String status = rs.getString("status");
				sReport.setDate(date);
				sReport.setStatus(status);
				report.add(sReport);
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
	public static ArrayList<StudentMarksReport> marksReport(int studentID){
		Connection connection = DbUtil.getConnection();
		ArrayList<StudentMarksReport> report = new ArrayList<StudentMarksReport>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(STUDENT_MARKS);
			preparedStatement.setInt(1, studentID);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				StudentMarksReport mReport = new StudentMarksReport();
				String name = rs.getString("name");
				String subject = rs.getString("subject");
				int marks = rs.getInt("marks");
				mReport.setMarks(marks);
				mReport.setName(name);
				mReport.setSubject(subject);
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
	public static Student getStudentByID(int studentID) {
		Connection connection = DbUtil.getConnection();
		Student student = new Student();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_BY_ID);
			preparedStatement.setInt(1, studentID);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			int studID = rs.getInt("studentID");
			String phoneNumber = rs.getString("phoneNumber");
			java.sql.Date dob = rs.getDate("DOB");
			String address = rs.getString("address");
			String name = rs.getString("name");
			int year = rs.getInt("class");
			String section = rs.getString("section");
			student.setStudentID(studID);
			student.setAddress(address);
			student.setDOB(dob);
			student.setName(name);
			student.setPhoneNumber(phoneNumber);
			student.setSection(section);
			student.setYear(year);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}
	public static ArrayList<Student> getStudents(){
		Connection connection = DbUtil.getConnection();
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENTS);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				int studentID = rs.getInt("studentID");
				String phoneNumber = rs.getString("phoneNumber");
				java.sql.Date dob = rs.getDate("DOB");
				String address = rs.getString("address");
				String name = rs.getString("name");
				int year = rs.getInt("class");
				String section = rs.getString("section");
				student.setStudentID(studentID);
				student.setAddress(address);
				student.setDOB(dob);
				student.setName(name);
				student.setPhoneNumber(phoneNumber);
				student.setSection(section);
				student.setYear(year);
			    students.add(student);
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
		return students;
	}
	public static ArrayList<Student> getStudentsByName(String nme){
		Connection connection = DbUtil.getConnection();
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_NAME);
			preparedStatement.setString(1, nme);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				int studentID = rs.getInt("studentID");
				String phoneNumber = rs.getString("phoneNumber");
				java.sql.Date dob = rs.getDate("DOB");
				String address = rs.getString("address");
				String name = rs.getString("name");
				int year = rs.getInt("class");
				String section = rs.getString("section");
				student.setStudentID(studentID);
				student.setAddress(address);
				student.setDOB(dob);
				student.setName(name);
				student.setPhoneNumber(phoneNumber);
				student.setSection(section);
				student.setYear(year);
			    students.add(student);
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
		return students;
	}
	public static ArrayList<Student> getStudentsByClassSection(int y,String s){
		Connection connection = DbUtil.getConnection();
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_BY_CLASS_SECTION);
			preparedStatement.setInt(1, y);
			preparedStatement.setString(2, s);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Student student = new Student();
				int studentID = rs.getInt("studentID");
				String phoneNumber = rs.getString("phoneNumber");
				java.sql.Date dob = rs.getDate("DOB");
				String address = rs.getString("address");
				String name = rs.getString("name");
				int year = rs.getInt("class");
				String section = rs.getString("section");
				student.setStudentID(studentID);
				student.setAddress(address);
				student.setDOB(dob);
				student.setName(name);
				student.setPhoneNumber(phoneNumber);
				student.setSection(section);
				student.setYear(year);
			    students.add(student);
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
		return students;
	}
	public static void newStudent(String name, String address, String phoneNumber, java.sql.Date DOB,String section,int year) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT);
			preparedStatement.setString(1, name);
			preparedStatement.setDate(2, DOB);
			preparedStatement.setString(3,address);
			preparedStatement.setString(4,phoneNumber);
			preparedStatement.setInt(5, year);
			preparedStatement.setString(6, section);
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
	public static void updateStudent(int studentID,String name, String address, String phoneNumber, java.sql.Date DOB,String section,int year) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENT);
			preparedStatement.setString(1, name);
			preparedStatement.setDate(2, DOB);
			preparedStatement.setString(3,address);
			preparedStatement.setString(4,phoneNumber);
			preparedStatement.setInt(5, year);
			preparedStatement.setString(6, section);
			preparedStatement.setInt(7, studentID);
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
	public static void deleteStudent(int studentID) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENT);
			preparedStatement.setInt(1, studentID);
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

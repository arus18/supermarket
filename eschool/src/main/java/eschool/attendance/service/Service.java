package eschool.attendance.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import eschool.attendance.model.Attendance;
import eschool.attendance.model.AttendanceReport;
import eschool.util.DbUtil;

public class Service {
	
	private static final String SELECT_ATTENDANCE = "SELECT * FROM attendance";
	private static final String INSERT_ATTENDANCE  = "INSERT INTO attendance(studentID,date,status,class,section) VALUES(?,?,?,?,?)";
	private static final String UPDATE_ATTENDANCE = "UPDATE attendance SET status = ? WHERE studentID = ? AND date = ?";
	private static final String DELETE_ATTENDANCE = "DELETE FROM attendance WHERE studentID = ? AND date = ?";
	private static final String GET_ATTENDANCE_BY_CLASS_SECTION_DATE = "SELECT * FROM attendance WHERE class = ? AND section = ? AND date = ?";
	private static final String ATTENDANCE_REPORT_BY_CLASS_SECTION = "SELECT students.name,attendance.status FROM students,attendance WHERE students.studentID = attendance.studentID AND attendance.class = ? AND attendance.section = ? AND attendance.date = ?";
	private static final String ATTENDANCE_REPORT_YEAR = "SELECT COUNT(*) AS count FROM attendance WHERE status = 'Present' AND monthname(attendance.date) = ?";
	
	public static ArrayList<AttendanceReport> attendanceReport(int yr,String section,java.sql.Date dte){
		Connection connection = DbUtil.getConnection();
		ArrayList<AttendanceReport> report = new ArrayList<AttendanceReport>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(ATTENDANCE_REPORT_BY_CLASS_SECTION);
			preparedStatement.setInt(1, yr);
			preparedStatement.setString(2, section);
			preparedStatement.setDate(3, dte);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				AttendanceReport aReport = new AttendanceReport();
				String name = rs.getString("name");
				String status = rs.getString("status");
				aReport.setName(name);
				aReport.setStatus(status);
				report.add(aReport);
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
	public static ArrayList<Attendance> getAttendance(){
		Connection connection = DbUtil.getConnection();
		ArrayList<Attendance> attendance = new ArrayList<Attendance>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ATTENDANCE);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Attendance att = new Attendance();
				int studentID = rs.getInt("studentID");
				java.sql.Date date = rs.getDate("date");
				String status = rs.getString("status");
				int year = rs.getInt("class");
				String section = rs.getString("section");
				att.setDate(date);
				att.setSection(section);
				att.setStatus(status);
				att.setStudentID(studentID);
				att.setYear(year);
				attendance.add(att);
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
		return attendance;
	}
	public static ArrayList<Attendance> getAttendanceBySectionClassDate(int yr,String sec,java.sql.Date dte){
		Connection connection = DbUtil.getConnection();
		ArrayList<Attendance> attendance = new ArrayList<Attendance>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(GET_ATTENDANCE_BY_CLASS_SECTION_DATE);
			preparedStatement.setInt(1, yr);
			preparedStatement.setString(2, sec);
			preparedStatement.setDate(3, dte);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Attendance att = new Attendance();
				int studentID = rs.getInt("studentID");
				java.sql.Date date = rs.getDate("date");
				String status = rs.getString("status");
				int year = rs.getInt("class");
				String section = rs.getString("section");
				att.setDate(date);
				att.setSection(section);
				att.setStatus(status);
				att.setStudentID(studentID);
				att.setYear(year);
				attendance.add(att);
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
		return attendance;
	}
	public static void newAttendance(int studentID,java.sql.Date date,String status,int yr,String section) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ATTENDANCE);
			preparedStatement.setInt(1, studentID);
			preparedStatement.setDate(2, date);
			preparedStatement.setString(3, status);
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
	public static void updateAttaendance(String status,int studentID,java.sql.Date date) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ATTENDANCE);
			preparedStatement.setString(1,status);
			preparedStatement.setInt(2,studentID);
			preparedStatement.setDate(3, date);
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
	public static void deleteAttendance(int studentID,java.sql.Date date) {
		Connection connection = DbUtil.getConnection();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ATTENDANCE);
			preparedStatement.setInt(1, studentID);
			preparedStatement.setDate(2, date);
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
	private static int attendanceCountMonth(String month) {
		Connection connection = DbUtil.getConnection();
		PreparedStatement preparedStatment;
		int count = 0;
		try {
			preparedStatment = connection.prepareStatement(ATTENDANCE_REPORT_YEAR);
			preparedStatment.setString(1, month);
			ResultSet rs = preparedStatment.executeQuery();
			rs.next();
			count = rs.getInt("count");
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
	public static JSONObject attendanceReportYear() {
		JSONObject json = new JSONObject();
		String[] months = {"january","february","march","april","may","june","july","august","september","october","november","december"};
		for(int i = 0; i < 12; i++) {
			try {
				json.put(months[i], attendanceCountMonth(months[i]));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return json;
	}
}

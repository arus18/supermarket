package eschool.student.crud;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eschool.student.service.Service;

/**
 * Servlet implementation class UpdateStudent
 */
@WebServlet("/UpdateStudent")
public class UpdateStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int studentID = Integer.parseInt(request.getParameter("studentID"));
		String name = request.getParameter("name");
		java.sql.Date dob = java.sql.Date.valueOf(request.getParameter("DOB"));
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		int year = Integer.parseInt(request.getParameter("class"));
		String section = request.getParameter("section");
		Service.updateStudent(studentID, name, address, phone, dob, section, year);
		response.sendRedirect("students-all.jsp");
	}

}

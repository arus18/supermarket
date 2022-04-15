package eschool.student.function;

import java.io.IOException;
import java.util.ArrayList;

import eschool.student.service.*;
import eschool.student.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchStudentByClassSection
 */
@WebServlet("/SearchStudentByClassSection")
public class SearchStudentByClassSection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchStudentByClassSection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int year = Integer.parseInt(request.getParameter("class"));
		String section = request.getParameter("section");
		ArrayList<Student> list = Service.getStudentsByClassSection(year, section);
		request.setAttribute("searchStudentByClassSection", list);
		RequestDispatcher rd = request.getRequestDispatcher("students-all.jsp");
		rd.forward(request, response);
	}

}

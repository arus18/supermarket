package eschool.student.function;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eschool.student.model.Student;
import eschool.student.service.Service;

/**
 * Servlet implementation class SearchStudentByName
 */
@WebServlet("/SearchStudentByName")
public class SearchStudentByName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchStudentByName() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String search = request.getParameter("search");
		ArrayList<Student> list = Service.getStudentsByName(search);
		request.setAttribute("searchStudentByName", list);
		RequestDispatcher rd = request.getRequestDispatcher("students-all.jsp");
		rd.forward(request, response);
	}

}

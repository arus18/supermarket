package eschool.marks.crud;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eschool.marks.service.Service;

/**
 * Servlet implementation class UpdateMarks
 */
@WebServlet("/UpdateMarks")
public class UpdateMarks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMarks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("studentID"));
		int year = Integer.parseInt(request.getParameter("class"));
		String section = request.getParameter("section");
		String subject = request.getParameter("subject");
		int marks = Integer.parseInt(request.getParameter("marks"));
		Service.updateMarks(id, subject, marks, section, year);
		response.sendRedirect("marks.jsp");
	}

}

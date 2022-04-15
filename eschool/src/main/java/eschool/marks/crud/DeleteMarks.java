package eschool.marks.crud;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eschool.marks.service.Service;

/**
 * Servlet implementation class DeleteMarks
 */
@WebServlet("/DeleteMarks")
public class DeleteMarks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMarks() {
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
		Service.deleteMarks(id, subject, section, year);
		response.sendRedirect("marks.jsp");
	}

}

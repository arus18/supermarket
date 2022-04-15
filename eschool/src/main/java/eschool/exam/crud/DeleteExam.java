package eschool.exam.crud;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eschool.exam.service.Service;

/**
 * Servlet implementation class DeleteExam
 */
@WebServlet("/DeleteExam")
public class DeleteExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteExam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tempDateTime = request.getParameter("datetime").replace("T"," ");
		System.out.println(tempDateTime);
		java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(tempDateTime);
		int year = Integer.parseInt(request.getParameter("class"));
		String section = request.getParameter("section");
		String subject = request.getParameter("subject");
		Service.deleteExam(subject, dateTime, year, section);
		response.sendRedirect("online-exams.jsp");
	}

}

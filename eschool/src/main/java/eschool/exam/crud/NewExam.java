package eschool.exam.crud;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import eschool.exam.service.*;
/**
 * Servlet implementation class NewExam
 */
@WebServlet("/NewExam")
public class NewExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewExam() {
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
		String subject = request.getParameter("subject");
		int duration = Integer.parseInt(request.getParameter("duration"));
		String datetime = request.getParameter("datetime").replace("T"," ");
		java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(datetime);
		Service.newExam(subject, dateTime, duration, year, section);
		response.sendRedirect("online-exam-add.jsp");
	}

}

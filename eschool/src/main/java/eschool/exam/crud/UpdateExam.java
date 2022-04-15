package eschool.exam.crud;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eschool.exam.service.Service;

/**
 * Servlet implementation class UpdateExam
 */
@WebServlet("/UpdateExam")
public class UpdateExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateExam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String tempOldDateTime = request.getParameter("olddatetime").replace("T"," ");
		String tempDateTime = request.getParameter("datetime").replace("T"," ");
		java.sql.Timestamp oldDateTime = java.sql.Timestamp.valueOf(tempOldDateTime);
		java.sql.Timestamp dateTime = java.sql.Timestamp.valueOf(tempDateTime);
		int year = Integer.parseInt(request.getParameter("class"));
		String section = request.getParameter("section");
		String subject = request.getParameter("subject");
		int duration = Integer.parseInt(request.getParameter("duration"));
		Service.updateExam(dateTime, subject, oldDateTime, year, section,duration);
		response.sendRedirect("online-exams.jsp");
	}

}

package eschool.exam.function;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eschool.exam.model.Exam;
import eschool.exam.service.Service;

/**
 * Servlet implementation class SearchExamByClassSectionSubject
 */
@WebServlet("/SearchExamByClassSectionSubject")
public class SearchExamByClassSectionSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchExamByClassSectionSubject() {
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
		ArrayList<Exam> list = Service.getExamsByClassSectionSubject(year, section, subject);
		request.setAttribute("searchExamByClassSectionSubject", list);
		RequestDispatcher rd = request.getRequestDispatcher("online-exams.jsp");
		rd.forward(request, response);
	}

}

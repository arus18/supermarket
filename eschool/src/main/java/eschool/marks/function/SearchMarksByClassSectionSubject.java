package eschool.marks.function;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eschool.marks.model.Marks;
import eschool.marks.service.Service;

/**
 * Servlet implementation class SearchMarksByClassSectionSubject
 */
@WebServlet("/SearchMarksByClassSectionSubject")
public class SearchMarksByClassSectionSubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMarksByClassSectionSubject() {
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
		ArrayList<Marks> list = Service.getMarksBySectionClassSubject(year, section, subject);
		request.setAttribute("searchMarksByClassSectionSubject", list);
		RequestDispatcher rd = request.getRequestDispatcher("marks.jsp");
		rd.forward(request, response);
	}

}

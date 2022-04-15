package eschool.marks.crud;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eschool.marks.service.Service;
import eschool.student.model.Student;

/**
 * Servlet implementation class NewMarks
 */
@WebServlet("/NewMarks")
public class NewMarks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewMarks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		int year = Integer.parseInt(request.getParameter("class"));
		String section = request.getParameter("section");
		String subject = request.getParameter("subject");
		int marks = Integer.parseInt(request.getParameter("marks"));
		Student student = eschool.student.service.Service.getStudentByID(id);
		if(student.getYear() == year && student.getSection().equals(section)) {
			Service.newMarks(student.getName(), id, subject, marks, section, year);
			response.sendRedirect("marks-add.jsp");
		}
		else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('incorrect class and section for given student id');");
			out.println("location='marks-add.jsp';");
			out.println("</script>");
		}
	}

}

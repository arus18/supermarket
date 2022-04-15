package supermarket.dashboard.function;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import supermarket.dashboard.service.Service;

/**
 * Servlet implementation class SalesAndPurchasesChart
 */
@WebServlet("/SalesAndPurchasesChart")
public class SalesAndPurchasesChart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalesAndPurchasesChart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json"); 
		PrintWriter out = response.getWriter();		
		JSONObject json = Service.totalSalesAndPurchasesLastSevenDays();
		out.println(json);
	}

}

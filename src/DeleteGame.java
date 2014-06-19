import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteGame
 */
@WebServlet("/DeleteGame")
public class DeleteGame extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteGame() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Connection c = null;
		Statement stmt = null;
		String pathGame = this.getServletContext().getRealPath("game.db");
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + pathGame);
			stmt = c.createStatement();
			String sql = "UPDATE GAME SET IS_ACTIVE = 0 WHERE ID = "
					+ request.getParameter("id");
			stmt.executeUpdate(sql);
		} catch (ClassNotFoundException | SQLException e) {

		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
			}
			try {
				c.close();
			} catch (SQLException e) {
			}
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
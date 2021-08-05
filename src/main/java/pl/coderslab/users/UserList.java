package pl.coderslab.users;


import pl.coderslab.databaseClasses.UserDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/list")
public class UserList extends HttpServlet {

    private static UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("users", userDao.findAll());

        getServletContext().getRequestDispatcher("/WEB-INF/list.jsp")
                .forward(request, response);

    }
}

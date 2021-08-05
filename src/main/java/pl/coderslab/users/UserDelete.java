package pl.coderslab.users;

import pl.coderslab.databaseClasses.UserDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/delete")
public class UserDelete extends HttpServlet {

    private static final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            userDao.delete(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect("/users/list");
    }
}

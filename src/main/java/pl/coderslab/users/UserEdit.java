package pl.coderslab.users;


import pl.coderslab.databaseClasses.User;
import pl.coderslab.databaseClasses.UserDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/edit")
public class UserEdit extends HttpServlet {

    private static final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("editId", request.getParameter("id"));
        request.setAttribute("editName", request.getParameter("name"));
        request.setAttribute("editEmail", request.getParameter("email"));

        getServletContext().getRequestDispatcher("/WEB-INF/edit.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User(Integer.parseInt(request.getParameter("id")), request.getParameter("name"), request.getParameter("email"), request.getParameter("password"));
        userDao.update(user);
        response.sendRedirect("/users/list");
    }
}

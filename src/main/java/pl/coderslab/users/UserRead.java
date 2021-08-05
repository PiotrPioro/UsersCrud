package pl.coderslab.users;


import pl.coderslab.databaseClasses.UserDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/user/read")
public class UserRead extends HttpServlet {

    private static final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        request.setAttribute("users", userDao.read(Integer.parseInt(request.getParameter("id"))));

        getServletContext().getRequestDispatcher("/WEB-INF/read.jsp")
                .forward(request, response);
    }
}

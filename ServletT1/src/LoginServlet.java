import com.company.RequestAttributeService;
import com.company.UserQuestionnaire;
import com.company.VoteService;
import com.company.credentials.CredentialService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "com.company.LoginServlet")
public class LoginServlet extends HttpServlet {
    private CredentialService credService;
    VoteService vs = VoteService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String user = request.getParameter("login");
        String password = request.getParameter("password");
        if (credService.checkCredentials(user,password)){
            HttpSession session = request.getSession(true);
            session.setAttribute("user_login", user);

            if (vs.isUserVoted(user)){
                RequestAttributeService.setAttributes(request, user);
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        } else{
            response.sendRedirect("/login.jsp?repeat=1");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String relogin = (String)request.getParameter("relogin");
        if (relogin != null && relogin.equals("1")){
            session.removeAttribute("user_login");
            response.sendRedirect("/login.jsp");
        }
    }

    @Override
    public void init() throws ServletException {
        super.init();
        credService = new CredentialService("c:\\JavaCourse\\files\\users.json");
    }

}

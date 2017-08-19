import com.company.Answer;
import com.company.RequestAttributeService;
import com.company.UserQuestionnaire;
import com.company.VoteService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class VoteServlet extends javax.servlet.http.HttpServlet {

    VoteService vs = VoteService.getInstance();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        HttpSession session = request.getSession(false);

        String user = (String)session.getAttribute("user_login");
        session.removeAttribute("voted");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        int age = Integer.parseInt(request.getParameter("age"));

        ArrayList<Answer> myAnswers;
        boolean alreadyVoted = vs.isUserVoted(user);
        if (!alreadyVoted) {
            myAnswers = new ArrayList<Answer>();
            for (int i = 1; i < 4; i++) {
                myAnswers.add(new Answer(user,i,Integer.parseInt(request.getParameter("question"+i))));
            }
            vs.addUserAnswers(user,name,surname,age,myAnswers);
            RequestAttributeService.setAttributes(request, user);
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
    }
}

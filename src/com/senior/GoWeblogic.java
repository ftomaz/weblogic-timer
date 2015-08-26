package com.senior;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.LocalDateTime;

public class GoWeblogic extends HttpServlet {
    
    
    @EJB
    private TimerClockSingleton timerClockSinglenton;
    
    Timer actualTimer;

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doIt(req, res);
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doIt(req, res);
    }


    private void doIt(HttpServletRequest req, HttpServletResponse res) throws IOException {

        if (req.getParameter("addTimer") != null) {
            LocalDateTime time = new LocalDateTime().plusSeconds(60);
            actualTimer = timerClockSinglenton.getTimer().createSingleActionTimer(time.toDate(), new TimerConfig(time, false));
        }

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        out.println("<html>" /**/
                    + "<body>" /**/
                    + "<h1>Servlet WebLogic Programatic Timer Test</h1>" /**/
                    + "<h2>" + timerClockSinglenton.checkTimerStatus() + "</h2>" + "</body>" /**/
                    + "</br></br><a href='/weblogic-timer?addTimer=1'>Add Programatic Timer</a> "
                    + "</html>");

         out.close();
    }


}

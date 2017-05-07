package me.exerosis.builditbigger;

import com.google.appengine.repackaged.com.google.gson.Gson;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.exerosis.builditbigger.jokes.JokeFactory;

public class Servlet extends HttpServlet {
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getServletPath().equals("/getJokes")) {
            String json = gson.toJson(JokeFactory.getInstance().getJokes(Integer.parseInt(request.getParameter("count"))).toBlocking().first());
            response.setContentType("json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            response.getWriter().flush();
        } else
            response.sendError(404);
    }
}
package me.exerosis.builditbigger;

import com.google.appengine.repackaged.com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import retrofit2.http.GET;

public class RetrofitServlet extends HttpServlet {
    private final Gson gson = new Gson();
    private final Map<String, Method> methodMap = new HashMap<>();
    private Object instance;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
           Class<?> retrofitInterface = Class.forName(config.getInitParameter("retrofit_interface"));
            for (Method method : retrofitInterface.getDeclaredMethods())
                if (method.isAnnotationPresent(GET.class))
                    methodMap.put(method.getAnnotation(GET.class).value(), method);
            instance = retrofitInterface.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(!methodMap.containsKey(request.getServletPath()))
            response.sendError(404);
        else {
            Object json = null;
            try {
                json = methodMap.get(request.getServletPath()).invoke(instance);
            } catch (Exception e) {
                e.printStackTrace();
            }
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(gson.toJson(json));
            response.getWriter().flush();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

package com.codecool.airbnbmanager.api;


import com.codecool.airbnbmanager.model.Lodgings;
import com.codecool.airbnbmanager.model.ToDo;
import com.codecool.airbnbmanager.model.User;
import com.codecool.airbnbmanager.service.LodgingsService;
import com.codecool.airbnbmanager.util.FieldType;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class ToDoController extends HttpServlet {

//    private final String servletName;
//    private final BaseService<User> userService;
//    private final LodgingsService lodgingsService;
//    private final ToDoServiceREST toDoService;
//
//    public ToDoController(String servletName, BaseService<User> userService, LodgingsService lodgingsService, ToDoServiceREST toDoService) {
//        this.servletName = servletName;
//        this.userService = userService;
//        this.lodgingsService = lodgingsService;
//        this.toDoService = toDoService;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        // Handling log-in
//        HttpSession session = request.getSession(false);
//
//        if (session == null || session.getAttribute(FieldType.EMAIL_ADDRESS.getInputString()).equals(GUEST_EMAIL)) {
//            response.sendRedirect("/login");
//        } else {
//
//            String userEmail = (String) session.getAttribute(FieldType.EMAIL_ADDRESS.getInputString());
//            User user = userService.handleGetUserBy(userEmail);
//            List<Lodgings> lodgingsList = lodgingsService.handleGetAllLodgingsBy(user.getId());
//            List<ToDo> toDoList = toDoService.handleGetAllTodosBy(lodgingsList);
//
//            String requestPath = request.getServletPath();
//            String templateToRender = toDoService.handleCrudGetBy(requestPath, "1");
//
//            WebContext context = new WebContext(request, response, request.getServletContext());
//            context.setVariable("userData", user);
//            context.setVariable("todoList", toDoList);
//            context.setVariable("lodgingsId", request.getParameter("lodgingsId"));
//
//
//
//            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
//            engine.process(templateToRender, context, response.getWriter());
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        HttpSession session = request.getSession(false);
//        if (session == null || session.getAttribute(FieldType.EMAIL_ADDRESS.getInputString()).equals(GUEST_EMAIL)) {
//            response.sendRedirect("/login");
//        } else {
//
//            String name = request.getParameter("name");
//            String description = request.getParameter("description");
//            long price = Long.parseLong(request.getParameter("price"));
//
//            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//            Date date = null;
//
//            try {
//                date = formatter.parse(request.getParameter("date"));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//            String userEmail = (String) session.getAttribute(FieldType.EMAIL_ADDRESS.getInputString());
//            User user = userService.handleGetUserBy(userEmail);
//            String LodgingsId = request.getParameter("lodgingsId");
//            long userId = user.getId();
//            Lodgings lodgings = lodgingsService.handleGetLodgingsBy(LodgingsId,userId).get(0);
//
//            ToDo toDo = new ToDo(name,lodgings, date, description, price);
//
//            toDoService.handleAddPost(toDo);
//
//            response.sendRedirect("/index");
//
//
//        }
//
//    }

}
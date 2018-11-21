package com.codecool.airbnbmanager.api;


import com.codecool.airbnbmanager.service.api.ToDoServiceREST;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;

@RestController
public class ToDoControllerREST extends HttpServlet {

    private static final String SUCCESS_MESSAGE = "SUCCESS";
    private static final String FAIL_MESSAGE = "FAIL";

    @Autowired
    private ToDoServiceREST toDoService;



    @GetMapping(path = "/api/todos", consumes = "text/plain")
    public String toDoViewAll(@RequestBody String body) {
        return toDoService.getAllToDosByLodgingsId(body);
    }

    @GetMapping(path = {"/api/todo/{id}", "/api/todo/edit/{id}"})
    public String toDoViewSingleAndEditGet(@PathVariable(name = "id") Long id) {
        return toDoService.createToDoJsonStringById(id);
    }

    @PutMapping(path = "/api/todo/edit", consumes = "text/plain")
    public String toDoEditPost(@RequestBody String body) {
        boolean isUpdateSuccessful = toDoService.handleToDoUpdate(body);
        return (isUpdateSuccessful) ? SUCCESS_MESSAGE : FAIL_MESSAGE;
    }
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
package com.zxf.servlet;

import com.zxf.entity.Student;
import com.zxf.repository.StudentRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    private StudentRepository studentRepository = new StudentRepository();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method == null) {
            method = "findAll";
        }
        switch (method) {
            case "findAll":
                //给客户端返回试图和数据,数据库中的所有数据
                List<Student> list = studentRepository.findAll();
                req.setAttribute("list", list);
                //转发到index
                req.getRequestDispatcher("index.jsp").forward(req, resp);
                break;
            case "deleteById":
                //删除一条数据
                String idStr = req.getParameter("id");
                int id = Integer.parseInt(idStr);
                studentRepository.deleteById(id);
                //重定向返回主页
                resp.sendRedirect("/student");
                break;
            case "findById":
                //找到一条数据然后转发到更改数据的页面 并将此条数据的内容也传递过去
                idStr = req.getParameter("id");
                id = Integer.parseInt(idStr);
                req.setAttribute("student", studentRepository.findById(id));
                req.getRequestDispatcher("update.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        switch (method) {
            case "add":
                //如果接受到add参数就调用studentRepository的addf()方法
                String name = req.getParameter("name");
                String scoreStr = req.getParameter("score");
                Double score = Double.parseDouble(scoreStr);
                studentRepository.add(name, score);
                break;
            case "update":
                //调用update()方法来更新一条数据
                String idStr = req.getParameter("id");
                int id = Integer.parseInt(idStr);
                name = req.getParameter("name");
                scoreStr = req.getParameter("score");
                score = Double.parseDouble(scoreStr);
                studentRepository.update(id, name, score);
                break;
        }
        //重定向到主页
        resp.sendRedirect("/student");
    }
}

package xl.start.springboot2autoconfig.servlet;//package xl.start.springboot2.web.servlet;
//
//import javax.servlet.DispatcherType;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 创建servlet流程:
// * 1. 使用 @WebServlet(urlPatterns="") 进行路径映射
// * 2. 继承 HttpServlet 重写 doGet doPost
// * 3. @ServletComponentScan 扫描servlet并注册
// *
// * created by XUAN on 2019/9/6
// */
//@WebServlet(urlPatterns = "/sync/servlet")
//public class SyncServlet extends HttpServlet {
//
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.getWriter().println("hello, world");
//    }
//}

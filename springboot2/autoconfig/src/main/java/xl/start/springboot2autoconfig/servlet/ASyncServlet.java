package xl.start.springboot2autoconfig.servlet;//package xl.start.springboot2.web.servlet;
//
//import javax.servlet.AsyncContext;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 异步servlet需要开启异步功能 asyncSupported = true
// *
// * created by XUAN on 2019/9/6
// */
//@WebServlet(urlPatterns = "/async/servlet", asyncSupported = true)
//public class ASyncServlet extends HttpServlet {
//
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
//        AsyncContext asyncContext = req.startAsync();
//        asyncContext.start(() -> {
//            try {
//                Thread.sleep(5000);
//                resp.getWriter().println("hello, world");
//                // 必须触发完成才能返回
//                asyncContext.complete();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//
//    }
//}

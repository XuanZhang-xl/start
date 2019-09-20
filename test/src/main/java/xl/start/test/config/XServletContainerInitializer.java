package xl.start.test.config;

import xl.start.test.config.zk.eventhandler.ZookeeperEventHandler;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterRegistration;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.HandlesTypes;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Set;

/**
 * https://blog.csdn.net/j080624/article/details/80016905
 *
 * 尼玛使用springboot之后 ServletContainerInitializer 貌似就没用了, springboot你对servlet做了什么???
 *
 *
 * created by XUAN on 2019/9/20
 */
//容器启动的时候会将@HandlesTypes指定的这个类型下面的子类（实现类，子接口等）传递过来；
//传入感兴趣的类型；
@HandlesTypes(value={ZookeeperEventHandler.class})
public class XServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("感兴趣的类型：");
        for (Class<?> clazz : c) {
            System.out.println(clazz);
        } //注册组件ServletRegistration
        ServletRegistration.Dynamic servlet = ctx.addServlet("userServlet", new UserServlet());
        //配置servlet的映射信息
        servlet.addMapping("/user");
        //注册Listener
        ctx.addListener(UserListener.class);
        //注册Filter FilterRegistration
        FilterRegistration.Dynamic filter = ctx.addFilter("userFilter", UserFilter.class);
        //配置Filter的映射信息
        filter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
    }

    private class UserFilter implements Filter {
        @Override
        public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
            System.out.println("UserFilter被调用");
            chain.doFilter(request, response);
        }
    }

    public class UserListener implements ServletRequestListener {
        public void requestDestroyed (ServletRequestEvent sre) {
            System.out.println("UserListener的requestDestroyed()方法被调用");
        }

        public void requestInitialized (ServletRequestEvent sre) {
            System.out.println("UserListener的requestInitialized()方法被调用");
        }
    }

    private class UserServlet implements Servlet {
        @Override
        public void init(ServletConfig config) throws ServletException {
            System.out.println("UserServlet的init()被调用");
        }

        @Override
        public ServletConfig getServletConfig() {
            return null;
        }

        @Override
        public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
            System.out.println("UserServlet的service()被调用");
        }

        @Override
        public String getServletInfo() {
            return null;
        }

        @Override
        public void destroy() {
            System.out.println("UserServlet的destroy()被调用");
        }
    }
}

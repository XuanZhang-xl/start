package xl.aop.aopmain;

/**
 * java -javaagent:aop-agent-0.0.1-SNAPSHOT.jar=Hello -jar aop-main-0.0.1-SNAPSHOT.jar
 * 听说这么启动的话print方法会被代理
 *
 * D:\03_java\01_jarPagkage\maven\repository\org\javassist\javassist\3.20.0-GA\javassist-3.20.0-GA.jar
 *
 * 说我这个路径有非法参数, 待测试
 *
 * @author XUAN
 * @since 2020/01/02
 */
public class MainClass {

    public static void main(String[] args) {
        new MainClass().print();
    }

    public void print () {
        String className = this.getClass().getName();
        String methodName = new Exception().getStackTrace()[0].getMethodName();
        System.out.println(className + "." + methodName + "() 已执行");
    }
}

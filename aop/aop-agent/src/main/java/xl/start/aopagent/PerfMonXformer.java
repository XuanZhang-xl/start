package xl.start.aopagent;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtBehavior;
import javassist.CtClass;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * created by XUAN on 2020/1/2
 */
public class PerfMonXformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] transformed = null;
        if (className.equals("xl/aop/aopmain/MainClass")) {
            // 只代理自己编写的类
            System.out.println("transforming " + className);
        } else {
            System.out.println("skipped " + className);
            return classfileBuffer;
        }
        ClassPool pool = ClassPool.getDefault();
        CtClass cl = null;
        try {
            cl = pool.makeClass(new ByteArrayInputStream(classfileBuffer));
            if (!cl.isInterface()) {
                CtBehavior[] methods = cl.getDeclaredBehaviors();
                for (int i = 0; i < methods.length; i++) {
                    // 如果已冻结, 则解冻  https://blog.csdn.net/weixin_34417635/article/details/92682091
                    if(cl.isFrozen()){
                        cl.defrost();
                        System.out.println("解冻状态:" + cl.isFrozen());
                    }
                    CtBehavior method = methods[i];
                    if (!method.isEmpty()) {
                        // 修改字节码
                        doMethod(method);
                    }
                    transformed = cl.toBytecode();
                }
            }
        } catch (Exception e) {
            System.err.println("could not instrument   " + className + ", exception: " + e);
        } finally {
            if (cl != null) {
                cl.detach();
            }
        }
        return transformed;
    }

    private void doMethod(CtBehavior method) throws CannotCompileException {
        if (!method.getName().equals("print")) {
            System.out.println("skipped method " + method.getName());
            return;
        }
        // 如果冻结, 则解冻
        CtClass declaringClass = method.getDeclaringClass();
        if (declaringClass.isFrozen()) {
            declaringClass.defrost();
            System.out.println("method.getDeclaringClass()解冻状态:" + declaringClass.isFrozen());
        }
        long l = System.nanoTime();
        // 添加局部变量, 不添加, 将报找不到局部变量的错误 https://blog.csdn.net/chao_1990/article/details/70256503
        // 依然报错, 但是这是asm的问题了, 有空再解决
        /*
        java.lang.VerifyError: Bad local variable type
        Exception Details:
          Location:
            xl/aop/aopmain/MainClass.print()V @109: lload_3
          Reason:
            Type top (current frame, locals[3]) is not assignable to long
        */
        // 可参考:
        // https://www.iteye.com/blog/yunshen0909-2222638
        // https://blog.csdn.net/dataiyangu/article/details/102708924
        // https://stackoverflow.com/questions/27487417/java-lang-verifyerror-bad-local-variable-type-after-bytecode-instrumentation
        method.addLocalVariable("startTime", CtClass.longType);
        method.insertBefore("long startTime = System.nanoTime();");
        method.insertAfter("System.out.println(\"leave\" + new Exception().getStackTrace()[0].getMethodName() + \"() and time: \" + (System.nanoTime() - startTime));");
        System.out.println(declaringClass.getName() + "." + method.getMethodInfo().getName() + " has been enhanced");
    }
}

package org.jvm;

import java.lang.reflect.Method;

/**
 * 为了多次载入执行类而加入的加载器
 * 把defineClass方法开放出来，只有外部显式调用的时候才会使用到loadByte方法
 * 由虚拟机调用时，仍然按照原有的双亲委派规则使用loadClass方法进行类加载
 */
public class JavaClassExecuter {
    public static String executer(byte[] classByte) throws NoSuchMethodException {
        HackSystem.clearBuffer();
        ClassModifier classModifier = new ClassModifier(classByte);
        byte[] modiByte = classModifier.modifyUTF8Constant("java/lang/System", "org/jvm/HackSystem");
        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class cs = loader.loadByte(modiByte);
        try {
            Method method = cs.getMethod("main", new Class[]{String[].class});
            method.invoke(null, new String[]{null});
        } catch (Throwable throwable) {
            throwable.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }
}

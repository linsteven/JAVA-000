package main.java.week01;

import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author yuanlin.yyl
 * @date 2020/10/21
 */
public class HelloClassLoader extends ClassLoader {

    private static final String HELLO_CLASS_NAME = "Hello";
    private static final String HELLO_METHOD_NAME = "hello";
    private static final String HELLO_CLASS_PATH = "./src/main/resources/Hello.xlass";


    public static void main(String[] args) {

        try {
             Class clazz = new HelloClassLoader().findClass(HELLO_CLASS_NAME);
             Method callHello = clazz.getMethod(HELLO_METHOD_NAME);
             callHello.invoke(clazz.newInstance());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Path path = Paths.get(HELLO_CLASS_PATH);
        byte[] data = new byte[1];

        try {
            data = Files.readAllBytes(path);
            for (int i = 0; i < data.length; i++) {
                data[i] = (byte)(255 - data[i]);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return defineClass(name, data, 0, data.length);

    }

}

package com.codeasatrade.core.computation;

import lombok.extern.slf4j.Slf4j;

import javax.tools.*;
import java.io.*;
import java.lang.reflect.Method;
import java.net.*;

@Slf4j
public class RuntimeCompiler {
    public static void compileAndRun1(String code) throws Exception {
        // Create a temporary file
        File sourceFile = File.createTempFile("Main", ".java");
        try (PrintWriter out = new PrintWriter(sourceFile)) {
            out.println(code);
        }

        // Compile the source file
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, sourceFile.getPath());

        // Load and instantiate the compiled class
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{sourceFile.getParentFile().toURI().toURL()});
        Class<?> cls = Class.forName("Main", true, classLoader);
        Object obj = cls.getDeclaredMethod("compute", String[].class).invoke(null, (Object) new String[0]);
        log.info("Result: {}", obj.toString());
    }

    public static String compileAndRun(String code) throws Exception {
        // Create a temporary file
        File sourceFile = File.createTempFile("Main", ".java");
        try (PrintWriter out = new PrintWriter(sourceFile)) {
            out.println(code);
        }

        // Compile the source file
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, sourceFile.getPath());

        // Load and instantiate the compiled class
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{sourceFile.getParentFile().toURI().toURL()});
        Class<?> cls = Class.forName("Main", true, classLoader);

        // Find the method that returns a String
        Method method = cls.getDeclaredMethod("compute");

        // Invoke the method and capture the result
        Object result = method.invoke(cls.getDeclaredConstructor().newInstance());

        // Clean up the temporary file
        sourceFile.delete();

        return (String) result;
    }

}
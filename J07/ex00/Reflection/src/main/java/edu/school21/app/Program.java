package edu.school21.app;

import java.lang.reflect.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringJoiner;

public class Program {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        //1. Get classname
        String className = "";
        {
            while (!className.equals("User") && !className.equals("Car"))
                className = (String) getInputArg("String", "Choose one of these classes: User, Trip");
        }
        System.out.println("----------------------------------------------------------");

        Class clazz = Class.forName("edu.school21.classes.".concat(className));
        Field [] fields = clazz.getDeclaredFields();
        Method [] methods = clazz.getDeclaredMethods();

        //2. Print class info
        {
            printClassInfo(clazz);
        }
        System.out.println("----------------------------------------------------------");

        //3. Create object
        Object obj = null;
        {
            System.out.println("Input values of necessary fields for " + className + " object creation: ");
            Constructor constr = getNotEmptyConstructor(clazz);
            Parameter constrParams[] = constr.getParameters();
            Object constrArgs[] = new Object[constrParams.length];

            for (int i = 0; i < constrParams.length; i++) {
                Class<?> type = constrParams[i].getType();
                while (constrArgs[i] == null)
                    constrArgs[i] = getInputArg(type.getSimpleName(), type.getSimpleName() + " " + fields[i].getName());
            }
            obj = constr.newInstance(constrArgs);
            System.out.println(obj);
        }
        System.out.println("----------------------------------------------------------");

        //4.Change a field
        {
            Field field = null;
            while (field == null) {
                String filedName = (String) getInputArg("String", "Input field name for value changing: ");
                try {
                    field = clazz.getDeclaredField(filedName);
                } catch (NoSuchFieldException e) {
                }
            }
            Object value = null;
            while (value == null)
                value = getInputArg(field.getType().getSimpleName(), "Input new value: ");
            field.setAccessible(true);
            field.set(obj, value);
            field.setAccessible(false);
            System.out.println(obj);
        }
        System.out.println("----------------------------------------------------------");

        //5. Invoke a method
        {
            Method method = null;
            while (method == null) {
                String methodName = (String)getInputArg("String", "Input method name:");
                method = getMethodByName(methods, methodName);
            }
            System.out.println("Input values of method arguments: ");
            Parameter methodParams[] = method.getParameters();
            Object methodArgs [] = new Object[methodParams.length];
            for (int i = 0; i < methodParams.length; i++) {
                while (methodArgs[i] == null) {
                    String type = methodParams[i].getType().getSimpleName();
                    methodArgs[i] = getInputArg(type, type);
                }
            }
            Object ret = method.invoke(obj, methodArgs);
            if (ret != null)
                System.out.println("Method returned: " + ret);
            System.out.println(obj);
        }
        System.out.println("----------------------------------------------------------");
    }

    private static Object getInputArg(String typeName, String message) {
        System.out.println(message);
        System.out.print("-> ");
        try {
            if (typeName.equals("String"))
                return new Scanner(System.in).nextLine();
            if (typeName.equals("Double") || typeName.equals("double"))
                return new Scanner(System.in).nextDouble();
            if (typeName.equals("Integer") || typeName.equals("int"))
                return new Scanner(System.in).nextInt();
            if (typeName.equals("Boolean") || typeName.equals("boolean"))
                return new Scanner(System.in).nextBoolean();
            if (typeName.equals("Long") || typeName.equals("long"))
                return new Scanner(System.in).nextLong();
        } catch (InputMismatchException e) {
             return null;
        }
        return null;
    }

    public static void printClassInfo(Class clazz) {
        System.out.println("Fields:");
        {
            for (Field field : clazz.getDeclaredFields()) {
                System.out.println("\t" + field.getType().getSimpleName() + " " + field.getName() + ";");
            }
        }
        System.out.println("Methods:");
        {
            for (Method method : clazz.getDeclaredMethods()) {
                if (method.getName().equals("toString"))
                    continue ;
                System.out.print("\t" + method.getReturnType().getSimpleName() + " " + method.getName() + "(");
                StringJoiner sj = new StringJoiner(", ");
                for (Parameter parameter : method.getParameters())
                    sj.add(parameter.getType().getSimpleName());
                System.out.print(sj);
                System.out.println(");");
            }
        }
    }

    public static Constructor getNotEmptyConstructor(Class clazz) {
        for (Constructor constructor : clazz.getDeclaredConstructors()) {
            if (constructor.getParameters().length > 0)
                return constructor;
        }
        return clazz.getDeclaredConstructors()[0];
    }

    public static Method getMethodByName(Method [] methods, String methodName) {
        for (Method method : methods) {
            if (method.getName().equals(methodName))
                return method;
        }
        return null;
    }
}

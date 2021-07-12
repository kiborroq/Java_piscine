package edu.school21.app;

import edu.school21.logic.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/context.xml");
        Printer printer = applicationContext.getBean(Printer.class);
        printer.print("hello");
    }
}

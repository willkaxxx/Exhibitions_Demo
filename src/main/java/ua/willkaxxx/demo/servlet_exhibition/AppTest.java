package ua.willkaxxx.demo.servlet_exhibition;

import org.apache.log4j.Logger;
import ua.willkaxxx.demo.servlet_exhibition.model.ConfigReader;


public class AppTest {

    private static final Logger log = Logger.getLogger(AppTest.class);
    public static void main(String[] args) {
        System.out.println(ConfigReader.getInstance().getProperty("page.size"));
    }
}

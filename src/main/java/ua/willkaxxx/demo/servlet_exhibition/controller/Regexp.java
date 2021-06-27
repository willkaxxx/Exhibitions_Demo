package ua.willkaxxx.demo.servlet_exhibition.controller;


public final class Regexp {
    public static String EMAIL = "[\\S]{3,}@[\\S]{1,}";
    public static String PASSWORD = "[\\S]{5,}";

    public static String SQL_FIELD_NAME = "[a-z_]+";
    public static String TEXT = ".+";
    public static String DECIMAL = "^[\\d]+(.\\d{0,2})?$";
    public static String DATE = "^\\d{4}-\\d{2}-\\d{2}$";
}

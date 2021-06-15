package ua.willkaxxx.demo.servlet_exhibition.controller;

public final class Regexp {
    public static final String EMAIL = "[\\S]{3,}@[\\S]{1,}";
    public static final String PASSWORD = "[\\S]{5,}";

    public static final String SQL_FIELD_NAME = "[a-z_]+";
    public static final String TEXT = ".+";
    public static final String DECIMAL = "^[\\d]+(.\\d{0,2})?$";
    public static final String DATE = "^\\d{4}-\\d{2}-\\d{2}$";
}

package utils;

import com.github.javafaker.Faker;

public class GeneratorUtil {
    static Faker faker = new Faker();

    public static String getFirstName() {return faker.name().firstName();}
    public static String getMiddleName() {return faker.name().fullName();}
    public static String getLastName() {return faker.name().lastName();}
    public static String getEmail() {return faker.internet().emailAddress();}
    public static String getContactNr() {return faker.numerify("#########");}
    public static String getKeywords() {return faker.lorem().word();}
    public static String getNotes() {return faker.lorem().sentence();}

}

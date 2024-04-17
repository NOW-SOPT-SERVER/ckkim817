package org.sopt;

import org.sopt.classes.Person;
import org.sopt.classes.PersonBuilder;

public class Main {
    public static void main(String[] args) {
        Person.run();
        Person person = new Person("김창균", 25, "male");
        person.walk();
        System.out.println(person.getName());
        person.setName("KimChangKyun");
        System.out.println(person.getName());

        Person personWithBuilder = new
                PersonBuilder()
                .name("김창균")
                .age(25)
                .sex("male")
                .build();

        Person personWithFactoryMethod = Person.create("김창균", 25, "male");

    }
}

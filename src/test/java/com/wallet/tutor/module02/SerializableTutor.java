package com.wallet.tutor.module02;

import static com.wallet.tutor.Logger.log;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

public class SerializableTutor {

    private static final String FILE_OBJECT_DATA = "files/object.data";
    private static final Calendar dayOfBirth;

    static {
        dayOfBirth = Calendar.getInstance();
        dayOfBirth.set(Calendar.YEAR, 2000);
        dayOfBirth.set(Calendar.MONTH, Calendar.OCTOBER);
        dayOfBirth.set(Calendar.DAY_OF_MONTH, 10);
    }

    /**
     * Make the class static, implement Serializable
     * and mark the field age as transient
     */
    static class Person implements Serializable {

        private final String name;
        private final Date birthdate;
        private final List<Address> addressList = new ArrayList<>();
        transient int age;

        public Person(String name, Date birthdate) {
            this.name = name;
            this.birthdate = birthdate;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(birthdate);
            age = Calendar.getInstance().get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
        }

        public void addAddress(Address address) {
            addressList.add(address);
        }
    }


    /**
     * Make the class static and implement Serializable
     */
    static class Address implements Serializable {
        private final String city;
        private final String street;
        private final int appartement;

        public Address(String city, String street, int appartement) {
            this.city = city;
            this.street = street;
            this.appartement = appartement;
        }
    }

    /**
     * Should write the data of Person to file FILE_OBJECT_DATA,
     * using ObjectOutputStream
     *
     * @param person
     */
    public void writeToFile(Person person) {
        try (FileOutputStream file = new FileOutputStream(FILE_OBJECT_DATA);

             ObjectOutputStream output = new ObjectOutputStream(file)) {

            output.writeObject(person);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Should read and return data from file FILE_OBJECT_DATA
     */
    public Person readFromFile() {
        try {
            FileInputStream file = new FileInputStream(FILE_OBJECT_DATA);

            ObjectInputStream inputStream = new ObjectInputStream(file);

            Person person = (Person) inputStream.readObject();

            log(person);

            return person;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testSerializeObject() {
        Person person = new Person("John Johnes", dayOfBirth.getTime());
        writeToFile(person);
        log("Age: " + person.age);
        Person personFromFile = readFromFile();
        log("Name from file: " + personFromFile.name);
        log("Age from file: " + personFromFile.age);
        assertEquals(personFromFile.name, personFromFile.name);
        assertNotEquals(person.age, personFromFile.age);
    }

    @Test
    public void testSerializePersonAddress() {
        Person person = new Person("John Johnes", dayOfBirth.getTime());
        Address address = new Address("New York", "Water street", 10);
        person.addAddress(address);

        writeToFile(person);

        log("Age: " + person.age);
        Person personFromFile = readFromFile();
        log("Name from file: " + personFromFile.name);
        log("Age from file: " + personFromFile.age);
        assertEquals(personFromFile.name, personFromFile.name);
        assertNotEquals(person.age, personFromFile.age);

        Address addressFromFile = personFromFile.addressList.get(0);
        log("City from file: " + addressFromFile.city);
        log("Appartment from file: " + addressFromFile.appartement);
        assertEquals(addressFromFile.city, address.city);
        assertEquals(addressFromFile.appartement, address.appartement);
    }

}

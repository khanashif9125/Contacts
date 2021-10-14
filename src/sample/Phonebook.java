package sample;

import javafx.beans.property.SimpleStringProperty;

public class Phonebook {
    private SimpleStringProperty name;
    private SimpleStringProperty phoneno;
    private SimpleStringProperty email;
    private SimpleStringProperty birth;

    public Phonebook(String name, String phoneno, String email, String birth) {
        this.name = new SimpleStringProperty(name);
        this.phoneno = new SimpleStringProperty(phoneno);
        this.email = new SimpleStringProperty(email);
        this.birth = new SimpleStringProperty(birth);
    }

    public String getName() {
        return name.get();
    }

    public String getPhoneno() {
        return phoneno.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getBirth() {
        return birth.get();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}


package entity;

import com.ibm.icu.text.Transliterator;

import javax.persistence.*;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Entity
@Table(name = "user", schema = "main")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String login;
    private String password;

    @Basic
    @Column(name = "surname", nullable = false)
    private String surname;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "patronymic", nullable = false)
    private String patronymic;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public void generateLoginPass() {
            String CYRILLIC_TO_LATIN = "Russian-Latin/BGN";
            Transliterator russianToLatin = Transliterator.getInstance(CYRILLIC_TO_LATIN);
            StringBuilder sb = new StringBuilder();
            sb.append(getSurname());
            sb.append(getName().substring(0, 1));
            sb.append(getPatronymic().substring(0, 1));
            login = russianToLatin.transliterate(sb.toString().toLowerCase());

            String myPass = "user";

            MessageDigest md = null;

            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            md.update(myPass.getBytes());
            byte[] digest = md.digest();
            this.password = DatatypeConverter.printHexBinary(digest).toUpperCase();
        }

        public boolean isEquals(String password) {
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            md.update(password.getBytes());
            byte[] digest = md.digest();

            return DatatypeConverter.printHexBinary(digest).toUpperCase().equals(getPassword());
        }


}

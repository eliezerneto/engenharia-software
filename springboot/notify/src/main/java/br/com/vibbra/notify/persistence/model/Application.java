package br.com.vibbra.notify.persistence.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Boolean webPush;

    @Column(nullable = false)
    private Boolean email;

    @Column(nullable = false)
    private Boolean sms;

    public Application() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isWebPush() {
        return this.webPush;
    }

    public Boolean getWebPush() {
        return this.webPush;
    }

    public void setWebPush(Boolean webPush) {
        this.webPush = webPush;
    }

    public Boolean isEmail() {
        return this.email;
    }

    public Boolean getEmail() {
        return this.email;
    }

    public void setEmail(Boolean email) {
        this.email = email;
    }

    public Boolean isSms() {
        return this.sms;
    }

    public Boolean getSms() {
        return this.sms;
    }

    public void setSms(Boolean sms) {
        this.sms = sms;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Application)) {
            return false;
        }
        Application application = (Application) o;
        return id == application.id && Objects.equals(name, application.name)
                && Objects.equals(webPush, application.webPush) && Objects.equals(email, application.email)
                && Objects.equals(sms, application.sms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, webPush, email, sms);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enity;

/**
 *
 * @author Vu
 */
public class Content {
    private String Address, city, country, tel, email;

    public Content() {
    }

    public Content(String Address, String city, String country, String tel, String email) {
        this.Address = Address;
        this.city = city;
        this.country = country;
        this.tel = tel;
        this.email = email;
    }

    public String getAddress() {
        return Address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}

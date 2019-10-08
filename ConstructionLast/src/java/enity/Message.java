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
public class Message {
    private String Name;
    private String Email;
    private String Message;

    public Message() {
    }

    
    public Message(String Name, String Email, String Message) {
        this.Name = Name;
        this.Email = Email;
        this.Message = Message;
    }
    
    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getMessage() {
        return Message;
    }
    public void setName(String Name) {
        this.Name = Name;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }
    
}

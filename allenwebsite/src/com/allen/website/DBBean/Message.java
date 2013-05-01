package com.allen.website.DBBean;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Message {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key id;
    @Persistent
    private String username;
    @Persistent
    private String message;
    @Persistent
    private Date date;

    
    public Message(String u, String p, Date date2)
    {
    	username=u;
    	message=p;
    	date=date2;
    }

    public Date getDate()
    {
    	return date;
    }
    
    public void setDate(Date date)
    {
    	this.date=date;
    }
    public Key getId()
    {
    	return id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String un) {
         username=un;
    }

    public String getMessage() {
        return message;
    }
    
    public void setMessage(String pwd) {
         message=pwd;
    }
}
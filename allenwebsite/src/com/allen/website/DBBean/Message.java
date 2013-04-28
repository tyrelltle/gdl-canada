package com.allen.website.DBBean;

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


    
    public Message(String u, String p)
    {
    	username=u;
    	message=p;
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
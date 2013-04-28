package com.allen.website.DBBean;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * JDO-annotated model class for storing movie properties; movie's promotional
 * image is stored as a Blob (large byte array) in the image field.
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class User {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private String username;

    @Persistent
    private String password;

    boolean sa;


    public User(String u, String p)
    {
    	username=u;
    	password=p;
    	sa=false;
    }

    public boolean getSa()
    {
    	return sa;
    }
    
    public void setSa(boolean s)
    {
    	sa=s;
    }
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String un) {
         username=un;
    }

    public String getPassword() {
        return password;
    }
    
    public void setPassword(String pwd) {
         password=pwd;
    }
}
package com.shameela;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/*
 * @class StudentRegistration is an Entity with getters and setters
 */
@Entity
@ApiModel(description="All details about the student. ")
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentRegistration {
	
@Id
@GeneratedValue
long registrationnumber;

@ApiModelProperty(notes="Name should have atleast 2 characters")
@Size(min=2, message="Name should have atleast 2 characters")
String name;
int age;
String depid;

@Override
public String toString() {
	return "StudentRegistration [registrationnumber=" + registrationnumber + ", name=" + name + ", age=" + age
			+ ", depid=" + depid + "]";
}

public StudentRegistration() {
	super();
}

public StudentRegistration(long registrationnumber, String name, int age,String depid) {
	super();
	this.registrationnumber = registrationnumber;
	this.name = name;
	this.age = age;
	this.depid = depid;
}


public long getRegistrationnumber() {
	return registrationnumber;
}
public void setRegistrationnumber(long registrationnumber) {
	this.registrationnumber = registrationnumber;
}
public String getName() {
return name;
}
public void setName(String name) {
this.name = name;
}
public int getAge() {
return age;
}
public void setAge(int age) {
this.age = age;
}
public String getDepid() {
return depid;
}
public void setDepid(String depid) {
this.depid = depid;
}

}

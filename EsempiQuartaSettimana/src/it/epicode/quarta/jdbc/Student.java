package it.epicode.quarta.jdbc;

import java.time.LocalDate;

public class Student {
	
	private long id;
	private String name;
	private String surname;
	private LocalDate dob;
	public Student(long id, String name, String surname, LocalDate dob) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dob = dob;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", surname=" + surname + ", dob=" + dob + "]";
	}
	

}

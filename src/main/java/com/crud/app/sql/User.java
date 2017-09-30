package com.crud.app.sql;

import java.io.Serializable;

public class User implements Serializable
{
	private static final long serialVersionUID = 1L;

	private String name;
	private String surname;
	private String patronymic;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}
}

package com.computec.crud.crud.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document(collection = "products")
public class Producto {

	@Id
	private int id;
	private String nombre;
	private float precio;

}

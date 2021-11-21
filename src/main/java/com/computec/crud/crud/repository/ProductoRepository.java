package com.computec.crud.crud.repository;

import java.io.Serializable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.computec.crud.crud.entity.Producto;

@Repository
public interface ProductoRepository extends MongoRepository<Producto, Integer>{
	
}

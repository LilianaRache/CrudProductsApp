package com.computec.crud.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.computec.crud.crud.entity.Producto;
import com.computec.crud.crud.repository.ProductoRepository;

@Service
@Transactional
public class ProductoService {

	@Autowired
	ProductoRepository productoRepository;
	
	public List<Producto> list(){
		return productoRepository.findAll();		
	}
	
	public Optional<Producto> getProduct(int id){
		return productoRepository.findById(id);
	}
	
	public void saveProduct(Producto producto) {
		productoRepository.save(producto);
	}
	
	public void deleteById(int id) {
		productoRepository.deleteById(id);
	}
	
	
}

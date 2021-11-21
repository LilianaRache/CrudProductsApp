package com.computec.crud.crud.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.computec.crud.crud.entity.Producto;
import com.computec.crud.crud.service.ProductoService;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
//@EnableOAuth2Sso
public class ProductoController {

	@Autowired 
	private ProductoService productoService;

	@GetMapping("/findAll")
	public ResponseEntity<List<Producto>> getProducts() {
		List<Producto> list = productoService.list();
		return new ResponseEntity<List<Producto>>(list,HttpStatus.OK);
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<?> getProduct(@PathVariable int id) {
		if (productoService.getProduct(id).isEmpty()) {
			return new ResponseEntity<String>("el producto con id: " + id + " No existe", HttpStatus.BAD_REQUEST);
		}
		Producto producto = productoService.getProduct(id).get();
		return new ResponseEntity<Producto>(producto, HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<String> saveProduct(@RequestBody Producto producto) {
		try {
			Optional<Producto> getProductoDB = productoService.getProduct(producto.getId());
			if (!getProductoDB.isEmpty()) {
				return new ResponseEntity<String>("el producto con el id: " + producto.getId() + " ya existe", HttpStatus.BAD_REQUEST);
			}
			productoService.saveProduct(producto);
		} catch (Exception e) {
			System.out.println("error.........." + e.getMessage());
		}
		return new ResponseEntity<String>("producto creado", HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable("id") int id, @RequestBody Producto productoUpdate) {
		
		if(id != productoUpdate.getId()) {
			return new ResponseEntity<String>("el id del producto: " + productoUpdate.getId() + " no puede ser diferente al id que intenta actualizar", HttpStatus.BAD_REQUEST);
		}
		Producto producto = productoService.getProduct(id).get();
		producto.setNombre(productoUpdate.getNombre());
		producto.setPrecio(productoUpdate.getPrecio());
		productoService.saveProduct(producto);
		return new ResponseEntity<String>("producto actualizado", HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id) {
		if (productoService.getProduct(id).isEmpty()) {
			return new ResponseEntity<String>("el producto con id: " + id + " No existe", HttpStatus.BAD_REQUEST);
		}
		productoService.deleteById(id);
		return new ResponseEntity<String>("el producto fue eliminado", HttpStatus.OK);
	}

	
}

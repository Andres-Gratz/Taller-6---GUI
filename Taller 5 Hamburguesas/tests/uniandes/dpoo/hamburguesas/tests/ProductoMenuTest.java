package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest {
	private ProductoMenu producto;
	
	@BeforeEach
	public void setUp() {
		producto = new ProductoMenu("Hamburguesa", 10000);
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("Hamburguesa", producto.getNombre(),"El nombre del producto no es el esperado");	
	}
	
	@Test
	public void testGetPrecio() {
		assertEquals(10000, producto.getPrecio(), "El precio del producto no es el esperado");
	}
	
	@Test
	public void testGenerarTextoFactura(){
		String textoFactura = producto.generarTextoFactura();
		assertTrue(textoFactura.contains("Hamburguesa"), "El texto de la factura no contiene el nombre del producto");
		assertTrue(textoFactura.contains("10000"), "El texto de la factura no contiene el precio del producto");
		assertTrue(textoFactura.endsWith("\n"), "El texto de la factura debería terminar con un salto de línea");
	}
	
	@Test
	public void testConstructor() {
		ProductoMenu nuevo = new ProductoMenu("Papas", 5000);
		assertAll("Constructor correcto",
		() -> assertEquals("Papas", nuevo.getNombre()),
		() -> assertEquals(5000, nuevo.getPrecio()));
	}
}

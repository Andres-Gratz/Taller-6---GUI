package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {
	private ProductoMenu base;
	private ProductoAjustado ajustado;
	
	@BeforeEach
	public void setUp() {
		base = new ProductoMenu("Hamburguesa", 10000);
		ajustado = new ProductoAjustado(base);
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("Hamburguesa", ajustado.getNombre(), "El nombre del producto ajustado no coincide con el del producto base");
	}
	
	@Test
	public void testGetPrecioSinAgregados() {
		assertEquals(10000, ajustado.getPrecio(), "El precio del producto sin agregados no coincide con el del producto base");
	}
	
	@Test
	public void TestGetPrecioConAgregadps() {
		Ingrediente queso = new Ingrediente("Queso", 1000);
		Ingrediente tomate = new Ingrediente("Tomate", 500);
		
		ajustado.añadirIngrediente(queso);
		ajustado.añadirIngrediente(tomate);
		
		int esperado = 10000 + 1000 + 500;
		
		assertEquals(esperado, ajustado.getPrecio(), "El precio debe incluir el precio de los ingredientes adicionados");
	}
	
	@Test
	public void testGenerarTextoFactura() {
		Ingrediente queso = new Ingrediente("Queso", 1000);
		Ingrediente cebolla = new Ingrediente("Cebolla", 500);
		
		ajustado.añadirIngrediente(queso);
		ajustado.eliminarIngrediente(cebolla);
		
		String textoFactura = ajustado.generarTextoFactura();
		assertTrue(textoFactura.contains("Hamburguesa"), "El texto de la factura no contiene el nombre del producto base");
		assertTrue(textoFactura.contains("+Queso"), "El texto de la factura no contiene el nombre del producto agregado");
		assertTrue(textoFactura.contains("Cebolla"), "El texto de la factura no contiene el nombre del producto eliminado");
		assertTrue(textoFactura.contains("1000"), "El texto de la factura no contiene el precio adicional del producto agregado");
		assertTrue(textoFactura.endsWith("\n"), "El texto de la factura no termina con un salto de línea");
	}
}

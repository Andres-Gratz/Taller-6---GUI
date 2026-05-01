package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {
	private Combo combo;
	private ProductoMenu hamburguesa;
	private ProductoMenu papas;
	private ProductoMenu gaseosa;
	
	@BeforeEach
	public void setUp() {
		hamburguesa = new ProductoMenu("Hamburguesa", 10000);
		papas = new ProductoMenu("Papas", 5000);
		gaseosa = new ProductoMenu("Gaseosa", 2500);
		ArrayList<ProductoMenu> items = new ArrayList<>();
		items.add(hamburguesa);
		items.add(papas);
		items.add(gaseosa);
		combo = new Combo("Combo 1", 0.5, items);
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("Combo 1", combo.getNombre(), "El nombre del combo no es el eperado");
	}
	
	@Test
	public void testGetPrecio() {
		int esperado = (int)(hamburguesa.getPrecio() * 0.5 + papas.getPrecio() * 0.5 + gaseosa.getPrecio() * 0.5);
		assertEquals(esperado, combo.getPrecio(), "El precio del combo no es el esperado");
	}
	
	@Test
	public void testGenerarTextoFactura() {
		String textoFactura = combo.generarTextoFactura();
		assertTrue(textoFactura.contains("Combo 1"), "El texto de la factura no contiene el nombre del combo");
		assertTrue(textoFactura.contains("Descuento"), "El texto de la factura no contiene la palabra descuento");
		assertTrue(textoFactura.contains(String.valueOf(combo.getPrecio())), "Debe incluir el precio calculado del combo");
		assertTrue(textoFactura.endsWith("\n"), "El texto de la factura debería terminar con un salto de línea");
	}
	
	@Test
	public void testConstructor() {
		ArrayList<ProductoMenu> items = new ArrayList<>();
		items.add(new ProductoMenu("Hamburguesa", 10000));
		items.add(new ProductoMenu("Papas", 5000));
		
		Combo nuevoCombo = new Combo("Combo 1", 0.5, items);
		
		assertAll("Constructor correcto",
				() -> assertEquals("Combo 1", nuevoCombo.getNombre()),
				() -> assertEquals((int)((10000+5000)*0.5), nuevoCombo.getPrecio()),
				() -> assertTrue(nuevoCombo.generarTextoFactura().contains("Combo 1")));
	}
}

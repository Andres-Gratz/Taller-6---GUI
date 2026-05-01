package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class PedidoTest {
	private Pedido pedido;
	private ProductoMenu hamburguesa;
	private ProductoMenu papas;
	
	@BeforeEach
	public void setUp() {
        pedido = new Pedido("Juan Perez", "Cra 7 # 2-10");
        hamburguesa = new ProductoMenu("Hamburguesa sencilla", 15000);
        papas = new ProductoMenu("Papas medianas", 5000);
    }
	
	@Test
	public void testConstructor() {
        assertEquals("Juan Perez", pedido.getNombreCliente());
        String factura = pedido.generarTextoFactura();
        assertTrue(factura.contains("Dirección: Cra 7 # 2-10"), "La factura debe incluir la dirección del cliente");
    }
	
	@Test
	public void testGetIdPedido() {
        int id1 = pedido.getIdPedido();
        Pedido otro = new Pedido("Maria Lopez", "Calle 10 # 5-20");
        int id2 = otro.getIdPedido();
        assertTrue(id2 > id1, "El id del segundo pedido debe ser mayor que el primero");
    }
	
	@Test
	public void testAgregarProductoYPrecios() {
		pedido.agregarProducto(hamburguesa);
        pedido.agregarProducto(papas);

        int netoEsperado = hamburguesa.getPrecio() + papas.getPrecio();
        int ivaEsperado = (int)(netoEsperado * 0.19);
        int totalEsperado = netoEsperado + ivaEsperado;

        assertEquals(totalEsperado, pedido.getPrecioTotalPedido());
	}
	
	@Test
	public void testGenerarTextoFactura() {
        pedido.agregarProducto(hamburguesa);
        String factura = pedido.generarTextoFactura();

        assertTrue(factura.contains("Cliente: Juan Perez"));
        assertTrue(factura.contains("Dirección: Cra 7 # 2-10"));
        assertTrue(factura.contains("Hamburguesa sencilla"));
        assertTrue(factura.contains("Precio Total"));
    }
	
	@Test
	public void testGuardarFactura() throws FileNotFoundException {
        pedido.agregarProducto(hamburguesa);
        File archivo = new File("factura_test.txt");
        pedido.guardarFactura(archivo);

        Scanner sc = new Scanner(archivo);
        String contenido = sc.useDelimiter("\\Z").next();
        sc.close();

        assertTrue(contenido.contains("Cliente: Juan Perez"));
        assertTrue(contenido.contains("Hamburguesa sencilla"));
        archivo.delete();
    }
}

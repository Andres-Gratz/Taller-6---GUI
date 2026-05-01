package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import uniandes.dpoo.hamburguesas.mundo.*;
import uniandes.dpoo.hamburguesas.excepciones.*;

public class RestauranteTest {

    private Restaurante restaurante;

    @BeforeEach
    public void setUp() {
        restaurante = new Restaurante();
    }

    @Test
    public void testIniciarPedido() throws YaHayUnPedidoEnCursoException {
        restaurante.iniciarPedido("Juan Perez", "Cra 7 # 2-10");
        assertNotNull(restaurante.getPedidoEnCurso(), "Debe existir un pedido en curso");
        assertEquals("Juan Perez", restaurante.getPedidoEnCurso().getNombreCliente());
    }

    @Test
    public void testIniciarPedidoYaExiste() throws YaHayUnPedidoEnCursoException {
        restaurante.iniciarPedido("Juan Perez", "Cra 7 # 2-10");
        assertThrows(YaHayUnPedidoEnCursoException.class,
            () -> restaurante.iniciarPedido("Maria Lopez", "Calle 10 # 5-20"));
    }

    @Test
    public void testCerrarPedidoSinIniciar() throws NoHayPedidoEnCursoException, IOException, YaHayUnPedidoEnCursoException {
    	File carpeta = new File("./facturas");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        restaurante.iniciarPedido("Juan Perez", "Cra 7 # 2-10");
        Pedido pedido = restaurante.getPedidoEnCurso();
        pedido.agregarProducto(new ProductoMenu("Hamburguesa", 15000));

        restaurante.cerrarYGuardarPedido();

        assertNull(restaurante.getPedidoEnCurso(), "El pedido en curso debe quedar en null");

        File factura = new File("./facturas/factura_" + pedido.getIdPedido() + ".txt");
        assertTrue(factura.exists(), "El archivo de factura debe existir");
        factura.delete();
    }

    @Test
    public void testCerrarYGuardarPedido() throws Exception {
        File carpeta = new File("./facturas");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }

        restaurante.iniciarPedido("Juan Perez", "Cra 7 # 2-10");
        Pedido pedido = restaurante.getPedidoEnCurso();
        pedido.agregarProducto(new ProductoMenu("Hamburguesa", 15000));

        restaurante.cerrarYGuardarPedido();

        assertNull(restaurante.getPedidoEnCurso(), "El pedido en curso debe quedar en null");

        File factura = new File("./facturas/factura_" + pedido.getIdPedido() + ".txt");
        assertTrue(factura.exists(), "El archivo de factura debe existir");
        factura.delete();
    }

    @Test
    public void testCargarIngredientes() throws Exception {
    	File archivoIngredientes = new File("ingredientes_test.txt");
        try (FileWriter fw = new FileWriter(archivoIngredientes)) {
            fw.write("Queso;2000\nTomate;1000\n");
        }

        File archivoMenu = new File("menu_test.txt");
        try (FileWriter fw = new FileWriter(archivoMenu)) {
            fw.write("");
        }

        File archivoCombos = new File("combos_test.txt");
        try (FileWriter fw = new FileWriter(archivoCombos)) {
            fw.write("");
        }

        restaurante.cargarInformacionRestaurante(archivoIngredientes, archivoMenu, archivoCombos);

        assertFalse(restaurante.getIngredientes().isEmpty(), "La lista de ingredientes no debe estar vacía");
        archivoIngredientes.delete();
        archivoMenu.delete();
        archivoCombos.delete();
    }
}

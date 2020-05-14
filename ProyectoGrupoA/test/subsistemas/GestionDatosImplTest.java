package subsistemas;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import bean.BaseEstadistica;
import bean.Factura;
import bean.Menu;
import bean.Plato;

class GestionDatosImplTest {

	Factura factura;
	Menu menu;
	GestionDatosImpl gestor;
	int vale = 0;
	int idFactura = 0;

	@Tag("CajaNegra")
	@DisplayName("Test de clases validas")
	@Nested
	class PruebasValidas {
		@BeforeEach
		void setUp() throws Exception {

			factura = new Factura(vale, idFactura, 0, 0, 0, "agua");
			gestor = new GestionDatosImpl();
			vale++;
			idFactura++;

			// Atributos para menu
			Plato plato1 = new Plato(1, "ensalada", "Ensalada rica rica", null, 1, null, null);
			Plato plato2 = new Plato(2, "pollO", "pollolaksd", null, 2, null, null);
			Plato plato3 = new Plato(3, "tarta", "tartasijdk", null, 3, null, null);
			ArrayList<Plato> primeros = new ArrayList();
			primeros.add(plato1);
			primeros.add(plato2);
			primeros.add(plato3);

			Plato plato4 = new Plato(4, "ensalada", "Ensalada rica rica", null, 1, null, null);
			Plato plato5 = new Plato(5, "pollO", "pollolaksd", null, 2, null, null);
			Plato plato6 = new Plato(6, "tarta", "tartasijdk", null, 3, null, null);
			ArrayList<Plato> segundos = new ArrayList();
			segundos.add(plato4);
			segundos.add(plato5);
			segundos.add(plato6);

			Plato plato7 = new Plato(7, "ensalada", "Ensalada rica rica", null, 1, null, null);
			Plato plato8 = new Plato(8, "pollO", "pollolaksd", null, 2, null, null);
			Plato plato9 = new Plato(9, "tarta", "tartasijdk", null, 3, null, null);
			ArrayList<Plato> postres = new ArrayList();
			postres.add(plato7);
			postres.add(plato8);
			postres.add(plato9);

			int id = (int) System.currentTimeMillis();

			menu = new Menu(id, new Date(), primeros, segundos, postres);
		}

		@AfterEach
		void tearDown() throws Exception {
			factura = null;
			gestor = null;

			// Operaciones de almacenar menu
			menu = null;

		}

		@DisplayName("Almacenar Factura - CP18")
		@Test
		void test() {
			Assertions.assertDoesNotThrow(() -> {
				gestor.almacenarFactura(factura);
			}, "Ha habido una excepcion");
		}

		@DisplayName("Almacenar Menu - CP30")
		@Test
		void testAlmacenarMenu() {
			Assertions.assertDoesNotThrow(() -> {
				gestor.almacenarMenu(menu);
			}, "Ha habido una excepcion");
		}

		@DisplayName("Obtener Facturas - CP28")
		@Test
		void testObtenerFacturas() {
			ArrayList<Factura> facturas = new ArrayList<>();
			assertEquals(facturas.getClass(), gestor.obtenerFacturas().getClass(), "Se esperaba un array de facturas");
		}

		@DisplayName("Almacenar Base Estadistica - CP8")
		@Test
		void testAlmacenarBaseEstadistica() {
			ArrayList<Integer> valoraciones = new ArrayList();
			valoraciones.add(5);
			valoraciones.add(3);
			valoraciones.add(9);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
			Date recogida;
			Date entrega;
			try {
				recogida = format.parse("2020-03-30T12:50:05+0200");
				entrega = format.parse("2020-03-30T12:30:05+0200");

				BaseEstadistica base = new BaseEstadistica(5, entrega, recogida, "Daniel", valoraciones);

				Assertions.assertDoesNotThrow(() -> {
					gestor.almacenarBaseEstadistica(base);
				}, "Ha habido una excepcion");
			} catch (ParseException e) {
				e.printStackTrace();
			}	
		}
		
		@DisplayName("Obtener Bases - CP47")
		@Test
		void testObtenerBases() {
			ArrayList<BaseEstadistica> bases = new ArrayList<>();
			assertEquals(bases.getClass(), gestor.obtenerBases().getClass(), "Se esperaba un array de BaseEstadistica");
		}
		
		@DisplayName("Obtener Platos - CP48")
		@Test
		void testObtenerPlatos() {
			ArrayList<BaseEstadistica> platos = new ArrayList<>();
			assertEquals(platos.getClass(), gestor.obtenerPlatos().getClass(), "Se esperaba un array de BaseEstadistica");
		}
		
	}

	@Tag("CajaNegra")
	@DisplayName("Test de clases no validas")
	@Nested
	class PruebasNoValidas {

		@BeforeEach
		void setUp() throws Exception {
			factura = new Factura(1, 1, 0, 0, 0, "agua");
			gestor = new GestionDatosImpl();

			// Atributos para almacenar menu
			Plato plato1 = new Plato(1, "ensalada", "Ensalada rica rica", null, 1, null, null);
			Plato plato2 = new Plato(2, "pollO", "pollolaksd", null, 2, null, null);
			Plato plato3 = new Plato(3, "tarta", "tartasijdk", null, 3, null, null);
			ArrayList<Plato> primeros = new ArrayList();
			primeros.add(plato1);
			primeros.add(plato2);
			primeros.add(plato3);

			Plato plato4 = new Plato(4, "ensalada", "Ensalada rica rica", null, 1, null, null);
			Plato plato5 = new Plato(5, "pollO", "pollolaksd", null, 2, null, null);
			Plato plato6 = new Plato(6, "tarta", "tartasijdk", null, 3, null, null);
			ArrayList<Plato> segundos = new ArrayList();
			segundos.add(plato4);
			segundos.add(plato5);
			segundos.add(plato6);

			Plato plato7 = new Plato(7, "ensalada", "Ensalada rica rica", null, 1, null, null);
			Plato plato8 = new Plato(8, "pollO", "pollolaksd", null, 2, null, null);
			Plato plato9 = new Plato(9, "tarta", "tartasijdk", null, 3, null, null);
			ArrayList<Plato> postres = new ArrayList();
			postres.add(plato7);
			postres.add(plato8);
			postres.add(plato9);

			int id = (int) System.currentTimeMillis();

			menu = new Menu(id, new Date(), primeros, segundos, postres);

		}

		@AfterEach
		void tearDown() throws Exception {
			factura = null;
			gestor = null;

			// Almacenar menu
			menu = null;

		}

		@DisplayName("Almacenar Factura - CP19")
		@Test
		void almacenarFacturaIdValeIncorrecto() {
			factura.setVale(-1);
			factura.setBandeja(1);
			Assertions.assertThrows(Exception.class, () -> {
				gestor.almacenarFactura(factura);
			}, "Se esperaba excepci�n por vale menor que 0");
		}

		@DisplayName("Almacenar Factura - CP20")
		@Test
		void almacenarFacturaBandejaNull() {
			factura.setVale(1);
			factura.setBandeja(null);
			Assertions.assertThrows(Exception.class, () -> {
				gestor.almacenarFactura(factura);
			}, "Se esperaba excepci�n por bandeja = null");
		}

		@DisplayName("Almacenar Factura - CP21")
		@Test
		void almacenarFacturaIdBandejaIncorreecto() {
			factura.setVale(2);
			factura.setBandeja(-1);
			Assertions.assertThrows(Exception.class, () -> {
				gestor.almacenarFactura(factura);
			}, "Se esperaba excepci�n por idBandeja menor que 0");
		}

		@DisplayName("Almacenar Factura - CP22")
		@Test
		void almacenarFacturaIdPrimerPlatoIncorrecto() {
			factura.setPlato1(-1);
			factura.setVale(3);
			factura.setBandeja(3);
			Assertions.assertThrows(Exception.class, () -> {
				gestor.almacenarFactura(factura);
			}, "Se esperaba excepci�n por idPlato1 menor que 0");
		}

		@DisplayName("Almacenar Factura - CP23")
		@Test
		void almacenarFacturaIdSegundoPlatoIncorrecto() {
			factura.setPlato2(-1);
			factura.setVale(4);
			factura.setBandeja(4);
			Assertions.assertThrows(Exception.class, () -> {
				gestor.almacenarFactura(factura);
			}, "Se esperaba excepci�n por idPlato2 menor que 0");
		}

		@DisplayName("Almacenar Factura - CP24")
		@Test
		void almacenarFacturaIdPostrePlatoIncorrecto() {
			factura.setPostre(-1);
			factura.setVale(5);
			factura.setBandeja(5);
			Assertions.assertThrows(Exception.class, () -> {
				gestor.almacenarFactura(factura);
			}, "Se esperaba excepci�n por idPostre menor que 0");
		}

		@DisplayName("Almacenar Factura -CP25")
		@Test
		void almacenarFacturaBebidaNull() {
			factura.setBebida(null);
			factura.setVale(6);
			factura.setBandeja(6);
			Assertions.assertThrows(Exception.class, () -> {
				gestor.almacenarFactura(factura);
			}, "Se esperaba excepci�n por bebida=null");
		}

		@DisplayName("Almacenar Menu - CP31")
		@Test
		void almacenarMenuNull() {
			menu = null;
			Assertions.assertThrows(Exception.class, () -> {
				gestor.almacenarMenu(menu);
			}, "Se esperaba excepcion por menu=null");
		}

		@DisplayName("Almacenar Menu - CP32")
		@Test
		void almacenarMenuIdNegativo() {
			menu.setId(-1);
			Assertions.assertThrows(Exception.class, () -> {
				gestor.almacenarMenu(menu);
			}, "Se esperaba excepcion por id negativo");
		}

		@DisplayName("Almacenar Menu - CP33")
		@Test
		void almacenarMenuFechaNull() {
			menu.setFecha(null);
			Assertions.assertThrows(Exception.class, () -> {
				gestor.almacenarMenu(menu);
			}, "Se esperaba excepcion por fecha=null");
		}

		@DisplayName("Almacenar Menu - CP34")
		@Test
		void almacenarMenuPrimerosNull() {
			menu.setPrimeros(null);
			Assertions.assertThrows(Exception.class, () -> {
				gestor.almacenarMenu(menu);
			}, "Se esperaba excepcion por primeros=null");
		}

		@DisplayName("Almacenar Menu - CP35")
		@Test
		void almacenarMenuMasPrimeros() {
			Plato platoAux = new Plato(7, "ensalada", "Ensalada rica rica", null, 1, null, null);
			menu.getPrimeros().add(platoAux);
			Assertions.assertThrows(Exception.class, () -> {
				gestor.almacenarMenu(menu);
			}, "Se esperaba excepcion por numero de primeros mayor que 3");
		}

		@DisplayName("Almacenar Menu - CP36")
		@Test
		void almacenarMenuMenosPrimeros() {
			menu.getPrimeros().remove(0);
			Assertions.assertThrows(Exception.class, () -> {
				gestor.almacenarMenu(menu);
			}, "Se esperaba excepcion por numero de primeros menor que 3");
		}

		@DisplayName("Almacenar Menu - CP37")
		@Test
		void almacenarMenuSegundosNull() {
			menu.setSegundos(null);
			Assertions.assertThrows(Exception.class, () -> {
				gestor.almacenarMenu(menu);
			}, "Se esperaba excepcion por segundos=null");
		}

		@DisplayName("Almacenar Menu - CP38")
		@Test
		void almacenarMenuMasSegundos() {
			Plato platoAux = new Plato(2, "ensalada", "Ensalada rica rica", null, 1, null, null);
			menu.getSegundos().add(platoAux);
			Assertions.assertThrows(Exception.class, () -> {
				gestor.almacenarMenu(menu);
			}, "Se esperaba excepcion por numero de segundos mayor que 3");
		}

		@DisplayName("Almacenar Menu - CP39")
		@Test
		void almacenarMenuMenosSegundos() {
			menu.getSegundos().remove(0);
			Assertions.assertThrows(Exception.class, () -> {
				gestor.almacenarMenu(menu);
			}, "Se esperaba excepcion por numero de segundos menor que 3");
		}

		@DisplayName("Almacenar Menu - CP40")
		@Test
		void almacenarMenuPostressNull() {
			menu.setPostres(null);
			Assertions.assertThrows(Exception.class, () -> {
				gestor.almacenarMenu(menu);
			}, "Se esperaba excepcion por postres=null");
		}

		@DisplayName("Almacenar Menu - CP41")
		@Test
		void almacenarMenuMasPostres() {
			Plato platoAux = new Plato(4, "ensalada", "Ensalada rica rica", null, 1, null, null);
			menu.getPostres().add(platoAux);
			Assertions.assertThrows(Exception.class, () -> {
				gestor.almacenarMenu(menu);
			}, "Se esperaba excepcion por numero de postres mayor que 3");
		}

		@DisplayName("Almacenar Menu - CP42")
		@Test
		void almacenarMenuMenosPostres() {
			menu.getPostres().remove(0);
			Assertions.assertThrows(Exception.class, () -> {
				gestor.almacenarMenu(menu);
			}, "Se esperaba excepcion por numero de postres menor que 3");
		}

	}
}

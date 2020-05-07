package subsistemas;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import bean.Plato;
import interfaces.ItfSensores;

class GestionPagosCalificacionesImplTest {

	/**
	 * Código de las pruebas de caja negra asociadas al método valorar platos.
	 * @author Daniel Martínez Fernández
	 *
	 */
	GestionPagosCalificacionesImpl gp;
	ItfSensores s;
	GestorSeleccionMenuImpl smenu;
	GestionMenus gestorMenus;
	GestionDatosImpl gestorDatos;
	
	@DisplayName("Test de clases válidas")
	@Nested
	class PruebasVálidas{
		@BeforeEach
		void setUp() throws Exception {
			gp = new GestionPagosCalificacionesImpl();
			s = new SensoresImpl(gp);
			gp.setSensores(s);
			gestorDatos = new GestionDatosImpl();
			gestorMenus = new GestionMenus(gestorDatos);
			smenu = new GestorSeleccionMenuImpl(gestorMenus, s);
			//Creamos el menu para este día
			gestorMenus.crearMenu("Albor",1);
			gestorMenus.escogerPlatos(1, 1, 1);
			gestorMenus.escogerPlatos(2, 1, 1);
			gestorMenus.escogerPlatos(3, 1, 1);
			gestorMenus.escogerPlatos(4, 1, 2);
			gestorMenus.escogerPlatos(5, 1, 2);
			gestorMenus.escogerPlatos(6, 1, 2);
			gestorMenus.escogerPlatos(7, 1, 3);
			gestorMenus.escogerPlatos(8, 1, 3);
			gestorMenus.escogerPlatos(9, 1, 3);
			//Antes de valorar los platos hay que seleccionar el menú en la aplicación.
			Plato plato1 = new Plato(1, "ensalada", "Ensalada rica rica", null, 1, null, null);
			Plato plato2 = new Plato(4, "pollO", "pollolaksd", null, 2, null, null);
			Plato plato3 = new Plato(7, "tarta", "tartasijdk", null, 3, null, null);
			String bebida = new String("agua");
			
			smenu.seleccionarMenu(plato1, plato2, plato3, bebida);
		}

		@AfterEach
		void tearDown() throws Exception {
			s = null;
			gp = null;
			gestorDatos = null;
			gestorMenus = null;
			smenu = null;
		}
		
		@DisplayName("Valorar platos - CP0")
		@Test
		void testValorarPlatos() {
			Assertions.assertDoesNotThrow(()->{gp.valorarPlatos("Daniel", 2, 5, 8);}, "Ha habido una excepcion");
		}
	}
	
	@DisplayName("Test de clases no válidas")
	@Nested
	class PruebasNoVálidas{
		@BeforeEach
		void setUp() throws Exception {
			gp = new GestionPagosCalificacionesImpl();
			s = new SensoresImpl(gp);
			gp.setSensores(s);
			gestorDatos = new GestionDatosImpl();
			gestorMenus = new GestionMenus(gestorDatos);
			smenu = new GestorSeleccionMenuImpl(gestorMenus, s);
			//Creamos el menu para este día
			gestorMenus.crearMenu("Albor",1);
			gestorMenus.escogerPlatos(1, 1, 1);
			gestorMenus.escogerPlatos(2, 1, 1);
			gestorMenus.escogerPlatos(3, 1, 1);
			gestorMenus.escogerPlatos(4, 1, 2);
			gestorMenus.escogerPlatos(5, 1, 2);
			gestorMenus.escogerPlatos(6, 1, 2);
			gestorMenus.escogerPlatos(7, 1, 3);
			gestorMenus.escogerPlatos(8, 1, 3);
			gestorMenus.escogerPlatos(9, 1, 3);
			//Antes de valorar los platos hay que seleccionar el menú en la aplicación.
			Plato plato1 = new Plato(1, "ensalada", "Ensalada rica rica", null, 1, null, null);
			Plato plato2 = new Plato(4, "pollO", "pollolaksd", null, 2, null, null);
			Plato plato3 = new Plato(7, "tarta", "tartasijdk", null, 3, null, null);
			String bebida = new String("agua");
			
			smenu.seleccionarMenu(plato1, plato2, plato3, bebida);
		}

		@AfterEach
		void tearDown() throws Exception {
			s = null;
			gp = null;
			gestorDatos = null;
			gestorMenus = null;
			smenu = null;
		}
		
		@DisplayName("Valorar platos - CP1")
		@Test
		void testValorarPlatosComensal() {
			assertThrows(Exception.class, ()->{
				gp.valorarPlatos(null, 2, 5, 9);
			}, "Se esperaba excepcion por comensal nulo");
		}
		
		@DisplayName("Valorar platos - CP2")
		@Test
		void testValorarPlatosPrimeroMayor() {
			assertThrows(Exception.class, ()->{
				gp.valorarPlatos("Daniel", 25, 5, 9);
			}, "Se esperaba excepcion por valoracion primer plato mayor que 10");
		}
		
		@DisplayName("Valorar platos - CP3")
		@Test
		void testValorarPlatosPrimeroMenor() {
			assertThrows(Exception.class, ()->{
				gp.valorarPlatos("Daniel", -1, 5, 9);
			}, "Se esperaba excepcion por valoracion primer plato menor que 0");
		}
		@DisplayName("Valorar platos - CP4")
		@Test
		void testValorarPlatosSegundoMenor() {
			assertThrows(Exception.class, ()->{
				gp.valorarPlatos("Daniel", 3, -5, 9);
			}, "Se esperaba excepcion por valoracion segundo plato menor que 0");
		}
		
		@DisplayName("Valorar platos - CP5")
		@Test
		void testValorarPlatosSegundoMayor() {
			assertThrows(Exception.class, ()->{
				gp.valorarPlatos("Daniel", 3, 15, 9);
			}, "Se esperaba excepcion por valoracion segundo plato mayor que 10");
		}
		
		@DisplayName("Valorar platos - CP6")
		@Test
		void testValorarPlatosTerceroMenor() {
			assertThrows(Exception.class, ()->{
				gp.valorarPlatos("Daniel", 3, 5, -2);
			}, "Se esperaba excepcion por valoracion postre menor que 0");
		}
		@DisplayName("Valorar platos - CP7")
		@Test
		void testValorarPlatosTerceroMayor() {
			assertThrows(Exception.class, ()->{
				gp.valorarPlatos("Daniel", 3, 5, 15);
			}, "Se esperaba excepcion por valoracion postre mayor que 10");
		}
	}
}

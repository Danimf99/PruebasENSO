package subsistemas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import bean.Menu;
import bean.Plato;
import interfaces.ItfSensores;

class GestionMenusTest {
	
	/**
	 * Codigo de las pruebas de caja negra asociadas al m�todo escoger plato.
	 * @author Manuel Leston
	 *
	 */

	
	GestorSeleccionMenuImpl smenu;
	GestionMenus gestorMenus;
	GestionDatosImpl gestorDatos;
	
	@Tag("CajaNegra")
	@DisplayName("Test de clases validas")
	@Nested
	class PruebasValidas{
		@BeforeEach
		void setUp() throws Exception {
			gestorDatos = new GestionDatosImpl();
			gestorMenus = new GestionMenus(gestorDatos);
			//Creamos el menu para este dia
			gestorMenus.crearMenu("Albor",1);
		}

		@AfterEach
		void tearDown() throws Exception {
			gestorDatos = null;
			gestorMenus = null;
			smenu = null;
		}
		
		@DisplayName("Escoger platos - CP10")
		@Test
		void testEscogerPlatos() {
			Assertions.assertDoesNotThrow(()->{gestorMenus.escogerPlatos(1, 1, 1);}, "Ha habido una excepcion");
		}
		
		@DisplayName("Obtener Menu - CP44")
		@Test
		void testObtenerMenu() {
			Menu[] menus = new Menu[5];
			assertEquals(menus.getClass(), gestorMenus.obtenerMenus().getClass(), "Se esperaba un array de Menus");
		}
		
		@DisplayName("Obtener Menu Día - CP45")
		@Test
		void testObtenerMenuDia() {
			Assertions.assertDoesNotThrow(() -> {
				gestorMenus.obtenerMenuDelDia();
			}, "Ha habido una excepcion");
		}
	}
	
	@Tag("CajaNegra")
	@DisplayName("Test de clases no validas")
	@Nested
	class PruebasNoValidas{
		@BeforeEach
		void setUp() throws Exception {
			gestorDatos = new GestionDatosImpl();
			gestorMenus = new GestionMenus(gestorDatos);
			//Creamos el menu para este dia
			gestorMenus.crearMenu("Albor",1);
		}

		@AfterEach
		void tearDown() throws Exception {
			gestorDatos = null;
			gestorMenus = null;
			smenu = null;
		}
		
		@DisplayName("Escoger platos (Tipo fuera de rango) - CP11")
		@Test
		void testEscogerPlatoTipo() {
			assertThrows(Exception.class, ()->{
				gestorMenus.escogerPlatos(1, 1, 4);
			}, "Se esperaba excepcion por tipo fuera de rango");
		}
		
		@DisplayName("Escoger platos (Tipo con valor negativo) - CP12")
		@Test
		void testEscogerPlatoTipoNegativo() {
			assertThrows(Exception.class, ()->{
				gestorMenus.escogerPlatos(1, 1, -1);
			}, "Se esperaba excepcion por tipo negativo");
		}
		
		@DisplayName("Escoger platos (ID con valor negativo) - CP13")
		@Test
		void testEscogerPlatoIDNegativo() {
			assertThrows(Exception.class, ()->{
				gestorMenus.escogerPlatos(-1, 1, 1);
			}, "Se esperaba excepcion por ID negativo");
		}
		
		@DisplayName("Escoger platos (Menu con valor negativo) - CP14")
		@Test
		void testEscogerPlatoMenuNegativo() {
			assertThrows(Exception.class, ()->{
				gestorMenus.escogerPlatos(1, -23, 1);
			}, "Se esperaba excepcion por Menu negativo");
		}
		
		@DisplayName("Escoger platos (Menu con valor fuera de rango) - CP15")
		@Test
		void testEscogerPlatoMenu() {
			assertThrows(Exception.class, ()->{
				gestorMenus.escogerPlatos(1, 23, 1);
			}, "Se esperaba excepcion por Menu fuera de rango");
		}
	}
	
	
	@DisplayName("Test de obtenerPlatos y addPlato")
	@Nested
	class PruebasSubmetodos{
		@BeforeEach
		void setUp() throws Exception {
			gestorDatos = new GestionDatosImpl();
			gestorMenus = new GestionMenus(gestorDatos);
			//Creamos el menu para este dia
			gestorMenus.crearMenu("Albor",1);
			gestorMenus.escogerPlatos(2, 1, 1);
			gestorMenus.escogerPlatos(3, 1, 1);
			gestorMenus.escogerPlatos(4, 1, 2);
			gestorMenus.escogerPlatos(5, 1, 2);
			gestorMenus.escogerPlatos(6, 1, 2);
			gestorMenus.escogerPlatos(7, 1, 3);
			gestorMenus.escogerPlatos(8, 1, 3);
			gestorMenus.escogerPlatos(9, 1, 3);
			
		}
		
		@DisplayName("Test valido de obtenerPlatos - CP16")
		@Test
		void testObtenerPlatos() {
			assertDoesNotThrow(()->{gestorDatos.obtenerPlatos("Albos");
			}, "Ha habido una excepcion");
		}
		
		@DisplayName("Test valido de addPlato - CP17")
		@Test
		void testAddPlato() {
			//En escoger platos dejar el primero sin determinar para insertarlo ahora
			Plato plato1 = new Plato(1, "tortilla", "Plato original de Marsella", "Albor", 1, "francesa", null);
			assertDoesNotThrow(()->{gestorMenus.obtenerMenuDelDia().addPlato(plato1);
			}, "Ha habido una excepcion");
		}
		
		
	}
	

}

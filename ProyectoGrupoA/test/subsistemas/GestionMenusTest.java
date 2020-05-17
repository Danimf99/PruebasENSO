package subsistemas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import bean.Menu;
import bean.Plato;
import interfaces.ItfSensores;

class GestionMenusTest {
	
	/**
	 * Codigo de las pruebas de caja negra asociadas al metodo escoger plato.
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
		
		@DisplayName("Obtener Menu Dia - CP45")
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
	
	@Tag("CajaBlanca")
	@DisplayName("Pruebas de caja blanca")
	@Nested
	class PruebasCajaBlanca{
		
		@BeforeEach
		void setUp() throws Exception {
			gestorDatos = Mockito.mock(GestionDatosImpl.class);
			gestorMenus = new GestionMenus(gestorDatos);
			//Creamos el menu para este dia
			
		}
		
		@AfterEach
		void tearDown() throws Exception {
			gestorDatos = null;
			gestorMenus = null;
			smenu = null;
		}
		/**
		 * Codigo de las pruebas de caja blanca asociadas al metodo escoger plato.
		 * @author Manuel Leston
		 *
		 */
		@DisplayName("Test escogerPlato (CB-Camino 1) - CP69")
		@Test
		void testCaminoUnoEscogerPlato() {
			Plato plato1 = new Plato(1, "ensalada", "Ensalada", null, 1, null, null);
			Plato plato2 = new Plato(4, "pollo", "pollo", null, 2, null, null);
			Plato plato3 = new Plato(7, "tarta", "tartas", null, 3, null, null);
			
			ArrayList<Plato> platos = new ArrayList<>();
			ArrayList<Plato> pruebaPlato = new ArrayList<>();
			pruebaPlato.add(plato1);
			pruebaPlato.add(plato2);
			pruebaPlato.add(plato3);
			
			Mockito.when(gestorDatos.obtenerPlatos("Albor")).thenReturn(new ArrayList<>(pruebaPlato));
			
			//Creamos el menu para este dia
			try {
				gestorMenus.crearMenu("Albor",1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			//Mockito.when(gestorMenus.obtenerMenuDelDia()).thenReturn(new Menu(1, null, new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
			
			
			Assertions.assertAll(()->{
				Exception e = Assertions.assertThrows(Exception.class, ()->{
					gestorMenus.escogerPlatos(1, 3, 1);
				}, "No se ha lanzado una excepcion");
				Assertions.assertEquals("Ese menu aun no esta creado",e.getMessage(), "El mensaje de la excepcion lanzada no es el esperado");
			});
			
		}
		
		@DisplayName("Test escogerPlato (CB-Camino 2) - CP70")
		@Test
		void testCaminoDosEscogerPlato() {
			Plato plato1 = new Plato(1, "ensalada", "Ensalada", null, 1, null, null);
			Plato plato2 = new Plato(4, "pollo", "pollo", null, 2, null, null);
			Plato plato3 = new Plato(7, "tarta", "tartas", null, 3, null, null);
			
			ArrayList<Plato> platos = new ArrayList<>();
			ArrayList<Plato> pruebaPlato = new ArrayList<>();
			pruebaPlato.add(plato1);
			pruebaPlato.add(plato2);
			pruebaPlato.add(plato3);
			
			Mockito.when(gestorDatos.obtenerPlatos("Albor")).thenReturn(new ArrayList<>(pruebaPlato));
			
			//Creamos el menu para este dia
			try {
				gestorMenus.crearMenu("Albor",1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			Assertions.assertAll(()->{
				Exception e = Assertions.assertThrows(Exception.class, ()->{
					gestorMenus.escogerPlatos(5, 1, 1);
				}, "No se ha lanzado una excepcion");
				Assertions.assertEquals("Ese plato no existe",e.getMessage(), "El mensaje de la excepcion lanzada no es el esperado");
			});
			
		}
		
		
		@DisplayName("Test escogerPlato (CB-Camino 3) - CP71")
		@Test
		void testCaminoTresEscogerPlato() {
			Plato plato1 = new Plato(1, "ensalada", "Ensalada", null, 1, null, null);
			Plato plato2 = new Plato(4, "pollo", "pollo", null, 2, null, null);
			Plato plato3 = new Plato(7, "tarta", "tartas", null, 3, null, null);
			
			ArrayList<Plato> platos = new ArrayList<>();
			ArrayList<Plato> pruebaPlato = new ArrayList<>();
			pruebaPlato.add(plato1);
			pruebaPlato.add(plato2);
			pruebaPlato.add(plato3);
			
			Mockito.when(gestorDatos.obtenerPlatos("Albor")).thenReturn(new ArrayList<>(pruebaPlato));
			
			//Creamos el menu para este dia
			try {
				gestorMenus.crearMenu("Albor",1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			Assertions.assertAll(()->{
				Exception e = Assertions.assertThrows(Exception.class, ()->{
					gestorMenus.escogerPlatos(1, 1, 3);
				}, "No se ha lanzado una excepcion");
				Assertions.assertEquals("Ese plato no es del tipo de plato solicitado",e.getMessage(), "El mensaje de la excepcion lanzada no es el esperado");
			});
		}
		
		
		@DisplayName("Test escogerPlato (CB-Camino 4) - CP72")
		@Test
		void testCaminoCuatroEscogerPlato() {
			Plato plato1 = new Plato(1, "ensalada", "Ensalada", null, 1, null, null);
			Plato plato2 = new Plato(2, "ensalada", "Ensalada", null, 1, null, null);
			Plato plato3 = new Plato(3, "ensalada", "Ensalada", null, 1, null, null);
			Plato plato4 = new Plato(4, "pollo", "pollo", null, 2, null, null);
			Plato plato5 = new Plato(5, "pollo", "pollo", null, 2, null, null);
			Plato plato6 = new Plato(6, "pollo", "pollo", null, 2, null, null);
			Plato plato7 = new Plato(7, "tarta", "tartas", null, 3, null, null);
			Plato plato8 = new Plato(8, "tarta", "tartas", null, 3, null, null);
			Plato plato9 = new Plato(9, "tarta", "tartas", null, 3, null, null);
			
			ArrayList<Plato> platos = new ArrayList<>();
			ArrayList<Plato> pruebaPlato = new ArrayList<>();
			pruebaPlato.add(plato1);
			pruebaPlato.add(plato2);
			pruebaPlato.add(plato3);
			pruebaPlato.add(plato4);
			pruebaPlato.add(plato5);
			pruebaPlato.add(plato6);
			pruebaPlato.add(plato7);
			pruebaPlato.add(plato8);
			pruebaPlato.add(plato9);
			
			Mockito.when(gestorDatos.obtenerPlatos("Albor")).thenReturn(new ArrayList<>(pruebaPlato));
			
			//Creamos el menu para este dia
			try {
				gestorMenus.crearMenu("Albor",1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			Assertions.assertDoesNotThrow(() -> {
				gestorMenus.escogerPlatos(1, 1, 1);
			}, "Ha habido una excepcion");
			
		}
		
		@DisplayName("Test escogerPlato (CB-Camino 5) - CP73")
		@Test
		void testCaminCincoEscogerPlato() {
			Plato plato1 = new Plato(1, "ensalada", "Ensalada", null, 1, null, null);
			Plato plato2 = new Plato(2, "ensalada", "Ensalada", null, 1, null, null);
			Plato plato3 = new Plato(3, "ensalada", "Ensalada", null, 1, null, null);
			Plato plato4 = new Plato(4, "pollo", "pollo", null, 2, null, null);
			Plato plato5 = new Plato(5, "pollo", "pollo", null, 2, null, null);
			Plato plato6 = new Plato(6, "pollo", "pollo", null, 2, null, null);
			Plato plato7 = new Plato(7, "tarta", "tartas", null, 3, null, null);
			Plato plato8 = new Plato(8, "tarta", "tartas", null, 3, null, null);
			Plato plato9 = new Plato(9, "tarta", "tartas", null, 3, null, null);
			
			ArrayList<Plato> platos = new ArrayList<>();
			ArrayList<Plato> pruebaPlato = new ArrayList<>();
			pruebaPlato.add(plato1);
			pruebaPlato.add(plato2);
			pruebaPlato.add(plato3);
			pruebaPlato.add(plato4);
			pruebaPlato.add(plato5);
			pruebaPlato.add(plato6);
			pruebaPlato.add(plato7);
			pruebaPlato.add(plato8);
			pruebaPlato.add(plato9);
			
			Mockito.when(gestorDatos.obtenerPlatos("Albor")).thenReturn(new ArrayList<>(pruebaPlato));
			
			//Creamos el menu para este dia
			try {
				gestorMenus.crearMenu("Albor",1);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			Assertions.assertDoesNotThrow(() -> {
				gestorMenus.escogerPlatos(4, 1, 2);
			}, "Ha habido una excepcion");
			
		}
	
		/**
		 * Codigo de las pruebas de caja blanca para el metodo crear Menu.
		 * @author Daniel Martinez
		 */
		@DisplayName("Crear Menu - CP55")
		@Test
		void testCaminoUnoCrearMenu() {
			Plato plato1 = new Plato(1, "ensalada", "Ensalada", null, 1, null, null);
			Plato plato2 = new Plato(4, "pollo", "pollo", null, 2, null, null);
			Plato plato3 = new Plato(7, "tarta", "tartas", null, 3, null, null);
			
			ArrayList<Plato> platos = new ArrayList<>();
			ArrayList<Plato> pruebaPlato = new ArrayList<>();
			pruebaPlato.add(plato1);
			pruebaPlato.add(plato2);
			pruebaPlato.add(plato3);
			
			Mockito.when(gestorDatos.obtenerPlatos("Solpor")).thenReturn(new ArrayList<>(pruebaPlato));
			
			Assertions.assertAll(()->{
				Assertions.assertDoesNotThrow(()->{
					platos.addAll(gestorMenus.crearMenu("Solpor", 2));
				});
				Assertions.assertEquals(plato1, platos.get(0), "El primer plato es diferente");
				Assertions.assertEquals(plato2, platos.get(1), "El segundo plato es diferente");
				Assertions.assertEquals(plato3, platos.get(2), "El tercer plato es diferente");
			});
			
		}
	
		
		@DisplayName("Crear Menu - CP57")
		@Test
		void testCaminoDosCrearMenu() {
			Plato plato1 = new Plato(1, "ensalada", "Ensalada", null, 1, null, null);
			Plato plato2 = new Plato(4, "pollo", "pollo", null, 2, null, null);
			Plato plato3 = new Plato(7, "tarta", "tartas", null, 3, null, null);
			
			Mockito.when(gestorDatos.obtenerPlatos("Solpor")).thenReturn(new ArrayList<>(Arrays.asList(plato1,plato2,plato3)));
			
			Assertions.assertAll(()->{
				Exception e = Assertions.assertThrows(Exception.class, ()->{
					gestorMenus.crearMenu("Solpor", 6);
				}, "No se ha lanzado una excepcion");
				Assertions.assertEquals("El numero de dia introducido no es válido",e.getMessage(), "El mensaje de la excepcion lanzada no es el esperado");
			});
			
		}
		
		@DisplayName("Crear Menu - CP58")
		@Test
		void testCaminoTresCrearMenu() {
			Plato plato1 = new Plato(1, "ensalada", "Ensalada", null, 1, null, null);
			Plato plato2 = new Plato(4, "pollo", "pollo", null, 2, null, null);
			Plato plato3 = new Plato(7, "tarta", "tartas", null, 3, null, null);
			
			Mockito.when(gestorDatos.obtenerPlatos("Solpor")).thenReturn(new ArrayList<>(Arrays.asList(plato1,plato2,plato3)));
			
			Assertions.assertAll(()->{
				Exception e = Assertions.assertThrows(Exception.class, ()->{
					gestorMenus.crearMenu("Solpor", -2);
				}, "No se ha lanzado una excepcion");
				Assertions.assertEquals("El numero de dia introducido no es válido",e.getMessage(), "El mensaje de la excepcion lanzada no es el esperado");
			});
			
		}
		
		@DisplayName("Crear Menu - CP59")
		@Test
		void testCaminoCuatroMenu() {
			Plato plato1 = new Plato(1, "ensalada", "Ensalada", null, 1, null, null);
			Plato plato2 = new Plato(4, "pollo", "pollo", null, 2, null, null);
			Plato plato3 = new Plato(7, "tarta", "tartas", null, 3, null, null);
			
			Mockito.when(gestorDatos.obtenerPlatos("Solpor")).thenReturn(new ArrayList<>(Arrays.asList(plato1,plato2,plato3)));
			//Creamos primero un menú para ese día
			try {
				gestorMenus.crearMenu("Solpor", 3);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			//ahora comprobamos que no nos deja crear otro menú para ese día
			Assertions.assertAll(()->{
				Exception e = Assertions.assertThrows(Exception.class, ()->{
					gestorMenus.crearMenu("Solpor", 3);
				}, "No se ha lanzado una excepcion");
				Assertions.assertEquals("Ya existe un menú para esa fecha, no se permiten cambios",e.getMessage(), "El mensaje de la excepcion lanzada no es el esperado");
			});
			
			
		}
		
		@DisplayName("Crear Menu - CP60")
		@Test
		void testCaminoCincoMenu() {
			
			Mockito.when(gestorDatos.obtenerPlatos("Solpor")).thenReturn(new ArrayList<>());
			
			Assertions.assertAll(()->{
				Exception e = Assertions.assertThrows(Exception.class, ()->{
					gestorMenus.crearMenu("Solpor", 3);
				}, "No se ha lanzado una excepcion");
				Assertions.assertEquals("Esa concesionaria aun no tiene platos",e.getMessage(), "El mensaje de la excepcion lanzada no es el esperado");
			});
			
		}
		
	}
	

}

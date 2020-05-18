package subsistemas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import bean.BaseEstadistica;
import bean.Factura;
import bean.Ingrediente;
import bean.Plato;

class GestionAnalisisEstadisticasImplTest {

	

	@Tag("CajaNegra")
	@DisplayName("Test de clases validas")
	@Nested
	class PruebasValidas {
		
		GestionAnalisisEstadisticasImpl analisisEstadisticas;
		Plato plato;
		
		@BeforeEach
		void setUp() throws Exception {
			analisisEstadisticas = new GestionAnalisisEstadisticasImpl();
			plato = analisisEstadisticas.obtenerPlatoMejorValorado(); 
		}
		
		@AfterEach
		void tearDown() throws Exception {
			analisisEstadisticas = null;

		}
		/**
		 * Pruebas de obtener plato mejor valorado
		 * @author Nicolas Mandianes
		 *
		 */
		@DisplayName("Obtener Plato Mejor Valorado - CP46")
		@Test
		void testObtenerPlatoMejorValorado() {
			Plato platoObjeto = new Plato(1, "ensalada", "Ensalada rica rica", null, 1, null, null);
			ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

			assertAll(
					()->{assertEquals(platoObjeto.getClass(),plato.getClass(),"Se esperaba un plato");},
					()->{assertTrue(plato.getId() > 1,"Se esperaba que el id fuera un numero natural");},
					()->{assertTrue(plato.getCategoriaPlato() > 1,"Se esperaba que la categoria del plato fuera un numero natural");},
					()->{assertEquals(ingredientes.getClass(),plato.getIngredientes(),"Se esperaba un ArrayList de ingredientes");},
					()->{assertNotNull(plato.getTipoCocina(),"Se esperaba un string");},
					()->{assertNotNull(plato.getDescripcion(),"Se esperaba un string");},
					()->{assertNotNull(plato.getConcesionaria(),"Se esperaba un string");}
			);
		}
		
	}
	
	
	@Tag("CajaBlanca")
	@DisplayName("Test de clases validas")
	@Nested
	public class CajaBlanca{
		@Mock
		private GestionDatosImpl gestorDatos;
		
		@InjectMocks
		private GestionAnalisisEstadisticasImpl gestorEst;
		
		@BeforeEach
		void setUp() throws Exception {
			//Crear la clase
			//gestorEst = new GestionAnalisisEstadisticasImpl();
			// Crear mock de la clase que crear dependencia
			gestorDatos=Mockito.mock(GestionDatosImpl.class);
			
			// Inyectamos en las clases anotadas sus clases simuladas
			MockitoAnnotations.initMocks(this);
		}
		
		@AfterEach
		void tearDown() throws Exception {
			gestorEst = null;
			gestorDatos = null;
		}
		/**
		 * Pruebas para obtener lato peor valorado
		 * @author Alba Mart�nez
		 *
		 */
		@DisplayName("Obtener plato peor valorado - Camino 1 - CP74")
		@Test
		void testCaminoUnoPeorPlato(){
			
			Mockito.when(gestorDatos.obtenerBases()).thenReturn(new ArrayList<BaseEstadistica>());
			Mockito.when(gestorDatos.obtenerFacturas()).thenReturn(new ArrayList<Factura>());
			Mockito.when(gestorDatos.obtenerPlatos()).thenReturn(new ArrayList<Plato>());
			
			assertEquals(gestorEst.obtenerPlatoPeorValorado(), null, "Se esperaba un null");
			
		}
		
		@DisplayName("Obtener plato peor valorado - Camino 2 - CP75")
		@Test
		void testCaminoDosPeorPlato(){
			GestionDatosImpl gestorDatosAux = new GestionDatosImpl();
			ArrayList<BaseEstadistica> bases = gestorDatosAux.obtenerBases();
			
			// Hacer con Mokito
			for(BaseEstadistica base : bases) {
				base.setValoraciones(new ArrayList());
			}
			
			Mockito.when(gestorDatos.obtenerBases()).thenReturn(bases);
			Mockito.when(gestorDatos.obtenerFacturas()).thenReturn(new ArrayList<Factura>());
			Mockito.when(gestorDatos.obtenerPlatos()).thenReturn(new ArrayList<Plato>());
			
			assertEquals(gestorEst.obtenerPlatoPeorValorado(), null, "Se esperaba un null");
			
		}

		@DisplayName("Obtener plato peor valorado - Camino 3 - CP76")
		@Test
		void testCaminoTresPeorPlato(){
			
			Plato platoObjeto = new Plato(1, "ensalada", "Ensalada rica rica", null, 1, null, null);
			GestionDatosImpl gestorDatosAux = new GestionDatosImpl();
			ArrayList<Plato> platos = gestorDatosAux.obtenerPlatos();
			ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
			
			Mockito.when(gestorDatos.obtenerBases()).thenReturn(new ArrayList<BaseEstadistica>());
			Mockito.when(gestorDatos.obtenerFacturas()).thenReturn(new ArrayList<Factura>());
			Mockito.when(gestorDatos.obtenerPlatos()).thenReturn(platos);
			
			Plato plato = gestorEst.obtenerPlatoPeorValorado();
			
			// Comprobar que es el primer plato
			assertAll(
					()->{assertEquals(platoObjeto.getClass(), plato.getClass(), "Se esperaba un plato");},
					()->{assertTrue(plato.getId() >= 1,"Se esperaba que el id fuera un numero natural");},
					()->{assertTrue(plato.getCategoriaPlato() >= 1,"Se esperaba que la categoria del plato fuera un numero natural");},
					()->{assertEquals(plato.getIngredientes().getClass(), ingredientes.getClass(),"Se esperaba un ArrayList de ingredientes");},
					()->{assertNotNull(plato.getTipoCocina(), "Se esperaba un string");},
					()->{assertNotNull(plato.getDescripcion(),"Se esperaba un string");},
					()->{assertNotNull(plato.getConcesionaria(),"Se esperaba un string");}
			);
			
		}
		
		@DisplayName("Obtener plato peor valorado - Camino 4 - CP77")
		@Test
		void testCaminoCuatroPeorPlato(){
			
			Plato platoObjeto = new Plato(1, "ensalada", "Ensalada rica rica", null, 1, null, null);
			GestionDatosImpl gestorDatosAux = new GestionDatosImpl();
			ArrayList<Factura> facturas = gestorDatosAux.obtenerFacturas();
			
			Mockito.when(gestorDatos.obtenerBases()).thenReturn(new ArrayList<BaseEstadistica>());
			Mockito.when(gestorDatos.obtenerFacturas()).thenReturn(facturas);
			Mockito.when(gestorDatos.obtenerPlatos()).thenReturn(new ArrayList<Plato>());
			
			assertEquals(gestorEst.obtenerPlatoPeorValorado(), null, "Se esperaba un null");
			
		}
		
		@DisplayName("Obtener plato peor valorado - Camino 5 - CP78")
		@Test
		void testCaminoCincoPeorPlato(){
			
			GestionDatosImpl gestorDatosAux = new GestionDatosImpl();
			ArrayList<Factura> facturas = gestorDatosAux.obtenerFacturas();
			ArrayList<BaseEstadistica> bases = gestorDatosAux.obtenerBases();
			

			Mockito.when(gestorDatos.obtenerBases()).thenReturn(bases);
			Mockito.when(gestorDatos.obtenerFacturas()).thenReturn(facturas);
			Mockito.when(gestorDatos.obtenerPlatos()).thenReturn(new ArrayList<Plato>());
			
			assertEquals(gestorEst.obtenerPlatoPeorValorado(), null, "Se esperaba un null");
		}
		
		@DisplayName("Obtener plato peor valorado - Camino 6 - CP79")
		@Test
		void testCaminoSeisPeorPlato(){
			
			GestionDatosImpl gestorDatosAux = new GestionDatosImpl();
			ArrayList<Factura> facturas = gestorDatosAux.obtenerFacturas();
			ArrayList<BaseEstadistica> bases = gestorDatosAux.obtenerBases();
			
			// Hacer con Mokito
			for(BaseEstadistica base : bases) {
				base.setValoraciones(new ArrayList());
			}
			
			Mockito.when(gestorDatos.obtenerBases()).thenReturn(bases);
			Mockito.when(gestorDatos.obtenerFacturas()).thenReturn(facturas);
			Mockito.when(gestorDatos.obtenerPlatos()).thenReturn(new ArrayList<Plato>());
			
			assertEquals(gestorEst.obtenerPlatoPeorValorado(), null, "Se esperaba un null");
		}
		
		@DisplayName("Obtener plato peor valorado - Camino 7 - CP80")
		@Test
		void testCaminoSietePeorPlato(){
			
			Plato platoObjeto = new Plato(1, "ensalada", "Ensalada rica rica", null, 1, null, null);
			GestionDatosImpl gestorDatosAux = new GestionDatosImpl();
			ArrayList<Factura> facturas = gestorDatosAux.obtenerFacturas();
			ArrayList<BaseEstadistica> bases = gestorDatosAux.obtenerBases();
			ArrayList<Plato> platos = gestorDatosAux.obtenerPlatos();
			ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
			

			Mockito.when(gestorDatos.obtenerBases()).thenReturn(bases);
			Mockito.when(gestorDatos.obtenerFacturas()).thenReturn(facturas);
			Mockito.when(gestorDatos.obtenerPlatos()).thenReturn(platos);
			
			Plato plato = gestorEst.obtenerPlatoPeorValorado();
			
			// Comprobar que es el peor valorado
			assertAll(
					()->{assertEquals(platoObjeto.getClass(), plato.getClass(), "Se esperaba un plato");},
					()->{assertTrue(plato.getId() >= 1,"Se esperaba que el id fuera un numero natural");},
					()->{assertTrue(plato.getCategoriaPlato() >= 1,"Se esperaba que la categoria del plato fuera un numero natural");},
					()->{assertEquals(plato.getIngredientes().getClass(), ingredientes.getClass(),"Se esperaba un ArrayList de ingredientes");},
					()->{assertNotNull(plato.getTipoCocina(), "Se esperaba un string");},
					()->{assertNotNull(plato.getDescripcion(),"Se esperaba un string");},
					()->{assertNotNull(plato.getConcesionaria(),"Se esperaba un string");}
			);
		}
		
		@DisplayName("Obtener plato peor valorado - Camino 8 - CP81")
		@Test
		void testCaminoOchoPeorPlato(){
			
			Plato platoObjeto = new Plato(1, "ensalada", "Ensalada rica rica", null, 1, null, null);
			GestionDatosImpl gestorDatosAux = new GestionDatosImpl();
			ArrayList<Factura> facturas = gestorDatosAux.obtenerFacturas();
			ArrayList<BaseEstadistica> bases = gestorDatosAux.obtenerBases();
			ArrayList<Plato> platos = gestorDatosAux.obtenerPlatos();
			ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
			
			// Hacer con Mokito
			for(BaseEstadistica base : bases) {
				base.setValoraciones(new ArrayList());
			}

			Mockito.when(gestorDatos.obtenerBases()).thenReturn(bases);
			Mockito.when(gestorDatos.obtenerFacturas()).thenReturn(facturas);
			Mockito.when(gestorDatos.obtenerPlatos()).thenReturn(platos);
			

			Plato plato = gestorEst.obtenerPlatoPeorValorado();
			
			// Comprobar que es el primer plato
			assertAll(
					()->{assertEquals(platoObjeto.getClass(), plato.getClass(), "Se esperaba un plato");},
					()->{assertTrue(plato.getId() >= 1,"Se esperaba que el id fuera un numero natural");},
					()->{assertTrue(plato.getCategoriaPlato() >= 1,"Se esperaba que la categoria del plato fuera un numero natural");},
					()->{assertEquals(plato.getIngredientes().getClass(), ingredientes.getClass(),"Se esperaba un ArrayList de ingredientes");},
					()->{assertNotNull(plato.getTipoCocina(), "Se esperaba un string");},
					()->{assertNotNull(plato.getDescripcion(),"Se esperaba un string");},
					()->{assertNotNull(plato.getConcesionaria(),"Se esperaba un string");}
			);
		}
		
		@DisplayName("Obtener plato peor valorado - Camino 9 - CP82")
		@Test
		void testCaminoNuevePeorPlato(){
			
			Plato platoObjeto = new Plato(1, "ensalada", "Ensalada rica rica", null, 1, null, null);
			GestionDatosImpl gestorDatosAux = new GestionDatosImpl();
			ArrayList<Factura> facturas = gestorDatosAux.obtenerFacturas();
			ArrayList<Plato> platos = gestorDatosAux.obtenerPlatos();
			ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

			Mockito.when(gestorDatos.obtenerBases()).thenReturn(new ArrayList<BaseEstadistica>());
			Mockito.when(gestorDatos.obtenerFacturas()).thenReturn(facturas);
			Mockito.when(gestorDatos.obtenerPlatos()).thenReturn(platos);
			
			Plato plato = gestorEst.obtenerPlatoPeorValorado();
			
			// Comprobar que es el primer plato
			assertAll(
					()->{assertEquals(platoObjeto.getClass(), plato.getClass(), "Se esperaba un plato");},
					()->{assertTrue(plato.getId() >= 1,"Se esperaba que el id fuera un numero natural");},
					()->{assertTrue(plato.getCategoriaPlato() >= 1,"Se esperaba que la categoria del plato fuera un numero natural");},
					()->{assertEquals(plato.getIngredientes().getClass(), ingredientes.getClass(),"Se esperaba un ArrayList de ingredientes");},
					()->{assertNotNull(plato.getTipoCocina(), "Se esperaba un string");},
					()->{assertNotNull(plato.getDescripcion(),"Se esperaba un string");},
					()->{assertNotNull(plato.getConcesionaria(),"Se esperaba un string");}
			);
		}
		
		@DisplayName("Obtener plato peor valorado - Camino 10 - CP83")
		@Test
		void testCaminoDiezPeorPlato(){
			
			Plato platoObjeto = new Plato(1, "ensalada", "Ensalada rica rica", null, 1, null, null);
			GestionDatosImpl gestorDatosAux = new GestionDatosImpl();
			ArrayList<BaseEstadistica> bases = gestorDatosAux.obtenerBases();
			ArrayList<Plato> platos = gestorDatosAux.obtenerPlatos();
			ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

			Mockito.when(gestorDatos.obtenerBases()).thenReturn(bases);
			Mockito.when(gestorDatos.obtenerFacturas()).thenReturn(new ArrayList<Factura>());
			Mockito.when(gestorDatos.obtenerPlatos()).thenReturn(platos);
			
			Plato plato = gestorEst.obtenerPlatoPeorValorado();
			
			// Comprobar que es el primer plato
			assertAll(
					()->{assertEquals(platoObjeto.getClass(), plato.getClass(), "Se esperaba un plato");},
					()->{assertTrue(plato.getId() >= 1,"Se esperaba que el id fuera un numero natural");},
					()->{assertTrue(plato.getCategoriaPlato() >= 1,"Se esperaba que la categoria del plato fuera un numero natural");},
					()->{assertEquals(plato.getIngredientes().getClass(), ingredientes.getClass(),"Se esperaba un ArrayList de ingredientes");},
					()->{assertNotNull(plato.getTipoCocina(), "Se esperaba un string");},
					()->{assertNotNull(plato.getDescripcion(),"Se esperaba un string");},
					()->{assertNotNull(plato.getConcesionaria(),"Se esperaba un string");}
			);
		}
		
		@DisplayName("Obtener plato mejor valorado - Camino 1 - CP84")
		@Test
		void testCaminoUnoMejorPlato(){
			
			Mockito.when(gestorDatos.obtenerBases()).thenReturn(new ArrayList<BaseEstadistica>());
			Mockito.when(gestorDatos.obtenerFacturas()).thenReturn(new ArrayList<Factura>());
			Mockito.when(gestorDatos.obtenerPlatos()).thenReturn(new ArrayList<Plato>());
			
			assertEquals(gestorEst.obtenerPlatoMejorValorado(), null, "Se esperaba un null");
			
		}
		
		@DisplayName("Obtener plato mejor valorado - Camino 2 - CP85")
		@Test
		void testCaminoDosMejorPlato(){
			GestionDatosImpl gestorDatosAux = new GestionDatosImpl();
			ArrayList<BaseEstadistica> bases = gestorDatosAux.obtenerBases();
			
			// Hacer con Mokito
			for(BaseEstadistica base : bases) {
				base.setValoraciones(new ArrayList());
			}
			
			Mockito.when(gestorDatos.obtenerBases()).thenReturn(bases);
			Mockito.when(gestorDatos.obtenerFacturas()).thenReturn(new ArrayList<Factura>());
			Mockito.when(gestorDatos.obtenerPlatos()).thenReturn(new ArrayList<Plato>());
			
			assertEquals(gestorEst.obtenerPlatoMejorValorado(), null, "Se esperaba un null");
			
		}

		@DisplayName("Obtener plato mejor valorado - Camino 3 - CP86")
		@Test
		void testCaminoTresMejorPlato(){
			
			Plato platoObjeto = new Plato(1, "ensalada", "Ensalada rica rica", null, 1, null, null);
			GestionDatosImpl gestorDatosAux = new GestionDatosImpl();
			ArrayList<Plato> platos = gestorDatosAux.obtenerPlatos();
			ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
			
			Mockito.when(gestorDatos.obtenerBases()).thenReturn(new ArrayList<BaseEstadistica>());
			Mockito.when(gestorDatos.obtenerFacturas()).thenReturn(new ArrayList<Factura>());
			Mockito.when(gestorDatos.obtenerPlatos()).thenReturn(platos);
			
			Plato plato = gestorEst.obtenerPlatoMejorValorado();
			
			// Comprobar que es el primer plato
			assertAll(
					()->{assertEquals(platoObjeto.getClass(), plato.getClass(), "Se esperaba un plato");},
					()->{assertTrue(plato.getId() >= 1,"Se esperaba que el id fuera un numero natural");},
					()->{assertTrue(plato.getCategoriaPlato() >= 1,"Se esperaba que la categoria del plato fuera un numero natural");},
					()->{assertEquals(plato.getIngredientes().getClass(), ingredientes.getClass(),"Se esperaba un ArrayList de ingredientes");},
					()->{assertNotNull(plato.getTipoCocina(), "Se esperaba un string");},
					()->{assertNotNull(plato.getDescripcion(),"Se esperaba un string");},
					()->{assertNotNull(plato.getConcesionaria(),"Se esperaba un string");}
			);
			
		}
		
		@DisplayName("Obtener plato mejor valorado - Camino 4 - CP87")
		@Test
		void testCaminoCuatroMejorPlato(){
			
			Plato platoObjeto = new Plato(1, "ensalada", "Ensalada rica rica", null, 1, null, null);
			GestionDatosImpl gestorDatosAux = new GestionDatosImpl();
			ArrayList<Factura> facturas = gestorDatosAux.obtenerFacturas();
			
			Mockito.when(gestorDatos.obtenerBases()).thenReturn(new ArrayList<BaseEstadistica>());
			Mockito.when(gestorDatos.obtenerFacturas()).thenReturn(facturas);
			Mockito.when(gestorDatos.obtenerPlatos()).thenReturn(new ArrayList<Plato>());
			
			assertEquals(gestorEst.obtenerPlatoMejorValorado(), null, "Se esperaba un null");
			
		}
		
		@DisplayName("Obtener plato mejor valorado - Camino 5 - CP88")
		@Test
		void testCaminoCincoMejorPlato(){
			
			GestionDatosImpl gestorDatosAux = new GestionDatosImpl();
			ArrayList<Factura> facturas = gestorDatosAux.obtenerFacturas();
			ArrayList<BaseEstadistica> bases = gestorDatosAux.obtenerBases();
			

			Mockito.when(gestorDatos.obtenerBases()).thenReturn(bases);
			Mockito.when(gestorDatos.obtenerFacturas()).thenReturn(facturas);
			Mockito.when(gestorDatos.obtenerPlatos()).thenReturn(new ArrayList<Plato>());
			
			assertEquals(gestorEst.obtenerPlatoMejorValorado(), null, "Se esperaba un null");
		}
		
		@DisplayName("Obtener plato mejor valorado - Camino 6 - CP89")
		@Test
		void testCaminoSeisMejorPlato(){
			
			GestionDatosImpl gestorDatosAux = new GestionDatosImpl();
			ArrayList<Factura> facturas = gestorDatosAux.obtenerFacturas();
			ArrayList<BaseEstadistica> bases = gestorDatosAux.obtenerBases();
			
			// Hacer con Mokito
			for(BaseEstadistica base : bases) {
				base.setValoraciones(new ArrayList());
			}
			
			Mockito.when(gestorDatos.obtenerBases()).thenReturn(bases);
			Mockito.when(gestorDatos.obtenerFacturas()).thenReturn(facturas);
			Mockito.when(gestorDatos.obtenerPlatos()).thenReturn(new ArrayList<Plato>());
			
			assertEquals(gestorEst.obtenerPlatoMejorValorado(), null, "Se esperaba un null");
		}
		
		@DisplayName("Obtener plato mejor valorado - Camino 7 - CP90")
		@Test
		void testCaminoSieteMejorPlato(){
			
			Plato platoObjeto = new Plato(1, "ensalada", "Ensalada rica rica", null, 1, null, null);
			GestionDatosImpl gestorDatosAux = new GestionDatosImpl();
			ArrayList<Factura> facturas = gestorDatosAux.obtenerFacturas();
			ArrayList<BaseEstadistica> bases = gestorDatosAux.obtenerBases();
			ArrayList<Plato> platos = gestorDatosAux.obtenerPlatos();
			ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
			

			Mockito.when(gestorDatos.obtenerBases()).thenReturn(bases);
			Mockito.when(gestorDatos.obtenerFacturas()).thenReturn(facturas);
			Mockito.when(gestorDatos.obtenerPlatos()).thenReturn(platos);
			
			Plato plato = gestorEst.obtenerPlatoMejorValorado();
			
			// Comprobar que es el peor valorado
			assertAll(
					()->{assertEquals(platoObjeto.getClass(), plato.getClass(), "Se esperaba un plato");},
					()->{assertTrue(plato.getId() >= 1,"Se esperaba que el id fuera un numero natural");},
					()->{assertTrue(plato.getCategoriaPlato() >= 1,"Se esperaba que la categoria del plato fuera un numero natural");},
					()->{assertEquals(plato.getIngredientes().getClass(), ingredientes.getClass(),"Se esperaba un ArrayList de ingredientes");},
					()->{assertNotNull(plato.getTipoCocina(), "Se esperaba un string");},
					()->{assertNotNull(plato.getDescripcion(),"Se esperaba un string");},
					()->{assertNotNull(plato.getConcesionaria(),"Se esperaba un string");}
			);
		}
		
		@DisplayName("Obtener plato mejor valorado - Camino 8 - CP91")
		@Test
		void testCaminoOchoMejorPlato(){
			
			Plato platoObjeto = new Plato(1, "ensalada", "Ensalada rica rica", null, 1, null, null);
			GestionDatosImpl gestorDatosAux = new GestionDatosImpl();
			ArrayList<Factura> facturas = gestorDatosAux.obtenerFacturas();
			ArrayList<BaseEstadistica> bases = gestorDatosAux.obtenerBases();
			ArrayList<Plato> platos = gestorDatosAux.obtenerPlatos();
			ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
			
			// Hacer con Mokito
			for(BaseEstadistica base : bases) {
				base.setValoraciones(new ArrayList());
			}

			Mockito.when(gestorDatos.obtenerBases()).thenReturn(bases);
			Mockito.when(gestorDatos.obtenerFacturas()).thenReturn(facturas);
			Mockito.when(gestorDatos.obtenerPlatos()).thenReturn(platos);
			

			Plato plato = gestorEst.obtenerPlatoMejorValorado();
			
			// Comprobar que es el primer plato
			assertAll(
					()->{assertEquals(platoObjeto.getClass(), plato.getClass(), "Se esperaba un plato");},
					()->{assertTrue(plato.getId() >= 1,"Se esperaba que el id fuera un numero natural");},
					()->{assertTrue(plato.getCategoriaPlato() >= 1,"Se esperaba que la categoria del plato fuera un numero natural");},
					()->{assertEquals(plato.getIngredientes().getClass(), ingredientes.getClass(),"Se esperaba un ArrayList de ingredientes");},
					()->{assertNotNull(plato.getTipoCocina(), "Se esperaba un string");},
					()->{assertNotNull(plato.getDescripcion(),"Se esperaba un string");},
					()->{assertNotNull(plato.getConcesionaria(),"Se esperaba un string");}
			);
		}
		
		@DisplayName("Obtener plato mejor valorado - Camino 9 - CP92")
		@Test
		void testCaminoNueveMejorPlato(){
			
			Plato platoObjeto = new Plato(1, "ensalada", "Ensalada rica rica", null, 1, null, null);
			GestionDatosImpl gestorDatosAux = new GestionDatosImpl();
			ArrayList<Factura> facturas = gestorDatosAux.obtenerFacturas();
			ArrayList<Plato> platos = gestorDatosAux.obtenerPlatos();
			ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

			Mockito.when(gestorDatos.obtenerBases()).thenReturn(new ArrayList<BaseEstadistica>());
			Mockito.when(gestorDatos.obtenerFacturas()).thenReturn(facturas);
			Mockito.when(gestorDatos.obtenerPlatos()).thenReturn(platos);
			
			Plato plato = gestorEst.obtenerPlatoMejorValorado();
			
			// Comprobar que es el primer plato
			assertAll(
					()->{assertEquals(platoObjeto.getClass(), plato.getClass(), "Se esperaba un plato");},
					()->{assertTrue(plato.getId() >= 1,"Se esperaba que el id fuera un numero natural");},
					()->{assertTrue(plato.getCategoriaPlato() >= 1,"Se esperaba que la categoria del plato fuera un numero natural");},
					()->{assertEquals(plato.getIngredientes().getClass(), ingredientes.getClass(),"Se esperaba un ArrayList de ingredientes");},
					()->{assertNotNull(plato.getTipoCocina(), "Se esperaba un string");},
					()->{assertNotNull(plato.getDescripcion(),"Se esperaba un string");},
					()->{assertNotNull(plato.getConcesionaria(),"Se esperaba un string");}
			);
		}
		
		@DisplayName("Obtener plato mejor valorado - Camino 10 - CP93")
		@Test
		void testCaminoDiezMejorPlato(){
			
			Plato platoObjeto = new Plato(1, "ensalada", "Ensalada rica rica", null, 1, null, null);
			GestionDatosImpl gestorDatosAux = new GestionDatosImpl();
			ArrayList<BaseEstadistica> bases = gestorDatosAux.obtenerBases();
			ArrayList<Plato> platos = gestorDatosAux.obtenerPlatos();
			ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();

			Mockito.when(gestorDatos.obtenerBases()).thenReturn(bases);
			Mockito.when(gestorDatos.obtenerFacturas()).thenReturn(new ArrayList<Factura>());
			Mockito.when(gestorDatos.obtenerPlatos()).thenReturn(platos);
			
			Plato plato = gestorEst.obtenerPlatoMejorValorado();
			
			// Comprobar que es el primer plato
			assertAll(
					()->{assertEquals(platoObjeto.getClass(), plato.getClass(), "Se esperaba un plato");},
					()->{assertTrue(plato.getId() >= 1,"Se esperaba que el id fuera un numero natural");},
					()->{assertTrue(plato.getCategoriaPlato() >= 1,"Se esperaba que la categoria del plato fuera un numero natural");},
					()->{assertEquals(plato.getIngredientes().getClass(), ingredientes.getClass(),"Se esperaba un ArrayList de ingredientes");},
					()->{assertNotNull(plato.getTipoCocina(), "Se esperaba un string");},
					()->{assertNotNull(plato.getDescripcion(),"Se esperaba un string");},
					()->{assertNotNull(plato.getConcesionaria(),"Se esperaba un string");}
			);
		}

	}
	

}

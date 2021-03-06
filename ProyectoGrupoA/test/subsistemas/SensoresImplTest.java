package subsistemas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import bean.Bandeja;

class SensoresImplTest {

	SensoresImpl sensores;
	
	@Tag("CajaNegra")
	@DisplayName("Casos de prueba V�lidos")
	@Nested
	class PruebasValidas {

		@BeforeEach
		void setUp() throws Exception {
			sensores = new SensoresImpl(new GestionPagosCalificacionesImpl());
		}

		@AfterEach
		void tearDown() throws Exception {
			sensores = null;
		}

		@DisplayName("ObtenerBandeja - CP26")
		@Test
		void obtenerBandeja() {
			assertEquals(Bandeja.class, sensores.obtenerBandeja().getClass(),"Se esperaba una clase de tipo bandeja");
			
		}
		
		@DisplayName("ObtenerVale - CP27")
		@Test
		void obtenerVale() {
			assertEquals(Integer.class, sensores.obtenerVale().getClass(),"Se esperaba una clase de tipo entero");
			
		}
		
		@DisplayName("Terminar Consumicion - CP29")
		@Test
		void terminarConsumicion() {
			assertDoesNotThrow(()->{sensores.terminarConsumicion(new Bandeja(0,new ArrayList<>(),"",new Date()));}, "Ha habido una excepcion");	
		}
	}

}

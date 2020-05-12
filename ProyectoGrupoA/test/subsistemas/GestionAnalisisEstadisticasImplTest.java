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
	

}

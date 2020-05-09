package subsistemas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bean.Plato;
import interfaces.ItfSensores;

class GestorSeleccionMenuImplTest {
	GestionPagosCalificacionesImpl gp;
	ItfSensores s;
	GestorSeleccionMenuImpl smenu;
	GestionMenus gestorMenus;
	GestionDatosImpl gestorDatos;
	@AfterEach
	void tearDown() throws Exception {
		gestorDatos = null;
		gestorMenus = null;
		smenu = null;
		s = null;
	}

	@BeforeEach
	void setUp() throws Exception {
		gp = new GestionPagosCalificacionesImpl();
		s = new SensoresImpl(gp);
		gp.setSensores(s);
		gestorDatos = new GestionDatosImpl();
		gestorMenus = new GestionMenus(gestorDatos);
		smenu = new GestorSeleccionMenuImpl(gestorMenus, s);
		//Primero creamos el menú para luego en la prueba poder seleccionarlo sin problema
		gestorMenus.crearMenu("Albor",1);
		gestorMenus.escogerPlatos(1, 1, 1);
		gestorMenus.escogerPlatos(4, 1, 2);
		gestorMenus.escogerPlatos(7, 1, 3);
	}

	@DisplayName("Seleccionar menú - CP9")
	@Test
	void testSeleccionMenu() {
		Plato plato1 = new Plato(1, "ensalada", "Ensalada", null, 1, null, null);
		Plato plato2 = new Plato(4, "pollO", "pollo", null, 2, null, null);
		Plato plato3 = new Plato(7, "tarta", "tarta", null, 3, null, null);
		String bebida = new String("agua");
		
		assertDoesNotThrow(()->{
			smenu.seleccionarMenu(plato1, plato2, plato3, bebida);
		}, "Ha habido una excepción");
	}

}

package subsistemas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import bean.Factura;

class GestionDatosImplTest {

	Factura factura;
	GestionDatosImpl gestor;
	int vale=0;
	int idFactura=0;

	@DisplayName("Test de clases válidas")
	@Nested
	class PruebasVálidas {
		@BeforeEach
		void setUp() throws Exception {

			factura = new Factura(vale, idFactura, 0, 0, 0, "agua");
			gestor = new GestionDatosImpl();
			vale++;
			idFactura++;
		}

		@AfterEach
		void tearDown() throws Exception {
			factura = null;
			gestor = null;

		}

		@DisplayName("Almacenar Factura - G0")
		@Test
		void test() {
			Assertions.assertDoesNotThrow(() -> {
				gestor.almacenarFactura(factura);
			}, "Ha habido una excepcion");
		}
	}

	@DisplayName("Test de clases no válidas")
	@Nested
	class PruebasNoVálidas{
		
		@BeforeEach
		void setUp() throws Exception {
			factura = new Factura(1, 1, 0, 0, 0, "agua");
			gestor = new GestionDatosImpl();
			
		}

		@AfterEach
		void tearDown() throws Exception {
			factura = null;
			gestor = null;

		}

		@DisplayName("Almacenar Factura - G1")
		@Test
		void almacenarFacturaIdValeIncorrecto() {
			factura.setVale(-1);
			factura.setBandeja(1);
			Assertions.assertThrows(Exception.class,() -> {
				gestor.almacenarFactura(factura);
			},"Se esperaba excepción por vale menor que 0" );
		}
		
		@DisplayName("Almacenar Factura - G2")
		@Test
		void almacenarFacturaBandejaNull() {
			factura.setVale(1);
			factura.setBandeja(null);
			Assertions.assertThrows(Exception.class,() -> {
				gestor.almacenarFactura(factura);
			},"Se esperaba excepción por bandeja = null" );
		}
		
		@DisplayName("Almacenar Factura - G3")
		@Test
		void almacenarFacturaIdBandejaIncorreecto() {
			factura.setVale(2);
			factura.setBandeja(-1);
			Assertions.assertThrows(Exception.class,() -> {
				gestor.almacenarFactura(factura);
			},"Se esperaba excepción por idBandeja menor que 0" );
		}
		
		@DisplayName("Almacenar Factura - G4")
		@Test
		void almacenarFacturaIdPrimerPlatoIncorrecto() {
			factura.setPlato1(-1);
			factura.setVale(3);
			factura.setBandeja(3);
			Assertions.assertThrows(Exception.class,() -> {
				gestor.almacenarFactura(factura);
			},"Se esperaba excepción por idPlato1 menor que 0" );
		}
		
		@DisplayName("Almacenar Factura - G5")
		@Test
		void almacenarFacturaIdSegundoPlatoIncorrecto() {
			factura.setPlato2(-1);
			factura.setVale(4);
			factura.setBandeja(4);
			Assertions.assertThrows(Exception.class,() -> {
				gestor.almacenarFactura(factura);
			},"Se esperaba excepción por idPlato2 menor que 0" );
		}
		
		@DisplayName("Almacenar Factura - G6")
		@Test
		void almacenarFacturaIdPostrePlatoIncorrecto() {
			factura.setPostre(-1);
			factura.setVale(5);
			factura.setBandeja(5);
			Assertions.assertThrows(Exception.class,() -> {
				gestor.almacenarFactura(factura);
			},"Se esperaba excepción por idPostre menor que 0" );
		}
		
		@DisplayName("Almacenar Factura - G7")
		@Test
		void almacenarFacturaBebidaNull() {
			factura.setBebida(null);
			factura.setVale(6);
			factura.setBandeja(6);
			Assertions.assertThrows(Exception.class,() -> {
				gestor.almacenarFactura(factura);
			},"Se esperaba excepción por bebida=null" );
		}
		
		
	}
}

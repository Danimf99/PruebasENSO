package subsistemas;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bean.Factura;


class TestAlmacenarFactura {
	

	GestionDatosImpl gestion;
	Factura factura;

	@BeforeEach
	void setUp() throws Exception {
		gestion = new GestionDatosImpl();
		factura = new Factura(1, 1, 1, 1, 1, "afsd");
	}
	
	@Test
	void testAlmacenarFactura() {		
		
		assertThrows(Exception.class,()->{gestion.almacenarFactura(factura);},"sdf");
	}

}

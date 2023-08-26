package ct.tienda_libros;

import ct.tienda_libros.vista.LibroForm;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.*;

@SpringBootApplication
public class TiendaLibrosApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext contextpSpring =
				new SpringApplicationBuilder(TiendaLibrosApplication.class)
						.headless(false)
						.web(WebApplicationType.NONE)
						.run(args);
		// Ejecutamos el codigo para cargar el formulario
		EventQueue.invokeLater(()->{
			//obtenemos el objeto form a traver de Spring
			LibroForm libroForm = contextpSpring.getBean(LibroForm.class);
			libroForm.setVisible(true);

		});

	}

}

package cifo.cursjava.pla7;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import cifo.cursjava.pla7.entidades.Alumnos;
import cifo.cursjava.pla7.entidades.Modulos;
import cifo.cursjava.pla7.entidades.Profesores;

public class Main {

	public static void main(String[] args) {
		// Crear la configuración cogíendola del xml y añadiendo la clase Categorias
		Configuration configuration = new Configuration()
			.configure("hibernate.cfg.xml")
			.addAnnotatedClass(Modulos.class)
			.addAnnotatedClass(Profesores.class)
			.addAnnotatedClass(Alumnos.class);
						
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
			.applySettings(configuration.getProperties());
						
		// Crear la factoría de sesiones
		SessionFactory factory = configuration.buildSessionFactory(builder.build());
						
		// Crear la sesión
		Session session = factory.getCurrentSession();
		try {
			// Iniciar transacción
			session.beginTransaction();
			
			Profesores prof1 = new Profesores("Profesor1", "11111111A", "prof1@email.com");
			Profesores prof2 = new Profesores("Profesor2", "22222222B", "prof2@mail.com");
			
			Modulos mod1 = new Modulos("Spring", prof1);
			Modulos mod2 = new Modulos("VueJS", prof2);
			
			Alumnos alu1 = new Alumnos("Alumno1", "alu1@mail.com");
			Alumnos alu2 = new Alumnos("Alumno2", "alu2@mail.com");
			Alumnos alu3 = new Alumnos("Alumno3", "alu3@mail.com");
			
			alu1.addModulo(mod1);
			alu2.addModulo(mod1);
			alu3.addModulo(mod2);
			
			session.save(prof1);
			session.save(prof2);
			session.save(mod1);
			session.save(mod2);
			session.save(alu1);
			session.save(alu2);
			session.save(alu3);
			
			session.getTransaction().commit();
			session.close();
		} finally {
			factory.close();
		}
	}
}

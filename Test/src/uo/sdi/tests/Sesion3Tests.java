package uo.sdi.tests;

import net.sourceforge.jwebunit.junit.WebTester;

import org.junit.*;

public class Sesion3Tests {

	private WebTester admin;
    private WebTester john;


	@Before
    public void prepare() {
    	john=new WebTester();
    	admin=new WebTester();
    	john.setBaseUrl("http://localhost:8280/sesion3.MVCCasero");
    	admin.setBaseUrl("http://localhost:8280/sesion3.MVCCasero");
    	
    }

    @Test
    public void testListarCategorias() {
    	john.beginAt("/");  // Navegar a la URL
    	john.assertLinkPresent("listarCategorias_link_id");  // Comprobar que existe el hipervínculo
    	john.clickLink("listarCategorias_link_id"); // Seguir el hipervínculo

    	john.assertTitleEquals("TaskManager - Listado de categorías");  // Comprobar título de la página

        // La base de datos contiene 7 categorías tal y como se entrega
        int i=0;
        for (i=0;i<7;i++)
        	john.assertElementPresent("item_"+i); // Comprobar elementos presentes en la página
        john.assertElementNotPresent("item_"+i);
    }

    @Test
    public void testIniciarSesionConExito() {
    	john.beginAt("/");  // Navegar a la URL
    	john.assertFormPresent("validarse_form_name");  // Comprobar formulario está presente
    	john.setTextField("nombreUsuario", "john"); // Rellenar primer campo de formulario
    	john.submit(); // Enviar formulario
    	john.assertTitleEquals("TaskManager - Página principal del usuario");  // Comprobar título de la página
    	john.assertTextInElement("login", "john");  // Comprobar cierto elemento contiene cierto texto
    	john.assertTextInElement("id", "2");  // Comprobar cierto elemento contiene cierto texto
    	john.assertTextPresent("Iniciaste sesión el"); // Comprobar cierto texto está presente
    }

    
    @Test
    public void testIniciarSesionSinExito() {
    	WebTester browser=new WebTester();
    	browser.setBaseUrl("http://localhost:8280/sesion3.MVCCasero");        
    	browser.beginAt("/");  // Navegar a la URL
    	browser.setTextField("nombreUsuario", "yoNoExisto"); // Rellenar primer campo de formulario
    	browser.submit(); // Enviar formulario
    	browser.assertTitleEquals("TaskManager - Inicie sesión");  // Comprobar título de la página
    }
    
    @Test
    public void testRegistro() {
    	WebTester browser=new WebTester();
    	

    	browser.beginAt("/");
    	browser.assertLinkPresent("registrar_link_id");  
    	browser.clickLink("registrar_link_id"); 
    	
    	browser.assertTitleEquals("Registro");
    	
    	browser.assertTextPresent("Login:");
    	browser.assertTextPresent("Email:"); 
    	browser.assertTextPresent("Contraseña:"); 
    	browser.assertTextPresent("Repetir Contraseña:"); 
    	
    	browser.setTextField("login", "browser");
    	browser.setTextField("email", "browser@gmail.com");
    	browser.setTextField("pass", "browser123");
    	browser.setTextField("rePass", "browser123");
    	browser.clickButton("registrar_button_id");
    	
    	

    }
    
	@Test
	public void testRegistroFailPassword() {
		
    	WebTester browser=new WebTester();
    	
		browser.beginAt("/"); 
		browser.clickLink("registrar_link_id"); 
		
		browser.assertTitleEquals("Registro");

		browser.setTextField("login", "browser");
		browser.setTextField("email", "browser@gmail.com");
		browser.setTextField("pass", "browser124");
		browser.setTextField("rePass", "browser123");
		browser.clickButton("registrar_button_id"); 
																		
																	
		browser.assertTextPresent("Error");
	}
	
	
	@Test
	public void adminListaUsuariosTest(){
		admin.beginAt("/"); 
		
		admin.setTextField("nombreUsuario", "admin");
		admin.setTextField("passUsuario", "admin123");
		
		admin.clickButton("validar_button_id"); 
		admin.clickLink("listarUsuarios_link_id");
		
		admin.assertLinkPresent("login_link_id");
		admin.assertLinkPresent("email_link_id");
		admin.assertLinkPresent("status_link_id");
		
		admin.clickLink("login_link_id");
		admin.clickLink("email_link_id");
		admin.clickLink("status_link_id");
		
		admin.clickLink("paginaAnterior_link_id");
		admin.clickLink("cerrarSesion_link_id"); 
	}

}
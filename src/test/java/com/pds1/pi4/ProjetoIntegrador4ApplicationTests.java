package com.pds1.pi4;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjetoIntegrador4ApplicationTests {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void testaCodigoRetorno() throws Exception{
		this.mvc.perform(get("http://localhost:8080/usuarios/1")).andExpect(status().isOk());
	}
	
	@Test
	public void testaMessagemRetorno() throws Exception{
		this.mvc.perform(get("http://localhost:8080/usuarios/1"))
		.andExpect(jsonPath("$.id", is(1)))
		.andExpect(jsonPath("$.nome", is("Pedro Adm")))
		.andExpect(jsonPath("$.email", is("pedro@gmail.com")))
		;
	}

	

}

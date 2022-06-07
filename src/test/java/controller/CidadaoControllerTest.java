package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import model.Cidadao;
import model.Logradouro;

public class CidadaoControllerTest {

	@Mock
	private CidadaoController cidadaoController;
	
	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test() {
		List<Cidadao> cidadaos = mockCidadoes();
		
		Mockito.when(cidadaoController.buscarSomenteOsDaRegiaoSudeste()).thenReturn(cidadaos);
		
		assertEquals(cidadaos, cidadaos);
	}

	private List<Cidadao> mockCidadoes() {
		List<Cidadao> cidadaos = new ArrayList<>();
		
		cidadaos.add(new Cidadao("Antonio Carlos", 43, new Logradouro("São Tomé das Letras", "MG")));
		cidadaos.add(new Cidadao("Cláudia Pereira", 20, new Logradouro("Aparecida", "SP")));
		cidadaos.add(new Cidadao("Leonardo Souza", 30, new Logradouro("Araraquara", "SP")));
		cidadaos.add(new Cidadao("Miguel Pereira", 33, new Logradouro("Parati", "RJ")));
		
		return cidadaos;
	}

}

package managers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Cidadao;
import model.Logradouro;

public class LeitorDeArquivoTest {

	String caminho;
	
	@Before
	public void init() {
		caminho = "src/main/resources/cidadaos.txt";
	}
	
	@Test
	public void quandoArquivoEstiverVazioEhEsperadoQueListaRetorneVazia() {
		LeitorDeArquivo leitorDeArquivo = new LeitorDeArquivo(caminho);
		
		String conteudoDoArquivo = "";
		Reader sr = new StringReader(conteudoDoArquivo);
		BufferedReader br = new BufferedReader(sr);
		
		List<Cidadao> esperado = new ArrayList<>();
		
		List<Cidadao> cidadaos = leitorDeArquivo.leArquivo(br);
		
		assertEquals(cidadaos, esperado);
	}
	
	@Test
	public void quandoExistirUmaLinhaNoArquivoEhEsperadoQueListaRetorneUmObjeto() {
		LeitorDeArquivo leitorDeArquivo = new LeitorDeArquivo(caminho);
		
		String conteudoDoArquivo = "Nome            | Idade | Munic�pio onde mora | Estado" +
				System.lineSeparator() +
				"Antonio Carlos  | 43    | S�o Tom� das Letras | MG";
		Reader sr = new StringReader(conteudoDoArquivo);
		BufferedReader br = new BufferedReader(sr);
		
		List<Cidadao> esperado = new ArrayList<>();
		esperado.add(new Cidadao("Antonio Carlos", 43, new Logradouro("S�o Tom� das Letras", "MG")));
		
		List<Cidadao> cidadaos = leitorDeArquivo.leArquivo(br);
		
		assertEquals(cidadaos, esperado);
	}
	
	@Test
	public void quandoExistir3LinhasNoArquivoEhEsperadoQueListaRetorne3Objetos() {
		LeitorDeArquivo leitorDeArquivo = new LeitorDeArquivo(caminho);
		
		String conteudoDoArquivo = "Nome            | Idade | Munic�pio onde mora | Estado" +
				System.lineSeparator() +
				"Antonio Carlos  | 43    | S�o Tom� das Letras | MG" +
				System.lineSeparator() +
				"Cl�udia Pereira | 20    | Aparecida           | SP" +
				System.lineSeparator() +
				"Jos� dos Santos | 30    | Manaus              | AM";
		Reader sr = new StringReader(conteudoDoArquivo);
		BufferedReader br = new BufferedReader(sr);
		
		List<Cidadao> esperado = new ArrayList<>();
		esperado.add(new Cidadao("Antonio Carlos", 43, new Logradouro("S�o Tom� das Letras", "MG")));
		esperado.add(new Cidadao("Cl�udia Pereira", 20, new Logradouro("Aparecida", "SP")));
		esperado.add(new Cidadao("Jos� dos Santos", 30, new Logradouro("Manaus", "AM")));
		
		List<Cidadao> cidadaos = leitorDeArquivo.leArquivo(br);
		
		assertEquals(cidadaos, esperado);
	}
	
	@Test
	public void quandoArquivoNaoExistirDeveLancarExcecao() {
		LeitorDeArquivo leitorDeArquivo = new LeitorDeArquivo("src/main/resources/cidadaos.txtttttt");
		
		assertThrows(FileNotFoundException.class, () -> leitorDeArquivo.abreArquivo());
	}

}

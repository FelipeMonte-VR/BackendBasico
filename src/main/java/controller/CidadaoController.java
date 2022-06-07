package controller;

import java.sql.Connection;
import java.util.List;

import dao.CidadaoDAO;
import factory.ConnectionFactory;
import model.Cidadao;

public class CidadaoController {
	private CidadaoDAO cidadaoDAO;

	public CidadaoController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.cidadaoDAO = new CidadaoDAO(connection);
	}

	public void create(Cidadao cidadao) {
		this.cidadaoDAO.create(cidadao);
	}

	public List<Cidadao> buscarSomenteOsDaRegiaoSudeste() {
		return this.cidadaoDAO.buscarSomenteOsDaRegiaoSudeste();
	}
	
	public List<Cidadao> buscarSomenteOsMaioresDe30() {
		return this.cidadaoDAO.buscarSomenteOsMaioresDe30();
	}
}

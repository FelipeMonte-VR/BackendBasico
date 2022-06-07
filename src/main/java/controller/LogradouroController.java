package controller;

import java.sql.Connection;
import dao.LogradouroDAO;
import factory.ConnectionFactory;
import model.Logradouro;

public class LogradouroController {
	private LogradouroDAO logradouroDAO;

	public LogradouroController() {
		Connection connection = new ConnectionFactory().recuperarConexao();
		this.logradouroDAO = new LogradouroDAO(connection);
	}

	public void create(Logradouro logradouro) {
		this.logradouroDAO.create(logradouro);
	}
}

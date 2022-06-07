package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import controller.LogradouroController;
import model.Cidadao;
import model.Logradouro;

public class CidadaoDAO {
	private Connection connection;

	public CidadaoDAO(Connection connection) {
		this.connection = connection;
	}

	public void create(Cidadao cidadao) {
		try {
			connection.setAutoCommit(false);
			
			LogradouroController logradouroController = new LogradouroController();
			logradouroController.create(cidadao.getLogradouro());
			
			String sql = "INSERT INTO pessoas (nome, idade, logradouros_id_logradouros) VALUES (?, ?, ?);";
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, cidadao.getNome());
				pstm.setInt(2, cidadao.getIdade());
				pstm.setInt(3, cidadao.getLogradouro().getIdLogradouro());
				pstm.execute();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
//	public void create(Cidadao cidadao) {
//		try {
//			String sql = "INSERT INTO pessoas (nome, idade, logradouros_id_logradouros) VALUES (?, ?, ?);";
//			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//				pstm.setString(1, cidadao.getNome());
//				pstm.setInt(2, cidadao.getIdade());
//				pstm.setInt(3, cidadao.getLogradouro().getIdLogradouro());
//				pstm.execute();
//			}
//		} catch (SQLException e) {
//			throw new RuntimeException(e);
//		}
//	}
	
	public List<Cidadao> buscarSomenteOsDaRegiaoSudeste() {
		List<Cidadao> cidadaos = new ArrayList<>();
		
		try {
			String sql = "SELECT nome, idade, municipio, estado FROM pessoas INNER JOIN logradouros ON id_logradouros=logradouros_id_logradouros WHERE estado='MG' OR estado='ES' or estado='SP' or estado='RJ';";
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.execute();
				
				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						Logradouro l = new Logradouro(rst.getString(3), rst.getString(4));
						Cidadao c = new Cidadao(rst.getString(1), rst.getInt(2), l);
						cidadaos.add(c);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return cidadaos;
	}
	
	public List<Cidadao> buscarSomenteOsMaioresDe30() {
		List<Cidadao> cidadaos = new ArrayList<>();
		
		try {
			String sql = "SELECT nome, idade, municipio, estado FROM pessoas INNER JOIN logradouros ON id_logradouros=logradouros_id_logradouros;";
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.execute();
				
				try (ResultSet rst = pstm.getResultSet()) {
					while (rst.next()) {
						Logradouro l = new Logradouro(rst.getString(3), rst.getString(4));
						Cidadao c = new Cidadao(rst.getString(1), rst.getInt(2), l);
						cidadaos.add(c);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return cidadaos.stream().filter(c -> c.getIdade() >= 30).collect(Collectors.toList());
	}
}

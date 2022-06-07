package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Logradouro;


public class LogradouroDAO {
	private Connection connection;

	public LogradouroDAO(Connection connection) {
		this.connection = connection;
	}

	public void create(Logradouro logradouro) {
		read(logradouro);
		
		if (logradouro.getIdLogradouro() == -1) {
			try {
				String sql = "INSERT INTO logradouros (municipio, estado) VALUES (?, ?);";
				try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
					pstm.setString(1, logradouro.getMunicipio());
					pstm.setString(2, logradouro.getEstado());
					pstm.execute();
					
					read(logradouro);
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	public void read(Logradouro logradouro) {
		try {
			String sql = "SELECT id_logradouros, municipio, estado FROM logradouros WHERE municipio=? and estado=?;";
			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				pstm.setString(1, logradouro.getMunicipio());
				pstm.setString(2, logradouro.getEstado());
				pstm.execute();
				
				try (ResultSet rst = pstm.getResultSet()) {
					if (rst.next()) {
						int idLogradouros = rst.getInt(1);
						logradouro.setIdLogradouro(idLogradouros);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}

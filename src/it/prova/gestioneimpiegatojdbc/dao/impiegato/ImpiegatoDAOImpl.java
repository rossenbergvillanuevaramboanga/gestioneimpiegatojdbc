package it.prova.gestioneimpiegatojdbc.dao.impiegato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.prova.gestioneimpiegatojdbc.dao.AbstractMySQLDAO;
import it.prova.gestioneimpiegatojdbc.model.Compagnia;
import it.prova.gestioneimpiegatojdbc.model.Impiegato;

public class ImpiegatoDAOImpl extends AbstractMySQLDAO implements ImpiegatoDAO {

	public ImpiegatoDAOImpl(Connection connection) {
		super(connection);
	}

	@Override
	public List<Impiegato> list() throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		List<Impiegato> result = new ArrayList<Impiegato>();
		Impiegato impiegatoTemp = null;
		Compagnia compagniaTemp = null;

		try (Statement ps = connection.createStatement();
				ResultSet rs = ps.executeQuery(
						"select * from impiegato i inner join compagnia c on i.compagnia_id = c.id")) {

			while (rs.next()) {
				impiegatoTemp = new Impiegato();
				impiegatoTemp.setNome(rs.getString("nome"));
				impiegatoTemp.setCognome(rs.getString("cognome"));
				impiegatoTemp.setCodiceFiscale(rs.getString("codicefiscale"));
				impiegatoTemp.setDataNascita(rs.getDate("datanascita"));
				impiegatoTemp.setDataAssunzione(rs.getDate("dataassunzione"));
				impiegatoTemp.setId(rs.getLong("i.id"));

				compagniaTemp = new Compagnia();
				compagniaTemp.setRagioneSociale(rs.getString("ragionesociale"));
				compagniaTemp.setFatturatoAnnuo(rs.getInt("fatturatoannuo"));
				compagniaTemp.setDataFondazione(rs.getDate("datafondazione"));
				compagniaTemp.setId(rs.getLong("c.id"));

				impiegatoTemp.setCompagnia(compagniaTemp);
				result.add(impiegatoTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		
		}
		return result;
	}

	@Override
	public Impiegato get(Long idInput) throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Impiegato impiegatoTemp = null;

		try (PreparedStatement ps = connection.prepareStatement("select * from impiegato where id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					impiegatoTemp = new Impiegato();
					impiegatoTemp.setNome(rs.getString("nome"));
					impiegatoTemp.setCognome(rs.getString("cognome"));
					impiegatoTemp.setCodiceFiscale(rs.getString("codicefiscale"));
					impiegatoTemp.setDataNascita(rs.getDate("datanascita"));
					impiegatoTemp.setDataAssunzione(rs.getDate("dataassunzione"));
					impiegatoTemp.setId(rs.getLong("id"));

				} else {
					impiegatoTemp = null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			
		}

		return impiegatoTemp;

	}

	@Override
	public int update(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;

		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE impiegato SET nome=?, cognome=?, codicefiscale=?, datanascita=?, dataassunzione=?, compagnia_id=? where id=?;")) {
			ps.setString(1, input.getNome());
			ps.setString(2, input.getCognome());
			ps.setString(3, input.getCodiceFiscale());
			ps.setDate(4, new java.sql.Date(input.getDataNascita().getTime()));
			ps.setDate(5, new java.sql.Date(input.getDataAssunzione().getTime()));
			ps.setLong(6, input.getCompagnia().getId());
			ps.setLong(7, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		return result;
	}

	@Override
	public int insert(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;

		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO impiegato (nome, cognome, codicefiscale, datanascita, dataassunzione, compagnia_id) VALUES (?,?,?,?,?,?);")) {
			ps.setString(1, input.getNome());
			ps.setString(2, input.getCognome());
			ps.setString(3, input.getCodiceFiscale());
			ps.setDate(4, new java.sql.Date(input.getDataNascita().getTime()));
			ps.setDate(5, new java.sql.Date(input.getDataAssunzione().getTime()));
			ps.setLong(6, input.getCompagnia().getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return result;
	}

	@Override
	public int delete(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;

		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM impiegato where id = ?;")) {
			ps.setLong(1, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return result;
	}

	@Override
	public List<Impiegato> findByExample(Impiegato input) throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		List<Impiegato> result = new ArrayList<Impiegato>();
		Impiegato impiegatoTemp = null;
		
		String query = "SELECT * FROM impiegato where 1=1";
		
		if (input == null) throw new Exception("Input invalido."); 
		if (input.getNome() != null && input.getNome().isEmpty())
			query += " and nome LIKE '" + input.getNome() + "%' ";
		if (input.getCognome() != null && input.getCognome().isEmpty())
			query += " and cognome LIKE '" + input.getCognome() + "%' ";
		if (input.getCodiceFiscale() != null && input.getCodiceFiscale().isEmpty())
			query += " and codicefiscale LIKE '" + input.getCodiceFiscale() + "%' ";
		if (input.getDataNascita() != null)
			query += " and datadinascita >= '" + new java.sql.Date(input.getDataNascita().getTime()) + "' ";
		if (input.getDataAssunzione() != null)
			query += " and dataassunzione >= '" + new java.sql.Date(input.getDataAssunzione().getTime()) + "' ";
		query += ";";
		
		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery(query)) {

			while (rs.next()) {
				impiegatoTemp = new Impiegato();
				impiegatoTemp.setNome(rs.getString("nome"));
				impiegatoTemp.setCognome(rs.getString("cognome"));
				impiegatoTemp.setCodiceFiscale(rs.getString("codicefiscale"));
				impiegatoTemp.setDataNascita(rs.getDate("datanascita"));
				impiegatoTemp.setDataAssunzione(rs.getDate("dataassunzione"));
				impiegatoTemp.setId(rs.getLong("id"));
				result.add(impiegatoTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
		return result;
	}

	@Override
	public List<Impiegato> findAllByCompagnia(Compagnia compagnia) throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (compagnia == null || compagnia.getId() == null || compagnia.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		List<Impiegato> result = new ArrayList<Impiegato>();
		Impiegato impiegatoTemp = null;
		Compagnia compagniaTemp = null;

		try (PreparedStatement ps = connection.prepareStatement("select * from impiegato i inner join compagnia c on i.compagnia_id = c.id where compagnia_id = ?")) {
			ps.setLong(1, compagnia.getId());

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					impiegatoTemp = new Impiegato();
					impiegatoTemp.setNome(rs.getString("nome"));
					impiegatoTemp.setCognome(rs.getString("cognome"));
					impiegatoTemp.setCodiceFiscale(rs.getString("codicefiscale"));
					impiegatoTemp.setDataNascita(rs.getDate("datanascita"));
					impiegatoTemp.setDataAssunzione(rs.getDate("dataassunzione"));
					impiegatoTemp.setId(rs.getLong("i.id"));

					compagniaTemp = new Compagnia();
					compagniaTemp.setRagioneSociale(rs.getString("ragionesociale"));
					compagniaTemp.setFatturatoAnnuo(rs.getInt("fatturatoannuo"));
					compagniaTemp.setDataFondazione(rs.getDate("datafondazione"));
					compagniaTemp.setId(rs.getLong("c.id"));

					impiegatoTemp.setCompagnia(compagniaTemp);
					result.add(impiegatoTemp);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			
		}

		return result;
	}

	@Override
	public int countByDataFondazioneCompagniaGreaterThan(Date data) throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (data == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;

		try (PreparedStatement ps = connection.prepareStatement("select count(*) as result from compagnia"
				+ " where datafondazione > ?;")) {
			
			ps.setDate(1, new java.sql.Date(data.getTime()));

			try (ResultSet rs = ps.executeQuery()) {

				if (rs.next()) {
					result = rs.getInt("result");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			
		}

		return result;

	}

	@Override
	public List<Impiegato> findAllErroriAssunzione() throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		List<Impiegato> result = new ArrayList<Impiegato>();
		Impiegato impiegatoTemp = null;
		Compagnia compagniaTemp = null;

		try (Statement s = connection.createStatement();
				ResultSet rs = s.executeQuery(
						"select * from impiegato i inner join compagnia c on i.compagnia_id = c.id where dataassunzione < datafondazione")) {

			while (rs.next()) {
				impiegatoTemp = new Impiegato();
				impiegatoTemp.setNome(rs.getString("nome"));
				impiegatoTemp.setCognome(rs.getString("cognome"));
				impiegatoTemp.setCodiceFiscale(rs.getString("codicefiscale"));
				impiegatoTemp.setDataNascita(rs.getDate("datanascita"));
				impiegatoTemp.setDataAssunzione(rs.getDate("dataassunzione"));
				impiegatoTemp.setId(rs.getLong("i.id"));

				compagniaTemp = new Compagnia();
				compagniaTemp.setRagioneSociale(rs.getString("ragionesociale"));
				compagniaTemp.setFatturatoAnnuo(rs.getInt("fatturatoannuo"));
				compagniaTemp.setDataFondazione(rs.getDate("datafondazione"));
				compagniaTemp.setId(rs.getLong("c.id"));

				impiegatoTemp.setCompagnia(compagniaTemp);
				result.add(impiegatoTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		
		}

		return result;
	}

}

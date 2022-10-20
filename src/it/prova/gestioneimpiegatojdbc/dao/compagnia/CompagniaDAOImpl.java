package it.prova.gestioneimpiegatojdbc.dao.compagnia;

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

public class CompagniaDAOImpl extends AbstractMySQLDAO implements CompagniaDAO {

	public CompagniaDAOImpl(Connection connection) {
		super(connection);
	}

	@Override
	public List<Compagnia> list() throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		List<Compagnia> result = new ArrayList<Compagnia>();
		Compagnia compagniaTemp = null;

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from compagnia")) {

			while (rs.next()) {
				compagniaTemp = new Compagnia();
				compagniaTemp.setRagioneSociale(rs.getString("ragionesociale"));
				compagniaTemp.setFatturatoAnnuo(rs.getInt("fatturatoannuo"));
				compagniaTemp.setDataFondazione(rs.getDate("datafondazione"));
				compagniaTemp.setId(rs.getLong("id"));
				result.add(compagniaTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return result;
	}

	@Override
	public Compagnia get(Long idInput) throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Compagnia compagniaTemp = null;

		try (PreparedStatement ps = connection.prepareStatement("select * from compagnia where id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					compagniaTemp = new Compagnia();
					compagniaTemp.setRagioneSociale(rs.getString("ragionesociale"));
					compagniaTemp.setFatturatoAnnuo(rs.getInt("fatturatoannuo"));
					compagniaTemp.setDataFondazione(rs.getDate("datafondazione"));
					compagniaTemp.setId(rs.getLong("id"));
				} else {
					compagniaTemp = null;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return compagniaTemp;
	}

	@Override
	public int update(Compagnia input) throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;

		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE compagnia SET ragionesociale=?, fatturatoannuo=?, datafondazione=? where id=?;")) {
			ps.setString(1, input.getRagioneSociale());
			ps.setInt(2, input.getFatturatoAnnuo());
			ps.setDate(3, new java.sql.Date(input.getDataFondazione().getTime()));
			ps.setLong(4, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Compagnia input) throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;

		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO compagnia (ragionesociale, fatturatoannuo, datafondazione) VALUES (?,?,?);")) {
			ps.setString(1, input.getRagioneSociale());
			ps.setInt(2, input.getFatturatoAnnuo());
			ps.setDate(3, new java.sql.Date(input.getDataFondazione().getTime()));
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
	
		}
		return result;
	}

	@Override
	public int delete(Compagnia input) throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;

		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM compagnia where id = ?;")) {
			ps.setLong(1, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		
		}
		return result;
	}

	@Override
	public List<Compagnia> findByExample(Compagnia input) throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");
		
		List<Compagnia> result = new ArrayList<Compagnia>();
		Compagnia compagniaTemp = null;
		String query = "SELECT * FROM compagnia where 1=1";
		
		if (input == null) throw new Exception("Input invalido."); 
		if (input.getRagioneSociale() != null && input.getRagioneSociale().isEmpty())
			query += " and ragionesociale LIKE '" + input.getRagioneSociale() + "%' ";
		if (input.getFatturatoAnnuo() != null)
			query += " and fatturato annuo = '" + input.getFatturatoAnnuo() + "%' ";
		if (input.getDataFondazione() != null)
			query += " and datafondazione > '" + new java.sql.Date(input.getDataFondazione().getTime()) + "' ";
		query += ";";
		
		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery(query)) {

			while (rs.next()) {
				compagniaTemp = new Compagnia();
				compagniaTemp.setRagioneSociale(rs.getString("ragionesociale"));
				compagniaTemp.setFatturatoAnnuo(rs.getInt("fatturatoannuo"));
				compagniaTemp.setDataFondazione(rs.getDate("datafondazione"));
				compagniaTemp.setId(rs.getLong("id"));
				result.add(compagniaTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return result;
		
	}

	@Override
	public List<Impiegato> findAllByDataAssunzioneMaggioreDi(Date dataAssunzione) throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (dataAssunzione == null)
			throw new Exception("Valore di input non ammesso.");

		List<Impiegato> result = new ArrayList<Impiegato>();
		Impiegato impiegatoTemp = null;
		Compagnia compagniaTemp = null;

		try (PreparedStatement ps = connection.prepareStatement("select * from impiegato i inner join compagnia c on i.compagnia_id = c.id where dataassunzione > ?")) {
			ps.setDate(1, new java.sql.Date(dataAssunzione.getTime()));
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
	public List<Compagnia> findAllByRagioneSocialeContiene(String ragioneSociale) throws Exception {
		// TODO Auto-generated method stub
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (ragioneSociale == null || ragioneSociale.isEmpty())
			throw new Exception("Valore di input non ammesso.");
		
		List<Compagnia> result = new ArrayList<Compagnia>();
		Compagnia compagniaTemp = null;
		
		try (PreparedStatement ps = connection.prepareStatement("select * from compagnia where ragionesociale like ?")) {
			ps.setString(1, ragioneSociale + "%");
			
			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					compagniaTemp = new Compagnia();
					compagniaTemp.setRagioneSociale(rs.getString("ragionesociale"));
					compagniaTemp.setFatturatoAnnuo(rs.getInt("fatturatoannuo"));
					compagniaTemp.setDataFondazione(rs.getDate("datafondazione"));
					compagniaTemp.setId(rs.getLong("id"));

					result.add(compagniaTemp);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			
		}

		return result;
	}

}

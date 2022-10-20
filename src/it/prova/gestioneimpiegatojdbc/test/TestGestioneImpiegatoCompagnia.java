package it.prova.gestioneimpiegatojdbc.test;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import it.prova.gestioneimpiegatojdbc.connection.MyConnection;
import it.prova.gestioneimpiegatojdbc.dao.Constants;
import it.prova.gestioneimpiegatojdbc.dao.compagnia.CompagniaDAO;
import it.prova.gestioneimpiegatojdbc.dao.compagnia.CompagniaDAOImpl;
import it.prova.gestioneimpiegatojdbc.dao.impiegato.ImpiegatoDAO;
import it.prova.gestioneimpiegatojdbc.dao.impiegato.ImpiegatoDAOImpl;
import it.prova.gestioneimpiegatojdbc.model.Compagnia;
import it.prova.gestioneimpiegatojdbc.model.Impiegato;

public class TestGestioneImpiegatoCompagnia {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Istanziamento CompagniaDAO
		CompagniaDAO compagniaDAOInstance = null;
		ImpiegatoDAO impiegatoDAOInstance = null;

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			compagniaDAOInstance = new CompagniaDAOImpl(connection);
			impiegatoDAOInstance = new ImpiegatoDAOImpl(connection);

			// Batteria di Test
//			testCRUDCompagnia(compagniaDAOInstance);
//			testDeleteCompagnia(compagniaDAOInstance);
//			testFindByExampleCompagnia(compagniaDAOInstance);
//			testFindAllByDataAssunzioneMaggioreDiCompagnia(compagniaDAOInstance);
//			testFindAllByRagioneSocialeContieneCompagnia(compagniaDAOInstance);

			testCRUDCompagnia(compagniaDAOInstance);
			System.out.println("In tabella compagnia ci sono " + compagniaDAOInstance.list().size() + " elementi. \n");

			testFindByExampleCompagnia(compagniaDAOInstance);
			System.out.println("In tabella compagnia ci sono " + compagniaDAOInstance.list().size() + " elementi. \n");

			testFindAllByDataAssunzioneMaggioreDiCompagnia(compagniaDAOInstance, impiegatoDAOInstance);
			System.out.println("In tabella compagnia ci sono " + compagniaDAOInstance.list().size() + " elementi.   ");
			System.out.println("In tabella impiegato ci sono " + impiegatoDAOInstance.list().size() + " elementi. \n");

			testFindAllByRagioneSocialeContieneCompagnia(compagniaDAOInstance);
			System.out.println("In tabella compagnia ci sono " + compagniaDAOInstance.list().size() + " elementi.  \n");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private static void testFindAllByRagioneSocialeContieneCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testFindAllByRagioneSocialeContieneCompagnia inizio.............");

		// Create Compagnia
		Compagnia compagniaTest = new Compagnia("Apple", 124, new SimpleDateFormat("dd-MM-yyyy").parse("01-04-1976"));

		if (compagniaDAOInstance.insert(compagniaTest) < 1)
			throw new RuntimeException("testInsertCompagnia : FAILED");

		List<Compagnia> listCompagnia = new ArrayList<Compagnia>();
		listCompagnia = compagniaDAOInstance.list();
		if (listCompagnia.size() != 1)
			throw new RuntimeException("testListCompagnia : FAILED");

		compagniaTest = listCompagnia.get(listCompagnia.size() - 1);

		// ----------PROPER TEST----------
		if (compagniaDAOInstance.findAllByRagioneSocialeContiene("App").size() != 1)
			throw new RuntimeException("testFindAllByRagioneSocialeContieneCompagnia : FAILED");

		// Delete - DELETE
		if (compagniaDAOInstance.delete(compagniaTest) != 1)
			throw new RuntimeException("testDeleteCompagnia : FAILED");

		System.out.println(".......testFindAllByRagioneSocialeContieneCompagnia fine: PASSED.............");

	}

	private static void testFindAllByDataAssunzioneMaggioreDiCompagnia(CompagniaDAO compagniaDAOInstance,
			ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testFindAllByDataAssunzioneMaggioreDiCompagnia inizio.............");

		// CRUD Compagnia
		Compagnia compagniaTest = new Compagnia("Apple", 124, new SimpleDateFormat("dd-MM-yyyy").parse("01-04-1976"));

		if (compagniaDAOInstance.insert(compagniaTest) < 1)
			throw new RuntimeException("testInsertCompagnia : FAILED");

		List<Compagnia> listCompagnia = new ArrayList<Compagnia>();
		listCompagnia = compagniaDAOInstance.list();
		if (listCompagnia.size() != 1)
			throw new RuntimeException("testListCompagnia : FAILED");

		compagniaTest = listCompagnia.get(listCompagnia.size() - 1);

		// CRUD Impiegato
		Impiegato impiegatoTest = new Impiegato("Rossenberg", "Ramboanga", "RMBRSN97P07Z216A",
				new SimpleDateFormat("dd-MM-yyyy").parse("07-09-1997"),
				new SimpleDateFormat("dd-MM-yyyy").parse("01-02-2022"), compagniaTest);

		if (impiegatoDAOInstance.insert(impiegatoTest) < 1)
			throw new RuntimeException("testInsertCompagnia : FAILED");

		List<Impiegato> listImpiegato = new ArrayList<Impiegato>();
		listImpiegato = impiegatoDAOInstance.list();
		if (listImpiegato.size() != 1)
			throw new RuntimeException("testListImpiegato : FAILED");

		impiegatoTest = listImpiegato.get(listCompagnia.size() - 1);

		// ----------PROPER TEST----------
		if (compagniaDAOInstance
				.findAllByDataAssunzioneMaggioreDi(new SimpleDateFormat("dd-MM-yyyy").parse("01-04-1976")).size() != 1)
			throw new RuntimeException("testFindAllByDataAssunzioneMaggioreDiCompagnia : FAILED");

		// Delete - DELETE

		if (impiegatoDAOInstance.delete(impiegatoTest) != 1)
			throw new RuntimeException("testDeleteImpiegato : FAILED");
		if (compagniaDAOInstance.delete(compagniaTest) != 1)
			throw new RuntimeException("testDeleteCompagnia : FAILED");

		System.out.println(".......testFindAllByDataAssunzioneMaggioreDiCompagnia fine: PASSED.............");
	}

	private static void testFindByExampleCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testFindByExampleCompagnia inizio.............");

		Compagnia compagniaTest = new Compagnia("Apple", 124, new SimpleDateFormat("dd-MM-yyyy").parse("01-04-1976"));

		// Insert - CREATE
		int quantiElementiInseriti = compagniaDAOInstance.insert(compagniaTest);
		if (quantiElementiInseriti < 1)
			throw new RuntimeException("testInsertCompagnia : FAILED");

		// List - READ
		List<Compagnia> listCompagnia = new ArrayList<Compagnia>();
		listCompagnia = compagniaDAOInstance.list();
		if (listCompagnia.size() != 1)
			throw new RuntimeException("testListCompagnia : FAILED");

		Long idElement = listCompagnia.get(listCompagnia.size() - 1).getId();
		compagniaTest.setId(idElement);

		// ----------PROPER TEST----------
		Compagnia exampleCompagnia = new Compagnia();
		exampleCompagnia.setRagioneSociale("App");

		if (compagniaDAOInstance.findByExample(exampleCompagnia).size() != 1)
			throw new RuntimeException("testFindByExampleCompagnia : FAILED");

		// Delete - DELETE
		if (compagniaDAOInstance.delete(compagniaTest) != 1)
			throw new RuntimeException("testDeleteCompagnia : FAILED");

		System.out.println(".......testFindByExampleCompagnia fine: PASSED.............");

	}

	private static void testCRUDCompagnia(CompagniaDAO compagniaDAOInstance) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testCRUDCompagnia inizio.............");

		Compagnia compagniaTest = new Compagnia("Apple", 124, new SimpleDateFormat("dd-MM-yyyy").parse("01-04-1976"));

		// Insert - CREATE
		int quantiElementiInseriti = compagniaDAOInstance.insert(compagniaTest);
		if (quantiElementiInseriti < 1)
			throw new RuntimeException("testInsertCompagnia : FAILED");

		// List - READ
		List<Compagnia> listCompagnia = new ArrayList<Compagnia>();
		listCompagnia = compagniaDAOInstance.list();
		if (listCompagnia.size() != 1)
			throw new RuntimeException("testListCompagnia : FAILED");

		Long idElement = listCompagnia.get(listCompagnia.size() - 1).getId();
		compagniaTest.setId(idElement);

		// Update - UPDATE
		compagniaTest.setRagioneSociale("Apple Inc.");
		if (compagniaDAOInstance.update(compagniaTest) != 1)
			throw new RuntimeException("testUpdateCompagnia : FAILED");

		// Delete - DELETE
		if (compagniaDAOInstance.delete(compagniaTest) != 1)
			throw new RuntimeException("testDeleteCompagnia : FAILED");

		System.out.println(".......testCRUDCompagnia fine: PASSED.............");

	}

}

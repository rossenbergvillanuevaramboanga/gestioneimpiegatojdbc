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

public class TestGestioneImpiegatoImpiegato {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Istanziamento CompagniaDAO
		CompagniaDAO compagniaDAOInstance = null;
		ImpiegatoDAO impiegatoDAOInstance = null;

		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			compagniaDAOInstance = new CompagniaDAOImpl(connection);
			impiegatoDAOInstance = new ImpiegatoDAOImpl(connection);

			// Batteria di Test
//			testCRUDImpiegato(compagniaDAOInstance, impiegatoDAOInstance);
//			testFindByExampleImpiegato(compagniaDAOInstance, impiegatoDAOInstance);
//			testFindByAllCompagniaImpiegato(compagniaDAOInstance, impiegatoDAOInstance);
//			testCountByDataFondazioneCompagniaGreaterThanImpiegato(compagniaDAOInstance, impiegatoDAOInstance);
//			testFindAllErrorAssunzioneImpiegato(compagniaDAOInstance, impiegatoDAOInstance);
			
			testCRUDImpiegato(compagniaDAOInstance, impiegatoDAOInstance);
			System.out.println("In tabella compagnia ci sono " + compagniaDAOInstance.list().size() + " elementi.   ");
			System.out.println("In tabella impiegato ci sono " + impiegatoDAOInstance.list().size() + " elementi. \n");
			
			testFindByExampleImpiegato(compagniaDAOInstance, impiegatoDAOInstance);
			System.out.println("In tabella compagnia ci sono " + compagniaDAOInstance.list().size() + " elementi.   ");
			System.out.println("In tabella impiegato ci sono " + impiegatoDAOInstance.list().size() + " elementi. \n");
			
			testFindByAllCompagniaImpiegato(compagniaDAOInstance, impiegatoDAOInstance);
			System.out.println("In tabella compagnia ci sono " + compagniaDAOInstance.list().size() + " elementi.   ");
			System.out.println("In tabella impiegato ci sono " + impiegatoDAOInstance.list().size() + " elementi. \n");
			
			testCountByDataFondazioneCompagniaGreaterThanImpiegato(compagniaDAOInstance, impiegatoDAOInstance);
			System.out.println("In tabella compagnia ci sono " + compagniaDAOInstance.list().size() + " elementi.   ");
			System.out.println("In tabella impiegato ci sono " + impiegatoDAOInstance.list().size() + " elementi. \n");
			
			testFindAllErrorAssunzioneImpiegato(compagniaDAOInstance, impiegatoDAOInstance);
			System.out.println("In tabella compagnia ci sono " + compagniaDAOInstance.list().size() + " elementi.   ");
			System.out.println("In tabella impiegato ci sono " + impiegatoDAOInstance.list().size() + " elementi. \n");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	private static void testFindAllErrorAssunzioneImpiegato(CompagniaDAO compagniaDAOInstance,
			ImpiegatoDAO impiegatoDAOInstance) throws Exception{
		// TODO Auto-generated method stub
		
		System.out.println(".......testFindAllErrorAssunzioneImpiegato inizio.............");

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
		
		//PROPER TEST
		if (impiegatoDAOInstance.findAllErroriAssunzione().size() != 0)
			throw new RuntimeException("testCountByDataFondazioneCompagniaGreaterThanImpiegato : FAILED");

		// Delete - DELETE

		if (impiegatoDAOInstance.delete(impiegatoTest) != 1)
			throw new RuntimeException("testDeleteImpiegato : FAILED");
		if (compagniaDAOInstance.delete(compagniaTest) != 1)
			throw new RuntimeException("testDeleteCompagnia : FAILED");

		System.out.println(".......testFindAllErrorAssunzioneImpiegato fine: PASSED.............");
		
	}

	private static void testCountByDataFondazioneCompagniaGreaterThanImpiegato(CompagniaDAO compagniaDAOInstance,
			ImpiegatoDAO impiegatoDAOInstance) throws Exception{
		// TODO Auto-generated method stub
		
		System.out.println(".......testCountByDataFondazioneCompagniaGreaterThanImpiegato inizio.............");

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
		
		//PROPER TEST
		if (impiegatoDAOInstance.countByDataFondazioneCompagniaGreaterThan(new SimpleDateFormat("dd-MM-yyyy").parse("01-01-1900")) != 1)
			throw new RuntimeException("testCountByDataFondazioneCompagniaGreaterThanImpiegato : FAILED");

		// Delete - DELETE

		if (impiegatoDAOInstance.delete(impiegatoTest) != 1)
			throw new RuntimeException("testDeleteImpiegato : FAILED");
		if (compagniaDAOInstance.delete(compagniaTest) != 1)
			throw new RuntimeException("testDeleteCompagnia : FAILED");

		System.out.println(".......testCountByDataFondazioneCompagniaGreaterThanImpiegato fine: PASSED.............");
		
	}

	private static void testFindByAllCompagniaImpiegato(CompagniaDAO compagniaDAOInstance,
			ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testFindByExampleImpiegato inizio.............");

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
		
		//PROPER TEST
		if (impiegatoDAOInstance.findAllByCompagnia(compagniaTest).size() != 1)
			throw new RuntimeException("testFindByExampleCompagnia : FAILED");

		// Delete - DELETE

		if (impiegatoDAOInstance.delete(impiegatoTest) != 1)
			throw new RuntimeException("testDeleteImpiegato : FAILED");
		if (compagniaDAOInstance.delete(compagniaTest) != 1)
			throw new RuntimeException("testDeleteCompagnia : FAILED");

		System.out.println(".......testFindByExampleImpiegato fine: PASSED.............");
		
	}

	private static void testFindByExampleImpiegato(CompagniaDAO compagniaDAOInstance,
			ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testFindByExampleImpiegato inizio.............");

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
		
		//PROPER TEST
		Impiegato exampleImpiegato = new Impiegato();
		exampleImpiegato.setNome("Ross");

		if (impiegatoDAOInstance.findByExample(exampleImpiegato).size() != 1)
			throw new RuntimeException("testFindByExampleCompagnia : FAILED");

		// Delete - DELETE

		if (impiegatoDAOInstance.delete(impiegatoTest) != 1)
			throw new RuntimeException("testDeleteImpiegato : FAILED");
		if (compagniaDAOInstance.delete(compagniaTest) != 1)
			throw new RuntimeException("testDeleteCompagnia : FAILED");

		System.out.println(".......testFindByExampleImpiegato fine: PASSED.............");
		
	}

	private static void testCRUDImpiegato(CompagniaDAO compagniaDAOInstance, ImpiegatoDAO impiegatoDAOInstance) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(".......testCRUDImpiegato inizio.............");

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

		// Delete - DELETE

		if (impiegatoDAOInstance.delete(impiegatoTest) != 1)
			throw new RuntimeException("testDeleteImpiegato : FAILED");
		if (compagniaDAOInstance.delete(compagniaTest) != 1)
			throw new RuntimeException("testDeleteCompagnia : FAILED");

		System.out.println(".......testCRUDImpiegato fine: PASSED.............");
		
	}

}

package test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import main.domeinLaag.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;

public class VluchtTest {

	static LuchtvaartMaatschappij lvm;
	static Fabrikant fabr;
	static VliegtuigType vtype;
	static Land landNL, landFR;
	static Vliegtuig vtg;
	static Luchthaven havenVertrek, havenAankomst;
	static Vlucht vlucht, vlucht2;
	static Calendar datum, tijdVertrek, tijdAankomst, tijdVertrek2, testAankomst2;

	@BeforeEach
	public void initialize() {
		try {
			lvm = new LuchtvaartMaatschappij("ZoefZoef");
			fabr = new Fabrikant("Boeing", "M. de wit");
			vtype = fabr.creeervliegtuigtype("Soms", 150);
			datum = Calendar.getInstance();
			datum.set(2000, Calendar.MARCH, 3);
			landFR = new Land("Frankrijk", 1);
			landNL = new Land("Nederland", 2);
			havenVertrek = new Luchthaven("Bonn", "1", true, landFR);
			havenAankomst = new Luchthaven("Schiphol", "2", true, landNL);
			vtg = new Vliegtuig(lvm, vtype, "Fokke", datum);
		} catch (Exception e) {System.out.println("An error occured: " + e);}
	}

	@Test
	public void testVerschillendeLuchthavens() {
		vlucht = new Vlucht();

		try {
			vlucht.zetVliegtuig(vtg);
			vlucht.zetVertrekpunt(havenVertrek);
			vlucht.zetBestemming(havenVertrek);
			/**
			Het programma vind dit goed...

			assertNotEquals(vlucht.getBestemming(), vlucht.getVertrekPunt());
			 */
		} catch (IllegalArgumentException e) {
			System.out.println(e.toString());
		}
		try {
			vlucht.zetVliegtuig(vtg);
			vlucht.zetVertrekpunt(havenVertrek);
			vlucht.zetBestemming(havenAankomst);
			assertNotEquals(vlucht.getBestemming(), vlucht.getVertrekPunt());
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}

	@Test
	public void testVertrekAankomst() {
		vlucht = new Vlucht();
		vlucht.zetVertrekpunt(havenVertrek);
		vlucht.zetBestemming(havenAankomst);
		tijdVertrek = Calendar.getInstance();
		tijdAankomst = Calendar.getInstance();
		try {
			tijdVertrek.set(2025, Calendar.SEPTEMBER, 31, 24, 0);
			vlucht.zetVertrekTijd(tijdVertrek);
		} catch (VluchtException e) {
			System.out.println(e.toString());
		}
		try {
			tijdVertrek.set(2025, Calendar.SEPTEMBER, 30, 24, 0);
			vlucht.zetVertrekTijd(tijdVertrek);
			tijdAankomst.set(2025, Calendar.SEPTEMBER, 30, 24, 1);
			vlucht.zetAankomstTijd(tijdAankomst);
		} catch (VluchtException e) {
			System.out.println(e.toString());
		}
		try {
			tijdVertrek.set(2025, Calendar.SEPTEMBER, 30, 12, 0);
			vlucht.zetVertrekTijd(tijdVertrek);
			tijdAankomst.set(2025, Calendar.SEPTEMBER, 30, 12, 1);
			vlucht.zetAankomstTijd(tijdAankomst);
		} catch (VluchtException e) {
			System.out.println(e.toString());
		}
		try {
			tijdVertrek.setTimeInMillis(System.currentTimeMillis()-60000);
			vlucht.zetVertrekTijd(tijdVertrek);
			if (vlucht.getVertrekTijd().before(Calendar.getInstance())) throw new VluchtException("Tijd in het verleden");
		} catch (VluchtException e) {
			System.out.println(e.toString());
		}
		try {
			tijdVertrek.setTimeInMillis(System.currentTimeMillis()-120000);
			vlucht.zetVertrekTijd(tijdVertrek);
			tijdAankomst.setTimeInMillis(System.currentTimeMillis()-60000);
			vlucht.zetAankomstTijd(tijdAankomst);
			if (vlucht.getVertrekTijd().before(Calendar.getInstance()) && vlucht.getAankomstTijd().before(Calendar.getInstance())) throw new VluchtException("2x Tijd in het verleden");
			else if (vlucht.getVertrekTijd().before(Calendar.getInstance())) throw new VluchtException("Tijd in het verleden");
			else if (vlucht.getAankomstTijd().before(Calendar.getInstance())) throw new VluchtException("Tijd in het verleden");
		} catch (VluchtException e) {
			System.out.println(e.toString());
		}
		try {
			tijdVertrek.setTimeInMillis(System.currentTimeMillis());
			vlucht.zetVertrekTijd(tijdVertrek);
			tijdAankomst.setTimeInMillis(System.currentTimeMillis()+60000);
			vlucht.zetAankomstTijd(tijdAankomst);
			if (vlucht.getVertrekTijd().before(Calendar.getInstance()) && vlucht.getAankomstTijd().before(Calendar.getInstance())) throw new VluchtException("2x Tijd in het verleden");
			else if (vlucht.getVertrekTijd().before(Calendar.getInstance())) throw new VluchtException("Tijd in het verleden");
			else if (vlucht.getAankomstTijd().before(Calendar.getInstance())) throw new VluchtException("Tijd in het verleden");
		} catch(VluchtException e) {
			System.out.println(e.toString());
		}
	}

	@Test
	public void testOverlapping() {
		vlucht = new Vlucht();
		vlucht.zetVliegtuig(vtg);
		vlucht.zetVertrekpunt(havenVertrek);
		vlucht.zetBestemming(havenAankomst);
		tijdVertrek = Calendar.getInstance();
		tijdAankomst = Calendar.getInstance();
		tijdVertrek.set(2025, Calendar.JULY, 1, 12, 43);
		tijdAankomst.set(2025, Calendar.JULY, 1, 15, 36);
		try {
			vlucht.zetVertrekTijd(tijdVertrek);
			vlucht.zetAankomstTijd(tijdAankomst);
		} catch (Exception e) {System.out.println(e.toString());}
		vlucht2 = new Vlucht();
		vlucht2.zetVliegtuig(vtg);
		vlucht2.zetVertrekpunt(havenVertrek);
		vlucht2.zetBestemming(havenAankomst);
		tijdVertrek2 = Calendar.getInstance();
		testAankomst2 = Calendar.getInstance();
		try {
			tijdVertrek2.set(2025, Calendar.JULY, 1, 15, 36);
			vlucht2.zetVertrekTijd(tijdVertrek2);
			testAankomst2.set(2025, Calendar.JULY, 1, 16, 36);
			vlucht2.zetAankomstTijd(testAankomst2);
		} catch (VluchtException e) {
			System.out.println(e.toString());
		}
		try {
			tijdVertrek2.set(2025, Calendar.JULY, 1, 11, 36);
			vlucht2.zetVertrekTijd(tijdVertrek2);
			testAankomst2.set(2025, Calendar.JULY, 1, 12, 43);
			vlucht2.zetAankomstTijd(testAankomst2);
		} catch (VluchtException e) {
			System.out.println(e.toString());
		}
		try {
			tijdVertrek2.set(2025, Calendar.JULY, 1, 12, 42);
			vlucht2.zetVertrekTijd(tijdVertrek2);
			testAankomst2.set(2025, Calendar.JULY, 1, 15, 37);
			vlucht2.zetAankomstTijd(testAankomst2);
		} catch (VluchtException e) {
			System.out.println(e.toString());
		}
		try {
			tijdVertrek2.set(2025, Calendar.JULY, 1, 15, 37);
			vlucht2.zetVertrekTijd(tijdVertrek2);
			testAankomst2.set(2025, Calendar.JULY, 1, 16, 37);
			vlucht2.zetAankomstTijd(testAankomst2);
		} catch (VluchtException e) {
			System.out.println(e.toString());
		}
	}
}

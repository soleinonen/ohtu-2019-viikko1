package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto saldollinenVarasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoVarastonOikeinNollatilavuudella() {
    	Varasto virheellinenVarasto = new Varasto(-1);
    	assertEquals(0, virheellinenVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriLuoVarastonSaldolla() {
    	Varasto saldollinenVarasto = new Varasto(10,5);
    	assertEquals(5, saldollinenVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriKasitteleeNegatiivisenSaldonOikein() {
    	Varasto saldollinenVarasto = new Varasto(10,-5);
    	assertEquals(0, saldollinenVarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktoriSaldollallaLuoVarastonNegatiivisellaTilavuudella() {
    	Varasto saldollinenVarasto = new Varasto(-1,0);
    	assertEquals(0, saldollinenVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktorissaLisaysYlimaarainenHukkaan() {
    	Varasto saldollinenVarasto = new Varasto(5, 10);
    	assertEquals(5, saldollinenVarasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenLisaysEiTeeMitaan() {
    	varasto.lisaaVarastoon(-1);
    	
    	// saldon pitäisi pysyä samana jos yritetään lisätä negatiivinen määrä
    	assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ylimaarainenMeneeHukkaanLisayksessa() {
    	varasto.lisaaVarastoon(12);
    	
    	// Saldon pitäisi olla maksimi kun lisätään varastoon enemmän kuin mitä kapasiteetti
    	assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void negatiivinenOttaminenPitaaSaldonEnnallaan() {
    	varasto.lisaaVarastoon(10);
    	varasto.otaVarastosta(-200);
    	assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void yliSaldonOttaminenPalauttaaOikeanMaaran() {
    	varasto.lisaaVarastoon(10);
    	double palautettu = varasto.otaVarastosta(100);
    	assertEquals(10, palautettu, vertailuTarkkuus);
    }
    
    @Test
    public void yliSaldonOttaminenNollaaSaldon() {
    	varasto.lisaaVarastoon(10);
    	varasto.otaVarastosta(100);
    	assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void merkkijononTulostus() {
    	assertEquals("saldo = 0.0, vielä tilaa 10.0", varasto.toString());
    }

}
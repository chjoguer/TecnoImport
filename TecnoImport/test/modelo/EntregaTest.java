/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ilian
 */
public class EntregaTest {
    
    public EntregaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getIdentrga method, of class Entrega.
     */
    @Test
    public void testGetIdentrga() {
        System.out.println("getIdentrga");
        Entrega instance = new Entrega(1, "SALIDA", "34 Y LETAMENDI", "Iliana", "Bolaños");
        Integer expResult = 1;
        assertEquals(instance.getIdentrga(), expResult);
    }



    /**
     * Test of getDescripcion method, of class Entrega.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
        Entrega instance = new Entrega(1, "SALIDA", "34 Y LETAMENDI", "Iliana", "Bolaños");
        String expResult = "SALIDA";
        assertEquals(instance.getDescripcion(), expResult);
    }



    /**
     * Test of getDireccion method, of class Entrega.
     */
    @Test
    public void testGetDireccion() {
        System.out.println("getDireccion");
        Entrega instance = new Entrega(1, "SALIDA", "34 Y LETAMENDI", "Iliana", "Bolaños");
        String expResult = "34 Y LETAMENDI";
        assertEquals(instance.getDireccion(), expResult);
    }

 

    /**
     * Test of getNombre method, of class Entrega.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Entrega instance = new Entrega(1, "SALIDA", "34 Y LETAMENDI", "Iliana", "Bolaños");
        String expResult = "Iliana";
        assertEquals(instance.getDireccion(), expResult);
    }


    /**
     * Test of getApellido method, of class Entrega.
     */
    @Test
    public void testGetApellido() {
        System.out.println("getApellido");
        Entrega instance = new Entrega(1, "SALIDA", "34 Y LETAMENDI", "Iliana", "Bolaños");
        String expResult = "Bolaños";
        assertEquals(instance.getDireccion(), expResult);
    }


    
}

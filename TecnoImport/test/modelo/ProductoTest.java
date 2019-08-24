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
public class ProductoTest {
    
    public ProductoTest() {
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
     * Test of getNombre method, of class Producto.
     */
    @Test
    public void testGetNombre() {
        System.out.println("getNombre");
        Producto instance = new Producto("cocina", 5, "Cocina de 4 hornillas", "Cocina", 23);
        String result = instance.getNombre();
        assertEquals("cocina",result);

    }



    /**
     * Test of getCantidad method, of class Producto.
     */
    @Test
    public void testGetCantidad() {
        System.out.println("getCantidad");
        Producto instance = new Producto("cocina", 5, "Cocina de 4 hornillas", "Cocina", 23);
        String expResult = "";
        int result = instance.getCantidad();
        assertNotEquals(10,result);
    }


    /**
     * Test of getDescripcion method, of class Producto.
     */
    @Test
    public void testGetDescripcion() {
        System.out.println("getDescripcion");
                Producto instance = new Producto("cocina", 5, "Cocina de 4 hornillas", "Cocina", 23);
        assertNotNull(instance.getDescripcion());
    }

    
    
}

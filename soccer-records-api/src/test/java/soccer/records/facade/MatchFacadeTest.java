/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import soccer.records.dto.MatchCreateDto;
import soccer.records.dto.MatchDto;
import soccer.records.dto.MatchEditDto;

/**
 *
 * @author windows_sucks
 */
public class MatchFacadeTest {
    
    public MatchFacadeTest() {
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
     * Test of createMatch method, of class MatchFacade.
     */
    @Test
    public void testCreateMatch() {
        System.out.println("createMatch");
        MatchCreateDto m = null;
        MatchFacade instance = new MatchFacadeImpl();
        Long expResult = null;
        Long result = instance.createMatch(m);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateMatch method, of class MatchFacade.
     */
    @Test
    public void testUpdateMatch() {
        System.out.println("updateMatch");
        MatchEditDto m = null;
        MatchFacade instance = new MatchFacadeImpl();
        instance.updateMatch(m);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteMatch method, of class MatchFacade.
     */
    @Test
    public void testDeleteMatch() {
        System.out.println("deleteMatch");
        Long id = null;
        MatchFacade instance = new MatchFacadeImpl();
        instance.deleteMatch(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllMatches method, of class MatchFacade.
     */
    @Test
    public void testFindAllMatches() {
        System.out.println("findAllMatches");
        MatchFacade instance = new MatchFacadeImpl();
        List<MatchDto> expResult = null;
        List<MatchDto> result = instance.findAllMatches();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findMatchById method, of class MatchFacade.
     */
    @Test
    public void testFindMatchById() {
        System.out.println("findMatchById");
        Long id = null;
        MatchFacade instance = new MatchFacadeImpl();
        MatchDto expResult = null;
        MatchDto result = instance.findMatchById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addPlayerResult method, of class MatchFacade.
     */
    @Test
    public void testAddPlayerResult() {
        System.out.println("addPlayerResult");
        Long m = null;
        Long r = null;
        MatchFacade instance = new MatchFacadeImpl();
        instance.addPlayerResult(m, r);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of matchResult method, of class MatchFacade.
     */
    @Test
    public void testMatchResult() {
        System.out.println("matchResult");
        Long m = null;
        MatchFacade instance = new MatchFacadeImpl();
        String expResult = "";
        String result = instance.matchResult(m);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class MatchFacadeImpl implements MatchFacade {

        public Long createMatch(MatchCreateDto m) {
            return null;
        }

        public void updateMatch(MatchEditDto m) {
        }

        public void deleteMatch(Long id) {
        }

        public List<MatchDto> findAllMatches() {
            return null;
        }

        public MatchDto findMatchById(Long id) {
            return null;
        }

        public void addPlayerResult(Long m, Long r) {
        }

        public String matchResult(Long m) {
            return "";
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import java.util.Arrays;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import soccer.records.config.ServiceConfiguration;
import soccer.records.dto.PlayerCreateDto;
import soccer.records.dto.PlayerDto;
import soccer.records.dto.PlayerEditDto;
import soccer.records.entity.Player;
import soccer.records.entity.Team;
import soccer.records.services.BeanMappingService;
import soccer.records.services.PlayerService;
import soccer.records.services.TeamService;

/**
 * Facade tests
 * 
 * @author Michaela Bocanova
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class PlayerFacadeTest extends AbstractTestNGSpringContextTests {
    
    
    @Mock 
    private PlayerService playerService;
    @Mock
    private TeamService teamService;
    
    @Mock
    private BeanMappingService mappingService;
    
    @Mock
    private PlayerDto p1Dto;
    @Mock
    private PlayerEditDto p1EditDto;
    @Mock
    private PlayerCreateDto p1CreateDto;
    
    @Mock
    private Team t1;
    @Mock
    private Player p1;
    
    //@Inject//doesnt work with @transactional
    @InjectMocks
    private PlayerFacade playerFacade = new PlayerFacadeImpl();
               
    @BeforeTest
    public void setUp() //throws FacadeException 
    {
        MockitoAnnotations.initMocks(this);
 
    }
    //@BeforeMethod
    public void resetMock() {
        Mockito.reset(playerService);
        
    }
    
    @Test
    public void createPlayer() {
        when(mappingService.mapTo(p1CreateDto, Player.class)).thenReturn(p1);
        when(teamService.findById(p1CreateDto.getTeamId())).thenReturn(t1);
        playerFacade.createPlayer(p1CreateDto);
        verify(playerService).create(p1);
    }
    
    @Test
    public void updatePlayer() {
        Mockito.when(mappingService.mapTo(p1EditDto, Player.class)).thenReturn(p1);
        playerFacade.updatePlayer(p1EditDto);
        Mockito.verify(playerService).update(p1);
    }
    
    @Test
    public void deletePlayer() {
        //Mockito.when(mappingService.mapTo(pr1Dto, Player.class)).thenReturn(pr1);
        Mockito.when(playerService.findById(1L)).thenReturn(p1);
        playerFacade.deletePlayer(1L);
        Mockito.verify(playerService).remove(p1);
    }
    
    @Test
    public void findAllPlayers() {
    
        List<Player> list = Arrays.asList(p1);
        Mockito.when(playerService.findAll()).thenReturn(list);
        List<PlayerDto> listDto = Arrays.asList(p1Dto);
        Mockito.when(mappingService.mapTo(list, PlayerDto.class)).thenReturn(listDto);
        
        List<PlayerDto> actual = playerFacade.findAllPlayers();
        //Mockito.verify(playerService).findAll();
        //Mockito.verify(mappingService).mapTo(list, PlayerDto.class);
        
        //Assert.assertEquals(actual, listDto);
    }
    
    @Test
    public void findPlayerById() {
        Mockito.when(playerService.findById(1L)).thenReturn(p1);
        Mockito.when(mappingService.mapTo(p1, PlayerDto.class)).thenReturn(p1Dto);
        
        PlayerDto actual = playerFacade.findPlayerById(1L);
        Mockito.verify(playerService, times(2)).findById(1L);
        Mockito.verify(mappingService).mapTo(p1, PlayerDto.class);
        
        Assert.assertEquals(actual, p1Dto);
    }
}

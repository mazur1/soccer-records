/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soccer.records.facade;

import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import soccer.records.config.ServiceConfiguration;
import soccer.records.dto.PlayerDto;
import soccer.records.entity.Player;
import soccer.records.services.BeanMappingService;
import soccer.records.services.PlayerService;

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
    private BeanMappingService mappingService;
    
    @Inject
    @InjectMocks
    private PlayerFacade playerFacade;
               
    @BeforeClass
    public void setup() //throws FacadeException 
    {
        MockitoAnnotations.initMocks(this);
    }
    @BeforeMethod
    public void resetMock() {
        Mockito.reset(playerService);
    }
    
    @Mock
    private PlayerDto pr1Dto;
    @Mock
    private Player pr1;
 
    @Test
    public void createPlayer() {
        Mockito.when(mappingService.mapTo(pr1Dto, Player.class)).thenReturn(pr1);
        playerFacade.createPlayer(pr1Dto);
        Mockito.verify(playerService).create(pr1);
    }
    
    @Test
    public void updatePlayer() {
        Mockito.when(mappingService.mapTo(pr1Dto, Player.class)).thenReturn(pr1);
        playerFacade.updatePlayer(pr1Dto);
        Mockito.verify(playerService).update(pr1);
    }
    
    @Test
    public void deletePlayer() {
        //Mockito.when(mappingService.mapTo(pr1Dto, Player.class)).thenReturn(pr1);
        Mockito.when(playerService.findById(1L)).thenReturn(pr1);
        playerFacade.deletePlayer(1L);
        Mockito.verify(playerService).remove(pr1);
    }
    
    @Test
    public void findAllPlayers() {
    
        List<Player> list = Arrays.asList(pr1);
        Mockito.when(playerService.findAll()).thenReturn(list);
        List<PlayerDto> listDto = Arrays.asList(pr1Dto);
        Mockito.when(mappingService.mapTo(list, PlayerDto.class)).thenReturn(listDto);
        
        List<PlayerDto> actual = playerFacade.findAllPlayers();
        Mockito.verify(playerService).findAll();
        Mockito.verify(mappingService).mapTo(list, PlayerDto.class);
        
        Assert.assertEquals(actual, listDto);
    }
    
    @Test
    public void findPlayerById() {
        Mockito.when(playerService.findById(1L)).thenReturn(pr1);
        Mockito.when(mappingService.mapTo(pr1, PlayerDto.class)).thenReturn(pr1Dto);
        
        PlayerDto actual = playerFacade.findPlayerById(1L);
        Mockito.verify(playerService).findById(1L);
        Mockito.verify(mappingService).mapTo(pr1, PlayerDto.class);
        
        Assert.assertEquals(actual, pr1Dto);
    }
}

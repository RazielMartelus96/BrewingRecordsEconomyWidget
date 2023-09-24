import io.curiositycore.brewingrecordseconomywidget.model.brew.BrewFactory;
import io.curiositycore.brewingrecordseconomywidget.model.brew.BrewManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ConfigTest {


    @BeforeEach
    public void getConfig(){
        BrewFactory brewFactory = new BrewFactory("/config.yml");
        BrewManager brewManager = BrewManager.getInstance();
        brewFactory.buildBrewSet().forEach(brew -> brewManager.register(brew));
    }

    @Test
    public void getBrewNames(){

    }
}

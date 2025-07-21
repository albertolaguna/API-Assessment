package service;

import com.solera.bootcamp.betos.service.WorkshopService;
import org.junit.jupiter.api.Test;
import service.*;

class workShopServiceTest{

    void testWorkShopService(){
        WorkshopService workshop = new WorkshopService();
        workshop.createWorkshop();
    }

    @Test
    void validateProductEqualsTest(){
        //creamos instancias e inicializamos el objeto
        WorkshopService workshop = new WorkshopService();
        assertNotEquals

    }

}

package com.example.demo.service;

import com.example.demo.dao.TankDao;
import com.example.demo.exception.MyException;
import com.example.demo.model.Tank;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TankServiceImplTest {
    private final TankService service;
    private final TankDao dao;

    @Autowired
    TankServiceImplTest(TankService service, TankDao dao) {
        this.service = service;
        this.dao = dao;
    }


    @Test
    void getTank() {
    }

    @Test
    void getAllTanks() {
    }

    @Test
    void createTanks() {
    }

    @Test
    void connectTo() throws MyException {
        service.createTanks(4);
        service.connectTo(1, 2);

        final List<Tank> allTanks = service.getAllTanks();

        service.createTanks(2);
        service.connectTo(3,4);



        service.connectTo(1, 3);


        service.addWater(1, 20);

        service.addWater(3, 3);
        service.addWater(5, 8);

        for (Tank value : allTanks){

            System.out.println(value.getForeignTanks().size());
            System.out.println(value.getCapacity());

//            value.getForeignTanks().stream().forEach(System.out::println);

        }

        System.out.println();
    }

}
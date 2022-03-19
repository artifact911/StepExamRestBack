package com.example.demo.service;

import com.example.demo.dao.TankDao;
import com.example.demo.exception.MyException;
import com.example.demo.model.Tank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TankServiceImpl implements TankService {

    private final TankDao dao;

    @Autowired
    public TankServiceImpl(TankDao dao) {
        this.dao = dao;
    }

    @Override
    public Tank getTank(long id) throws MyException {

        for (Tank value : dao.getTanks()) {
            if (value.getId() == id) {
                return value;
            }
        }
        return new Tank();

//        final Optional<Tank> optionalTank = dao.getTanks().stream().filter(tank -> id == tank.getId()).findAny();
//
//        return optionalTank.orElseThrow(() -> new MyException("not found"));
    }

    @Override
    public List<Tank> getAllTanks() {
        return dao.getTanks();
    }

    @Override
    public List<Tank> createTanks(int amount) {
        for (int i = 0; i < amount; i++) {
            getAllTanks().add(new Tank());
        }
        return dao.getTanks();
    }

    @Override
    public List<Tank> addWater(long id, int waterAmount) throws MyException {

        final double v = getTank(id).getWaterAmount();

        double totalAmountWater = 0;

        for(long value : getTank(id).getForeignTanks()){
           totalAmountWater += getTank(value).getWaterAmount();
        }

        if((totalAmountWater + waterAmount) < 0){
            getTank(id).setWaterAmount(0);
        } else {
            getTank(id).setWaterAmount(v + waterAmount);
        }
//        if ((waterAmount + v) < 0) {
//            getTank(id).setWaterAmount(0);
//        } else {
//            getTank(id).setWaterAmount(v + waterAmount);
//        }

        divideWater(getTank(id).getForeignTanks());

        return getAllTanks();
    }

//    @Override
//    public List<Tank> connectTo(long id, long otherId) throws MyException {
//
//        final Tank sourceTank = getTank(id);
//        final Tank connectingTank = getTank(otherId);
//
//        sourceTank.getForeignTanks().addAll(connectingTank.getForeignTanks());
//
//        Set<Tank> setOfForeignTanks = new HashSet<>(sourceTank.getForeignTanks());
//
//        for (Tank value : sourceTank.getForeignTanks()) {
//
//            value.getForeignTanks().addAll(setOfForeignTanks);
//        }
//        return getAllTanks();
//    }

    @Override
    public List<Tank> connectTo(long id, long otherId) throws MyException {

        final Tank sourceTank = getTank(id);
        final Tank connectingTank = getTank(otherId);

        sourceTank.getForeignTanks().addAll(connectingTank.getForeignTanks());

        Set<Long> setOfForeignTanks = new HashSet<>(sourceTank.getForeignTanks());

        for (Long value : sourceTank.getForeignTanks()) {

            getTank(value).getForeignTanks().addAll(setOfForeignTanks);
        }
        divideWater(setOfForeignTanks);
        return getAllTanks();
    }

//    private void divideWater(Set<Tank> tanks) {
//        final double sum = tanks.stream().mapToDouble(Tank::getCapacity).sum();
//
//        for (Tank tank : tanks) {
//            tank.setCapacity(sum / tanks.size());
//        }
//    }

    private void divideWater(Set<Long> tanks) throws MyException {

        double sum = 0;

        for (Long val : tanks) {
            sum += getTank(val).getWaterAmount();
        }
        for (Long val : tanks) {
            getTank(val).setWaterAmount(sum / tanks.size());
        }
    }


}

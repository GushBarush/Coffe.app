package com.example.coffeapp.service;

import com.example.coffeapp.dto.payday.PayDayDTO;
import com.example.coffeapp.entity.payday.PayDay;
import com.example.coffeapp.repository.PayDayRepo;
import com.example.coffeapp.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@AllArgsConstructor
public class PayDayService {

    final PayDayRepo payDayRepo;
    final UserRepo userRepo;

    public PayDayDTO getNewPayDay(String userNumber) {
        PayDay payDayCheck = payDayRepo.findByActive(true);

        if(payDayCheck != null) {
            PayDayDTO payDayDTO;
            ModelMapper mapper = new ModelMapper();
            payDayDTO = mapper.map(payDayCheck, PayDayDTO.class);

            return payDayDTO;
        }

        PayDay payDayEntity = new PayDay();
        ModelMapper mapper = new ModelMapper();
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
        PayDayDTO payDayDTO;

        payDayEntity.setActive(true);
        payDayEntity.setOpenTime(localDateTime);
        payDayEntity.setUser(userRepo.findByPhoneNumber(userNumber));
        payDayEntity.setSumFree(0.0);
        payDayEntity.setSumAll(0.0);
        payDayEntity.setSumCash(0.0);
        payDayEntity.setSumNotCash(0.0);
        payDayEntity.setSumExpense(0.0);

        payDayRepo.save(payDayEntity);

        payDayDTO = mapper.map(payDayEntity, PayDayDTO.class);

        return payDayDTO;
    }

    public PayDayDTO getCurrentPayDay() {
        PayDayDTO payDayDTO;
        PayDay payDayEntity = payDayRepo.findByActive(true);

        if (payDayEntity == null) {
            return null;
        }

        ModelMapper mapper = new ModelMapper();
        payDayDTO = mapper.map(payDayEntity, PayDayDTO.class);

        return payDayDTO;
    }

    public void endPayDay(Long id) {
        PayDay payDay = payDayRepo.getById(id);
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Europe/Moscow"));

        payDay.setCloseTime(localDateTime);
        payDay.setActive(false);

        payDayRepo.save(payDay);
    }
}

package com.example.coffeapp.service;

import com.example.coffeapp.dto.payday.PayDayDTO;
import com.example.coffeapp.entity.payday.PayDay;
import com.example.coffeapp.repository.PayDayRepo;
import com.example.coffeapp.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@AllArgsConstructor
public class PayDayService {

    private final PayDayRepo payDayRepo;
    private final UserRepo userRepo;

    public PayDayDTO getNewPayDay(String userNumber) {
        PayDay payDayEntity = new PayDay();
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime time = ldt.atZone(ZoneId.of("Europe/Moscow"));
        Timestamp currentTime = new Timestamp(time.toInstant().toEpochMilli());
        ModelMapper mapper = new ModelMapper();
        PayDayDTO payDayDTO;

        payDayEntity.setActive(true);
        payDayEntity.setOpenTime(currentTime);
        payDayEntity.setUser(userRepo.findByPhoneNumber(userNumber));
        payDayEntity.setSumAll(0.0);
        payDayEntity.setSumCash(0.0);
        payDayEntity.setSumNotCash(0.0);

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
        LocalDateTime ldt = LocalDateTime.now();
        ZonedDateTime time = ldt.atZone(ZoneId.of("Europe/Moscow"));
        Timestamp currentTime = new Timestamp(time.toInstant().toEpochMilli());

        payDay.setCloseTime(currentTime);
        payDay.setActive(false);

        payDayRepo.save(payDay);
    }
}

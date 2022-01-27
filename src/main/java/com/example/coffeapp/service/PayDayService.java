package com.example.coffeapp.service;

import com.example.coffeapp.dto.payday.PayDayDTO;
import com.example.coffeapp.entity.payday.PayDay;
import com.example.coffeapp.repository.PayDayRepo;
import com.example.coffeapp.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@AllArgsConstructor
public class PayDayService {

    private final PayDayRepo payDayRepo;
    private final UserRepo userRepo;

    public PayDayDTO getNewPayDay(Long userId) {
        PayDay payDayEntity = new PayDay();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        ModelMapper mapper = new ModelMapper();
        PayDayDTO payDayDTO;

        payDayEntity.setActive(true);
        payDayEntity.setOpenTime(currentTime);
        payDayEntity.setUser(userRepo.findById(userId).orElse(null));
        payDayEntity.setSumAll(0.0);
        payDayEntity.setSumCash(0.0);
        payDayEntity.setSumNotCash(0.0);

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
}

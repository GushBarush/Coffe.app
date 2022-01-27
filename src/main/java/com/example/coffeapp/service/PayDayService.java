package com.example.coffeapp.service;

import com.example.coffeapp.dto.payday.PayDayDTO;
import com.example.coffeapp.entity.payday.PayDay;
import com.example.coffeapp.repository.PayDayRepo;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PayDayService {

    private final PayDayRepo payDayRepo;

    public PayDayDTO getCurrentPayDay() {
        PayDayDTO payDayDTO = new PayDayDTO();
        PayDay payDayEntity = payDayRepo.findByActive(true);

        if (payDayEntity == null) {
            return payDayDTO;
        }

        ModelMapper mapper = new ModelMapper();
        payDayDTO = mapper.map(payDayEntity, PayDayDTO.class);

        return payDayDTO;
    }
}

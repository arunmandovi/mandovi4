package com.mandovi.Service;

import com.mandovi.DTO.HoldUpDayDTO;
import com.mandovi.Entity.HoldUpDay;
import com.mandovi.Repository.HoldUpDayRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoldUpDayServiceImpl implements HoldUpDayService {
    private final HoldUpDayRepository holdUpDayRepository;

    public HoldUpDayServiceImpl(HoldUpDayRepository holdUpDayRepository) {
        this.holdUpDayRepository = holdUpDayRepository;
    }

    @Override
    public List<String> getHoldUpDayList() {
        return holdUpDayRepository.getHoldUpDayList();
    }

    @Override
    public List<HoldUpDayDTO> getHoldUpDayByCity() {
        return holdUpDayRepository.getHoldUpDayCityWise();
    }
}

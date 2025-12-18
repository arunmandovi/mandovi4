package com.mandovi.Service;

import com.mandovi.DTO.HoldUpDayDTO;
import com.mandovi.Entity.HoldUpDay;

import java.util.List;

public interface HoldUpDayService {
    public List<String> getHoldUpDayList ();

    List<HoldUpDayDTO> getHoldUpDayByCity ();

    List<HoldUpDayDTO> getHoldUpByBranch (List<String> cities);
}

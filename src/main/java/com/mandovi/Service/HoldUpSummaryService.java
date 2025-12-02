package com.mandovi.Service;

import com.mandovi.DTO.HoldUpSummaryDTO;
import com.mandovi.Entity.HoldUpSummary;

import java.util.List;

public interface HoldUpSummaryService {

    List<HoldUpSummary> getAllHoldUp ();

    List<HoldUpSummary> getHoldUpByMonthYear (List<String> months, List<String> year);

    List<HoldUpSummaryDTO> getHoldUpSummaryCityWise (String month, String day);

    List<HoldUpSummaryDTO> getHoldUpSummaryBranchWise (String month, String day, List<String> cities);

    void deleteHodUpAll ();
}

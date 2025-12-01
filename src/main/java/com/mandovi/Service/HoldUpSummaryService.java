package com.mandovi.Service;

import com.mandovi.DTO.HoldUpSummaryDTO;

import java.util.List;

public interface HoldUpSummaryService {

    List<HoldUpSummaryDTO> getHoldUpSummaryCityWise (String month, String day);

    List<HoldUpSummaryDTO> getHoldUpSummaryBranchWise (String month, String day, List<String> cities);

}

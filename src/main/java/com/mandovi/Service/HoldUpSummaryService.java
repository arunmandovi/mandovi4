package com.mandovi.Service;

import com.mandovi.DTO.HoldUpSummaryDTO;

import java.time.LocalDate;
import java.util.List;

public interface HoldUpSummaryService {

    List<HoldUpSummaryDTO> getHoldUpSummaryCityWise (List<LocalDate> holdUpSummaryDate);

    List<HoldUpSummaryDTO> getHoldUpSummaryBranchWise (List<LocalDate> holdUpSummaryDate);
}

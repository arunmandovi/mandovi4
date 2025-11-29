package com.mandovi.Service;

import com.mandovi.DTO.HoldUpSummaryDTO;
import com.mandovi.Repository.HoldUpSummaryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class HoldUpSummaryServiceImpl implements HoldUpSummaryService {
    private final HoldUpSummaryRepository holdUpSummaryRepository;

    public HoldUpSummaryServiceImpl(HoldUpSummaryRepository holdUpSummaryRepository) {
        this.holdUpSummaryRepository = holdUpSummaryRepository;
    }

    @Override
    public List<HoldUpSummaryDTO> getHoldUpSummaryCityWise(List<LocalDate> holdUpSummaryDate) {
        return holdUpSummaryRepository.getHoldUpSummaryCityWise(holdUpSummaryDate);
    }

    @Override
    public List<HoldUpSummaryDTO> getHoldUpSummaryBranchWise(List<LocalDate> holdUpSummaryDate) {
        return holdUpSummaryRepository.getHoldUpSummaryBranchWise(holdUpSummaryDate);
    }
}

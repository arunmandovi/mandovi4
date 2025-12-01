package com.mandovi.Service;

import com.mandovi.DTO.HoldUpSummaryDTO;
import com.mandovi.Repository.HoldUpSummaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HoldUpSummaryServiceImpl implements HoldUpSummaryService {
    private final HoldUpSummaryRepository holdUpSummaryRepository;

    public HoldUpSummaryServiceImpl(HoldUpSummaryRepository holdUpSummaryRepository) {
        this.holdUpSummaryRepository = holdUpSummaryRepository;
    }

    @Override
    public List<HoldUpSummaryDTO> getHoldUpSummaryCityWise(String month, String day) {
        return holdUpSummaryRepository.getHoldUpSummaryCityWise(month, day);
    }

    @Override
    public List<HoldUpSummaryDTO> getHoldUpSummaryBranchWise(String month, String day, List<String> cities) {
        return holdUpSummaryRepository.getHoldUpSummaryBranchWise(month, day, cities);
    }
}

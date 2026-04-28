package com.mandovi.Service;

import com.mandovi.DTO.HoldUpSummaryDTO;
import com.mandovi.Entity.HoldUpSummary;
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
    public List<HoldUpSummary> getAllHoldUp() {
        return holdUpSummaryRepository.findAll();
    }

    @Override
    public List<HoldUpSummary> getHoldUpByMonthYear(List<String> months, List<String> year) {
        return holdUpSummaryRepository.getHoldUpByMonthYear(months, year);
    }

    @Override
    public List<HoldUpSummaryDTO> getHoldUpSummaryCityWise(String month, String day, List<String> years, List<String> channels) {
        return holdUpSummaryRepository.getHoldUpSummaryCityWise(month, day, years, channels);
    }

    @Override
    public List<HoldUpSummaryDTO> getHoldUpSummaryBranchWise(String month, String day, List<String> cities, List<String> years, List<String> channels) {
        return holdUpSummaryRepository.getHoldUpSummaryBranchWise(month, day, cities, years, channels);
    }

    @Override
    public void deleteHodUpAll() {
        holdUpSummaryRepository.deleteHoldUpAll();
    }


}

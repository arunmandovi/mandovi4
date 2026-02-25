package com.mandovi.Service;

import com.mandovi.DTO.*;
import com.mandovi.Entity.InsuranceDifference;
import com.mandovi.Entity.Outstanding;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OutstandingService {
    void saveOutstandingFromExcel (MultipartFile file);

    List<Outstanding> getOutstandingAll ();

    public List<Outstanding> getDifferentOutstanding (List<String> types);

    void deleteOutstandingAll ();

    public List<TotalOutstandingDTO> getTotalOutstandingBranchWise (List<String> segments);

    void deleteInsuranceDifferenceAll();

    public List<TotalOutstandingDTO> getTotalOutstandingSAWise(List<String> segments, List<String> salesMans);
    public List<TotalOutstandingDTO> getTotalOutstandingPartyWise (List<String> segments, List<String> salesMans, String party );

    public List<TotalOutstandingDTO> getCashOutstandingBranchWise (List<String> segments);
    public List<TotalOutstandingDTO> getCashOutstandingSAWise(List<String> segments, List<String> salesMans);
    public List<TotalOutstandingDTO> getCashOutstandingPartyWise (List<String> segments, List<String> salesMans, String party);

    public List<TotalOutstandingDTO> getInvoiceOutstandingBranchWise (List<String> segments);
    public List<TotalOutstandingDTO> getInvoiceOutstandingSAWise(List<String> segments, List<String> salesMans);
    public List<TotalOutstandingDTO> getInvoiceOutstandingPartyWise(List<String> segments, List<String> salesMans, String party );

    public List<TotalOutstandingDTO> getOthersOutstandingBranchWise (List<String> segments );
    public List<TotalOutstandingDTO> getOthersOutstandingSAWise(List<String> segments, List<String> salesMans);
    public List<TotalOutstandingDTO> getOthersOutstandingPartyWise (List<String> segments, List<String> salesMans, String party);

    public List<InsuranceDifference> getAllInsuranceDifference ();

    public List<IDOutstandingDTO> getIDOutstandingBranchWise (List<String> segments);
    public List<IDOutstandingDTO> getIDOutstandingSAWise (List<String> segments, List<String> insuranceParties);
    public List<IDOutstandingDTO> getIDOutstandingPartyWise (List<String> segments, List<String> insuranceParties, String party);

    public List<TotalOutstandingDTO> getCustomerCollectOutstandingBranchWise(List<String> segments );
    public List<TotalOutstandingDTO> getCustomerCollectOutstandingSAWise(List<String> segments, List<String> salesMans );
    public List<TotalOutstandingDTO> getCustomerCollectOutstandingPartyWise (List<String> segments, List<String> salesMans, String party );
}

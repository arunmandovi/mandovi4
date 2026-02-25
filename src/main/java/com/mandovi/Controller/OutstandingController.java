package com.mandovi.Controller;

import com.mandovi.DTO.*;
import com.mandovi.Entity.InsuranceDifference;
import com.mandovi.Entity.Outstanding;
import com.mandovi.Service.OutstandingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/outstanding")
public class OutstandingController {
    private final OutstandingService outstandingService;

    public OutstandingController(OutstandingService outstandingService) {
        this.outstandingService = outstandingService;
    }

    @PostMapping("/upload")
    ResponseEntity<?> saveOutstandingFromExcel(MultipartFile file){
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload valid Excel");
        }
        try {
            outstandingService.saveOutstandingFromExcel(file);
            return ResponseEntity.ok("Outstanding Excel uploaded");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/getalloutstanding")
    ResponseEntity<?> getAllOutstanding (){
        try {
            List<Outstanding> listAllOutstanding = outstandingService.getOutstandingAll();
            if (listAllOutstanding.isEmpty()){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listAllOutstanding);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/getoutstanding")
    ResponseEntity<?> getDifferentOutstanding (@RequestParam (required = false) List<String> types){
        try {
            List<Outstanding> listOutstanding = outstandingService.getDifferentOutstanding(types);
            if (listOutstanding.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listOutstanding);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(" ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/delete_all")
    ResponseEntity<?> deleteOutstandingAll (){
        try {
            outstandingService.deleteOutstandingAll();
            return ResponseEntity.ok("Outstanding deleted completely");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    //Total Outstanding API
    @GetMapping("/total_branch_outstanding")
    ResponseEntity<?> getTotalOutstandingBranchWise (
            @RequestParam (required = false) List<String> segments ){
        try {
            List<TotalOutstandingDTO> listTotalOutstanding = outstandingService.getTotalOutstandingBranchWise(segments);
            if (listTotalOutstanding.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listTotalOutstanding);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
    @GetMapping("/total_sa_outstanding")
    ResponseEntity<?> getTotalOutstandingSAWise (
            @RequestParam (required = false) List<String> segments,
            @RequestParam (required = false) List<String> salesMans ){
        try {
            List<TotalOutstandingDTO> listTotalOutstanding = outstandingService.getTotalOutstandingSAWise(segments, salesMans);
            if (listTotalOutstanding.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listTotalOutstanding);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
    @GetMapping("/total_party_outstanding")
    ResponseEntity<?> getTotalOutstandingPartyNameWise (
            @RequestParam (required = false) List<String> segments,
            @RequestParam (required = false) List<String> salesMans,
            @RequestParam (required = false) String party ){
        try {
            List<TotalOutstandingDTO> listTotalOutstanding = outstandingService.getTotalOutstandingPartyWise(segments, salesMans, party);
            if (listTotalOutstanding.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listTotalOutstanding);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    //Cash Outstanding API
    @GetMapping("/cash_branch_outstanding")
    ResponseEntity<?> getCashOutstandingBranchWise (
            @RequestParam (required = false) List<String> segments ){
        try {
            List<TotalOutstandingDTO> listCashOutstanding = outstandingService.getCashOutstandingBranchWise(segments);
            if (listCashOutstanding.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listCashOutstanding);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
    @GetMapping("/cash_sa_outstanding")
    ResponseEntity<?> getCashOutstandingSAWise (
            @RequestParam (required = false) List<String> segments,
            @RequestParam (required = false) List<String> salesMans ){
        try {
            List<TotalOutstandingDTO> listCashOutstanding = outstandingService.getCashOutstandingSAWise(segments, salesMans);
            if (listCashOutstanding.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listCashOutstanding);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
    @GetMapping("/cash_party_outstanding")
    ResponseEntity<?> getCashOutstandingPartyWise (
            @RequestParam (required = false) List<String> segments,
            @RequestParam (required = false) List<String> salesMans,
            @RequestParam (required = false) String party){
        try {
            List<TotalOutstandingDTO> listCashOutstanding = outstandingService.getCashOutstandingPartyWise(segments, salesMans, party);
            if (listCashOutstanding.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listCashOutstanding);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    //Invoice Outstanding API
    @GetMapping("/invoice_branch_outstanding")
    ResponseEntity<?> getInvoiceOutstandingBranchWise (
            @RequestParam (required = false) List<String> segments ){
        try {
            List<TotalOutstandingDTO> listInvoiceOutstanding = outstandingService.getInvoiceOutstandingBranchWise(segments);
            if (listInvoiceOutstanding.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listInvoiceOutstanding);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
    @GetMapping("/invoice_sa_outstanding")
    ResponseEntity<?> getInvoiceOutstanding (
            @RequestParam (required = false) List<String> segments,
            @RequestParam (required = false) List<String> salesMans ){
        try {
            List<TotalOutstandingDTO> listInvoiceOutstanding = outstandingService.getInvoiceOutstandingSAWise(segments, salesMans);
            if (listInvoiceOutstanding.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listInvoiceOutstanding);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
    @GetMapping("/invoice_party_outstanding")
    ResponseEntity<?> getInvoiceOutstandingPartyWise (
            @RequestParam (required = false) List<String> segments,
            @RequestParam (required = false) List<String> salesMans,
            @RequestParam (required = false) String party){
        try {
            List<TotalOutstandingDTO> listCashOutstanding = outstandingService.getInvoiceOutstandingPartyWise(segments, salesMans, party);
            if (listCashOutstanding.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listCashOutstanding);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    //Others Outstanding API
    @GetMapping("/others_branch_outstanding")
    ResponseEntity<?> getOthersOutstandingBranchWise (
            @RequestParam (required = false) List<String> segments ){
        try {
            List<TotalOutstandingDTO> listInsuranceOutstanding = outstandingService.getOthersOutstandingBranchWise(segments);
            if (listInsuranceOutstanding.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listInsuranceOutstanding);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
    @GetMapping("/others_sa_outstanding")
    ResponseEntity<?> getOthersOutstanding (
            @RequestParam (required = false) List<String> segments,
            @RequestParam (required = false) List<String> salesMans ){
        try {
            List<TotalOutstandingDTO> listInsuranceOutstanding = outstandingService.getOthersOutstandingSAWise(segments, salesMans);
            if (listInsuranceOutstanding.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listInsuranceOutstanding);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
    @GetMapping("/others_party_outstanding")
    ResponseEntity<?> getOthersOutstandingPartyWise (
            @RequestParam (required = false) List<String> segments,
            @RequestParam (required = false) List<String> salesMans,
            @RequestParam (required = false) String party){
        try {
            List<TotalOutstandingDTO> listCashOutstanding = outstandingService.getOthersOutstandingPartyWise(segments, salesMans, party);
            if (listCashOutstanding.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listCashOutstanding);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("/getallinsurancedifference")
    ResponseEntity<?> getAllInsuranceDifference (){
        try {
            List<InsuranceDifference> insuranceDifferenceRecords = outstandingService.getAllInsuranceDifference();
            if (insuranceDifferenceRecords.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(insuranceDifferenceRecords);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    //ID Outstanding API
    @GetMapping("id_branch_outstanding")
    ResponseEntity<?> getIDOutstandingBranchWise (
            @RequestParam (required = false) List<String> segments ){
        try {
            List<IDOutstandingDTO> listIDOutstandingBranchWise = outstandingService.getIDOutstandingBranchWise(segments);
            if (listIDOutstandingBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listIDOutstandingBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("id_sa_outstanding")
    ResponseEntity<?> getIDOutstandingSAWise (
            @RequestParam (required = false) List<String> segments,
            @RequestParam (required = false) List<String> insuranceParties ){
        try {
            List<IDOutstandingDTO> listIDOutstandingBranchWise = outstandingService.getIDOutstandingSAWise(segments, insuranceParties);
            if (listIDOutstandingBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listIDOutstandingBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("id_party_outstanding")
    ResponseEntity<?> getIDOutstandingSAWise (
            @RequestParam (required = false) List<String> segments,
            @RequestParam (required = false) List<String> insuranceParties,
            @RequestParam (required = false) String party){
        try {
            List<IDOutstandingDTO> listIDOutstandingBranchWise = outstandingService.getIDOutstandingPartyWise(segments, insuranceParties, party);
            if (listIDOutstandingBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listIDOutstandingBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    //Customer Collect Outstanding API
    @GetMapping("cc_branch_outstanding")
    ResponseEntity<?> getCustomerCollectOutstandingBranchWise (
            @RequestParam (required = false) List<String> segments ){
        try {
            List<TotalOutstandingDTO> listIDOutstandingBranchWise = outstandingService.getCustomerCollectOutstandingBranchWise(segments);
            if (listIDOutstandingBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listIDOutstandingBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("cc_sa_outstanding")
    ResponseEntity<?> getCustomerCollectOutstandingSAWise (
            @RequestParam (required = false) List<String> segments,
            @RequestParam (required = false) List<String> salesMans ){
        try {
            List<TotalOutstandingDTO> listIDOutstandingBranchWise = outstandingService.getCustomerCollectOutstandingSAWise(segments, salesMans);
            if (listIDOutstandingBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listIDOutstandingBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }

    @GetMapping("cc_party_outstanding")
    ResponseEntity<?> getCustomerCollectOutstandingSAWise (
            @RequestParam (required = false) List<String> segments,
            @RequestParam (required = false) List<String> salesMans,
            @RequestParam (required = false) String party){
        try {
            List<TotalOutstandingDTO> listIDOutstandingBranchWise = outstandingService.getCustomerCollectOutstandingPartyWise(segments, salesMans, party);
            if (listIDOutstandingBranchWise.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(listIDOutstandingBranchWise);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("ERROR : "+e.getMessage());
        }
    }
}

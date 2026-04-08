package com.trading.platform.controller;

import com.trading.platform.dto.ApiResponse;
import com.trading.platform.dto.SecurityDTO;
import com.trading.platform.entity.Customer;
import com.trading.platform.service.ReferenceDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reference")
@RequiredArgsConstructor
public class ReferenceDataController {

    private final ReferenceDataService referenceDataService;

    @GetMapping("/securities")
    public ResponseEntity<ApiResponse<List<SecurityDTO>>> getAllSecurities() {
        return ResponseEntity.ok(ApiResponse.ok(referenceDataService.getAllSecurities()));
    }

    @GetMapping("/securities/{symbol}")
    public ResponseEntity<ApiResponse<SecurityDTO>> getSecurity(@PathVariable String symbol) {
        return ResponseEntity.ok(ApiResponse.ok(referenceDataService.getSecurityBySymbol(symbol.toUpperCase())));
    }

    @GetMapping("/customers")
    public ResponseEntity<ApiResponse<List<Customer>>> getAllCustomers() {
        return ResponseEntity.ok(ApiResponse.ok(referenceDataService.getAllCustomers()));
    }
}

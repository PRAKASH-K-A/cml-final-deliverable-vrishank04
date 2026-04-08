package com.trading.platform.service;

import com.trading.platform.dto.SecurityDTO;
import com.trading.platform.entity.Customer;
import com.trading.platform.entity.Security;
import com.trading.platform.repository.CustomerRepository;
import com.trading.platform.repository.SecurityRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReferenceDataService {

    private final SecurityRepository securityRepository;
    private final CustomerRepository customerRepository;

    @PostConstruct
    @Transactional
    public void seedReferenceData() {
        if (securityRepository.count() == 0) {
            seedSecurities();
            log.info("Security master seeded.");
        }
        if (customerRepository.count() == 0) {
            seedCustomers();
            log.info("Customer master seeded.");
        }
    }

    private void seedSecurities() {
        List<Security> securities = List.of(
            sec("AAPL", "Apple Inc.", "NASDAQ", "Technology", "US0378331005", 185.0, 180.0, 0.01),
            sec("GOOGL", "Alphabet Inc.", "NASDAQ", "Technology", "US02079K3059", 175.0, 170.0, 0.01),
            sec("MSFT", "Microsoft Corporation", "NASDAQ", "Technology", "US5949181045", 420.0, 415.0, 0.01),
            sec("AMZN", "Amazon.com Inc.", "NASDAQ", "Consumer", "US0231351067", 190.0, 185.0, 0.01),
            sec("TSLA", "Tesla Inc.", "NASDAQ", "Automotive", "US88160R1014", 245.0, 240.0, 0.01),
            sec("META", "Meta Platforms Inc.", "NASDAQ", "Technology", "US30303M1027", 500.0, 495.0, 0.01),
            sec("NVDA", "NVIDIA Corporation", "NASDAQ", "Technology", "US67066G1040", 875.0, 870.0, 0.01),
            sec("JPM", "JPMorgan Chase & Co.", "NYSE", "Financial", "US46625H1005", 195.0, 190.0, 0.01),
            sec("GS", "Goldman Sachs Group Inc.", "NYSE", "Financial", "US38141G1040", 420.0, 415.0, 0.01),
            sec("IBM", "International Business Machines", "NYSE", "Technology", "US4592001014", 165.0, 160.0, 0.01)
        );
        securityRepository.saveAll(securities);
    }

    private void seedCustomers() {
        List<Customer> customers = List.of(
            cust("CLT-001", "Horizon Capital", "horizon@example.com", "INSTITUTIONAL"),
            cust("CLT-002", "Apex Trading LLC", "apex@example.com", "BROKER"),
            cust("CLT-003", "John Smith", "jsmith@example.com", "RETAIL"),
            cust("CLT-004", "Global Funds Corp", "gf@example.com", "INSTITUTIONAL"),
            cust("CLT-005", "Jane Doe", "jdoe@example.com", "RETAIL")
        );
        customerRepository.saveAll(customers);
    }

    private Security sec(String sym, String name, String exch, String sector, String isin,
                         double last, double strike, double tick) {
        return Security.builder()
                .symbol(sym).companyName(name).exchange(exch).sector(sector).isin(isin)
                .lastPrice(BigDecimal.valueOf(last)).strikePrice(BigDecimal.valueOf(strike))
                .bidPrice(BigDecimal.valueOf(last - 0.1)).askPrice(BigDecimal.valueOf(last + 0.1))
                .closePrice(BigDecimal.valueOf(last)).priceTick(BigDecimal.valueOf(tick))
                .minOrderQty(BigDecimal.ONE).maxOrderQty(BigDecimal.valueOf(100000))
                .lotSize(BigDecimal.ONE).securityType(Security.SecurityType.EQUITY).isActive(true)
                .build();
    }

    private Customer cust(String id, String name, String email, String type) {
        return Customer.builder()
                .clientId(id).name(name).email(email)
                .accountType(type).status("ACTIVE").build();
    }

    public List<SecurityDTO> getAllSecurities() {
        return securityRepository.findByIsActiveTrue()
                .stream().map(SecurityDTO::fromEntity).collect(Collectors.toList());
    }

    public SecurityDTO getSecurityBySymbol(String symbol) {
        return securityRepository.findBySymbol(symbol)
                .map(SecurityDTO::fromEntity)
                .orElseThrow(() -> new RuntimeException("Security not found: " + symbol));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}

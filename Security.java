package com.trading.platform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "securities", indexes = {
    @Index(name = "idx_security_symbol", columnList = "symbol", unique = true)
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Security {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String symbol;

    @Column(nullable = false)
    private String companyName;

    private String exchange;
    private String sector;
    private String isin;

    private BigDecimal lastPrice;
    private BigDecimal bidPrice;
    private BigDecimal askPrice;
    private BigDecimal closePrice;
    private BigDecimal strikePrice;

    private BigDecimal minOrderQty;
    private BigDecimal maxOrderQty;
    private BigDecimal priceTick;
    private BigDecimal lotSize;

    @Enumerated(EnumType.STRING)
    private SecurityType securityType;

    private Boolean isActive;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (isActive == null) isActive = true;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum SecurityType { EQUITY, OPTION, FUTURE, ETF, BOND }
}

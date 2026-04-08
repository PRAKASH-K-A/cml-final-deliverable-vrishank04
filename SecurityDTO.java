package com.trading.platform.dto;

import com.trading.platform.entity.Security;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class SecurityDTO {
    private Long id;
    private String symbol;
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
    private Security.SecurityType securityType;
    private Boolean isActive;

    public static SecurityDTO fromEntity(Security s) {
        return SecurityDTO.builder()
                .id(s.getId()).symbol(s.getSymbol()).companyName(s.getCompanyName())
                .exchange(s.getExchange()).sector(s.getSector()).isin(s.getIsin())
                .lastPrice(s.getLastPrice()).bidPrice(s.getBidPrice()).askPrice(s.getAskPrice())
                .closePrice(s.getClosePrice()).strikePrice(s.getStrikePrice())
                .minOrderQty(s.getMinOrderQty()).maxOrderQty(s.getMaxOrderQty())
                .priceTick(s.getPriceTick()).lotSize(s.getLotSize())
                .securityType(s.getSecurityType()).isActive(s.getIsActive())
                .build();
    }
}

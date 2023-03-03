package com.obamax.payload;

import lombok.Data;

@Data
public class OrderRequest {
    private Long productId;
    private Integer quantity;
    private String customerName;
    private String customerPhoneNumber;
}

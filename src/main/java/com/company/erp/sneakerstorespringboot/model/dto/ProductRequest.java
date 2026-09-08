package com.company.erp.sneakerstorespringboot.model.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank(message = "商品名稱不能為空")
    @Size(max = 120, message = "商品名稱不能超過 120 個字元")
    private String name;

    @NotBlank(message = "Slug 不能為空")
    @Size(max = 160, message = "Slug 不能超過 160 個字元")
    @Pattern(
            regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$",
            message = "Slug 只能包含小寫英文、數字及連字號"
    )
    private String slug;

    @Size(max = 2000, message = "商品介紹不能超過 2000 個字元")
    private String description;

    @NotNull(message = "商品價格不能為空")
    @DecimalMin(
            value = "0.01",
            message = "商品價格必須大於 0"
    )
    private BigDecimal price;

    @Size(max = 500, message = "圖片網址不能超過 500 個字元")
    private String imageUrl;

    private boolean active = true;

    @NotNull(message = "請選擇品牌")
    private Long brandId;

    @NotNull(message = "請選擇分類")
    private Long categoryId;
}
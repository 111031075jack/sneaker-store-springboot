package com.company.erp.sneakerstorespringboot.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BrandRequest {

    @NotBlank(message = "名稱不能為空")
    @Size(max = 80, message = "名稱不能超過80個字元")
    private String name;

    @NotBlank(message = "Slug 不能為空")
    @Size(max = 100, message = "Slug 不能超過100個字元")
    @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$", message = "Slug 只能包含小寫英文, 數字與連號")
    private String slug;

    private boolean active = true;

}

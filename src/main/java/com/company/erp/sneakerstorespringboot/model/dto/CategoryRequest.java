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
public class CategoryRequest {

    @NotBlank(message = "分類名稱不能為空")
    @Size(max = 50, message = "分類名稱不能超過50個字元")
    private String name;

    @NotBlank(message = "Slug 不能為空")
    @Size(max = 80, message = "Slug 不能超過80個字元")
    @Pattern(
            regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$",
            message = "Slug 只能包含小寫英文、數字及連字號"
    )
    private String slug;

    private boolean active = true;

}

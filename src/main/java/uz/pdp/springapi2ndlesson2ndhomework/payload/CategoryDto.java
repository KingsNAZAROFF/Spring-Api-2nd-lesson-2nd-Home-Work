package uz.pdp.springapi2ndlesson2ndhomework.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Integer id;
    @NotNull(message = "Category name bo'sh bo'lmasligi kerak")
    private String name;
    private Integer parentCategoryId;

    public CategoryDto(String name, Integer parentCategoryId) {
        this.name = name;
        this.parentCategoryId = parentCategoryId;
    }
}

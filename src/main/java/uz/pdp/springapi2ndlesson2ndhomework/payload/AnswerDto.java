package uz.pdp.springapi2ndlesson2ndhomework.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
    @NotNull(message = "Answer bo'sh bo'lmasligi kerak")
    private String answer;
}

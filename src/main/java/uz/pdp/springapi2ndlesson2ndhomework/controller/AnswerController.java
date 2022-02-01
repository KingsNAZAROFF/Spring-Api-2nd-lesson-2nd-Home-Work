package uz.pdp.springapi2ndlesson2ndhomework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springapi2ndlesson2ndhomework.payload.AnswerDto;
import uz.pdp.springapi2ndlesson2ndhomework.payload.ApiResponse;
import uz.pdp.springapi2ndlesson2ndhomework.service.AnswerService;

import javax.validation.Valid;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    @Autowired
    AnswerService answerService;

    @PostMapping("/{questionId}")
    public ResponseEntity<ApiResponse> check(@PathVariable Integer questionId, @Valid @RequestBody AnswerDto answerDto){
        ApiResponse response = answerService.checkAnswer(questionId, answerDto);
        return ResponseEntity.status(response.isSuccess()? HttpStatus.OK:HttpStatus.BAD_REQUEST).body(response);

    }

}

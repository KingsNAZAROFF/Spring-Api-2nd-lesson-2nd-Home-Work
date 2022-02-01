package uz.pdp.springapi2ndlesson2ndhomework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springapi2ndlesson2ndhomework.entity.Question;
import uz.pdp.springapi2ndlesson2ndhomework.payload.ApiResponse;
import uz.pdp.springapi2ndlesson2ndhomework.payload.QuestionDto;
import uz.pdp.springapi2ndlesson2ndhomework.service.QuestionService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @GetMapping
    public ResponseEntity<Page<Question>> getAll(@RequestParam int page){
        return ResponseEntity.ok(questionService.getAll(page));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Question> getOne(@PathVariable Integer id){
        Question question = questionService.getOne(id);
        return ResponseEntity.status(question!=null ? 200:409).body(question);
    }
    @PostMapping
    public ResponseEntity<ApiResponse> addQuestion(@Valid @RequestBody QuestionDto questionDto){
        ApiResponse response = questionService.addQuestion(questionDto);
        return ResponseEntity.status(response.isSuccess()?200:404).body(response);
    }
    @GetMapping("/byCategory/{categoryId}")
    public ResponseEntity<List<Question>> getByCategoryId(@PathVariable Integer categoryId){
        List<Question> questions = questionService.getByCategoryId(categoryId);
        return ResponseEntity.status(questions!=null?200:409).body(questions);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editQuestion(@PathVariable Integer id,@Valid @RequestBody QuestionDto questionDto){
        ApiResponse response = questionService.editQuestion(id, questionDto);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteQuestion(@PathVariable Integer id){
        ApiResponse response = questionService.deleteQuestion(id);
        return ResponseEntity.status(response.isSuccess()?200:404).body(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


    }

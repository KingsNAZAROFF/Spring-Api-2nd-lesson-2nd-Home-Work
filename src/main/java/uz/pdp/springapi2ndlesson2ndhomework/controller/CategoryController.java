package uz.pdp.springapi2ndlesson2ndhomework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springapi2ndlesson2ndhomework.entity.Category;
import uz.pdp.springapi2ndlesson2ndhomework.payload.ApiResponse;
import uz.pdp.springapi2ndlesson2ndhomework.payload.CategoryDto;
import uz.pdp.springapi2ndlesson2ndhomework.service.CategoryService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getALll(){
        List<Category> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getOne(@PathVariable Integer id){
        Category category = categoryService.getOne(id);
        return ResponseEntity.status(category!=null?200:409).body(category);
    }
    @PostMapping
    public ResponseEntity<ApiResponse> addCategory(@Valid  @RequestBody CategoryDto categoryDto){
        ApiResponse response = categoryService.addCategoryService(categoryDto);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> editCategory(@PathVariable Integer id,@Valid @RequestBody CategoryDto categoryDto){
        ApiResponse response = categoryService.editCategory(id, categoryDto);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer id){
        ApiResponse response = categoryService.deleteCategory(id);
        return ResponseEntity.status(response.isSuccess()?200:409).body(response);
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

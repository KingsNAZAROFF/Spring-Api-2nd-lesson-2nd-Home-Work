package uz.pdp.springapi2ndlesson2ndhomework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springapi2ndlesson2ndhomework.entity.Category;
import uz.pdp.springapi2ndlesson2ndhomework.payload.ApiResponse;
import uz.pdp.springapi2ndlesson2ndhomework.payload.CategoryDto;
import uz.pdp.springapi2ndlesson2ndhomework.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse addCategoryService(CategoryDto categoryDto){
        boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        if(existsByName) {
            return new ApiResponse("Bunnday Categoriya mavjud", false);
        }
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId()!=null) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalCategory.isPresent()){
                return new ApiResponse("Bunday ota kategoriya mavjud emas",false);
            }
            category.setParentCategory(optionalCategory.get());
        }
        categoryRepository.save(category);
        return new ApiResponse("Muvaffaqiyatli saqlandi",true);

    }

    public List<Category> getAllCategories(){
       return categoryRepository.findAll();
    }
    public Category getOne(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }


    public ApiResponse editCategory(Integer id,CategoryDto categoryDto) {
        boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        if (existsByName) {
            return new ApiResponse("Bunnday Categoriya mavjud", false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) {
            return new ApiResponse("Bunday kategoriya topilmadi", false);
        }
        Category editingCategory = optionalCategory.get();

        editingCategory.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalCategory1 = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalCategory1.isPresent()) {
                return new ApiResponse("Bunday ota kategoriya mavjud emas", false);
            }
            editingCategory.setParentCategory(optionalCategory1.get());
        }
        categoryRepository.save(editingCategory);
        return new ApiResponse("Muvaffaqiyatli saqlandi", true);
    }

    public ApiResponse deleteCategory(Integer id){
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isPresent()){
            categoryRepository.deleteById(id);
            return new ApiResponse("Kategoriya o'chirildi",true);
        }
        return new ApiResponse("Bunday kategoriya mvjud emas",false);
    }
}

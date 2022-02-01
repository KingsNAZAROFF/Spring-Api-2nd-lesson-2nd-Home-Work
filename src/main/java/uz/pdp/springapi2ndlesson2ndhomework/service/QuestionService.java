package uz.pdp.springapi2ndlesson2ndhomework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.springapi2ndlesson2ndhomework.entity.Category;
import uz.pdp.springapi2ndlesson2ndhomework.entity.Question;
import uz.pdp.springapi2ndlesson2ndhomework.payload.ApiResponse;
import uz.pdp.springapi2ndlesson2ndhomework.payload.QuestionDto;
import uz.pdp.springapi2ndlesson2ndhomework.repository.CategoryRepository;
import uz.pdp.springapi2ndlesson2ndhomework.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    QuestionRepository questionRepository;

    public ApiResponse addQuestion(QuestionDto questionDto){
        Optional<Category> optionalCategory = categoryRepository.findById(questionDto.getCategoryId());
        if (optionalCategory.isEmpty()){
            return new ApiResponse("Bunday category mavjud emas",false);
        }
        Question question = new Question();
        question.setQuestion(question.getQuestion());
        question.setAnswer(question.getAnswer());
        question.setCategory(optionalCategory.get());
        questionRepository.save(question);
        return new ApiResponse("Question Db ga saqlandi",true);
    }

    public ApiResponse editQuestion(Integer id,QuestionDto questionDto){
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isEmpty()){
            return  new ApiResponse("Bunday question topilmadi",false);
        }
        Optional<Category> optionalCategory = categoryRepository.findById(questionDto.getCategoryId());
        if (optionalCategory.isEmpty()){
            return new ApiResponse("Bunday category mavjud emas",false);
        }
        Question editingQuestion = optionalQuestion.get();
        editingQuestion.setQuestion(questionDto.getQuestion());
        editingQuestion.setAnswer(questionDto.getAnswer());
        editingQuestion.setCategory(optionalCategory.get());
        questionRepository.save(editingQuestion);
        return new ApiResponse("Question ma'lumotlari o'zgartirildi",true);
    }
    public Page<Question> getAll(int page){
        Pageable pageable = PageRequest.of(page,10);
        return questionRepository.findAll(pageable);
    }
    public List<Question> getByCategoryId(Integer categoryId){
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()){
            return null;
        }
      return questionRepository.findAllByCategoryId(categoryId);
    }
    public Question getOne(Integer id){
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        return optionalQuestion.orElse(null);
    }

    public ApiResponse deleteQuestion(Integer id){
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (optionalQuestion.isEmpty()){
            return new ApiResponse("Bunday Question mavjud emas",false);
        }
        questionRepository.deleteById(id);
        return new ApiResponse("Question DB dan o'chirildi",true);
    }
}
//.replaceAll("//s","")
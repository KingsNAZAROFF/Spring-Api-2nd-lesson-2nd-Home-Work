package uz.pdp.springapi2ndlesson2ndhomework.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springapi2ndlesson2ndhomework.entity.Question;
import uz.pdp.springapi2ndlesson2ndhomework.payload.AnswerDto;
import uz.pdp.springapi2ndlesson2ndhomework.payload.ApiResponse;
import uz.pdp.springapi2ndlesson2ndhomework.repository.QuestionRepository;

import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    QuestionRepository questionRepository;

    public ApiResponse checkAnswer(Integer questionId, AnswerDto answerDto){
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isEmpty()){
            return new ApiResponse("Bunday Question mavjud emas",false);
        }
        Question question = optionalQuestion.get();
        if (question.getAnswer().replaceAll("//s","").equals(answerDto.getAnswer().replaceAll("//s",""))){
            return new ApiResponse("Javobingiz to'g'ri",true);
        }
        return new ApiResponse("Javobingiz xato",false);

    }
}

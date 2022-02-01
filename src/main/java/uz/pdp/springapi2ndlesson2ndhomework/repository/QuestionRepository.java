package uz.pdp.springapi2ndlesson2ndhomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.springapi2ndlesson2ndhomework.entity.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {

    List<Question> findAllByCategoryId(Integer category_id);
}

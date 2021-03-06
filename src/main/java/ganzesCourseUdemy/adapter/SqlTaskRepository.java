package ganzesCourseUdemy.adapter;

import ganzesCourseUdemy.model.Task;
import ganzesCourseUdemy.model.TaskRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SqlTaskRepository extends TaskRepository, JpaRepository <Task, Integer> {
    @Override
    @Query(nativeQuery = true, value = "select count(*) > 0 from TASKS where id=:id")
    boolean existsById(@Param ("id") Integer integer);
    @Override
    boolean existsByDoneIsFalseAndGroupId(Integer groupId);
}
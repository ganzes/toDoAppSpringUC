package ganzesCourseUdemy.adapter;

import ganzesCourseUdemy.model.TaskGroup;
import ganzesCourseUdemy.model.TaskGroupRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqlTaskGroupRepository extends TaskGroupRepository, JpaRepository <TaskGroup, Integer> {

    @Override
    @Query("from TaskGroup g join fetch g.tasks")
    List<TaskGroup> findAll();

    @Override
    boolean existsByDoneIsFalseAndProjectsId(Integer projectId);
}
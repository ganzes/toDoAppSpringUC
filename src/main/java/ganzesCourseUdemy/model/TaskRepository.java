package ganzesCourseUdemy.model;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll ();

    Page <Task> findAll (Pageable page);

    Optional <Task> findById (Integer id);

    Task save (Task entity);

    boolean existsById(Integer integer);

    List<Task> findByDone(boolean done);

    boolean existsByDoneIsFalseAndGroupId(Integer groupId);

}
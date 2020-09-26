package ganzesCourseUdemy.adapter;

import ganzesCourseUdemy.model.ProjectRepository;
import ganzesCourseUdemy.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqlProjectRepository extends ProjectRepository, JpaRepository <Projects, Integer> {

    @Override
    @Query("from Projects g join fetch g.projectSteps")
    List<Projects> findAll();
}

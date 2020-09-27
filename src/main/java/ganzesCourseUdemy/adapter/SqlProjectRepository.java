package ganzesCourseUdemy.adapter;

import ganzesCourseUdemy.model.ProjectRepository;
import ganzesCourseUdemy.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SqlProjectRepository extends ProjectRepository, JpaRepository <Project, Integer> {

    @Override
    @Query("from Project g join fetch g.projectSteps")
    List<Project> findAll();
}

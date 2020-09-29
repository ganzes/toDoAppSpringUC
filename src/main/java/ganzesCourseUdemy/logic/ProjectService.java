package ganzesCourseUdemy.logic;

import ganzesCourseUdemy.model.Project;
import ganzesCourseUdemy.model.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private ProjectRepository projectRepository;

    public List<Project> findAll(){
        return projectRepository.findAll();
    };

    public Project saveProject(Project project){
        return projectRepository.save(project);
    }
}
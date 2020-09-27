package ganzesCourseUdemy.logic;

import ganzesCourseUdemy.model.TaskGroup;
import ganzesCourseUdemy.model.TaskGroupRepository;
import ganzesCourseUdemy.model.TaskRepository;
import ganzesCourseUdemy.model.projection.GroupReadModel;
import ganzesCourseUdemy.model.projection.GroupWriteModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskGroupService {
    private TaskGroupRepository taskGroupRepository;
    private TaskRepository taskRepository;

    public TaskGroupService(TaskGroupRepository taskGroupRepository, TaskRepository taskRepository) {
        this.taskGroupRepository = taskGroupRepository;
        this.taskRepository = taskRepository;
    }

    public GroupReadModel createGroup(GroupWriteModel source){
           TaskGroup result = taskGroupRepository.save(source.toGroup());
           return new GroupReadModel(result);
    }

    public List<GroupReadModel> readAll(){
        return taskGroupRepository.findAll()
                .stream()
                .map(GroupReadModel::new)
                .collect(Collectors.toList());
    }

    public void toggleGroup(int groupId){
        if (taskRepository.existsByDoneIsFalseAndGroupId(groupId)) {
            throw new IllegalStateException("Group has undone tasks. Done all the tasks first ");
        }

        TaskGroup result = taskGroupRepository.findById(groupId).orElseThrow(() ->
                new IllegalArgumentException("TaskGroup with given id not found"));
        result.setDone(result.isDone());
    }
}
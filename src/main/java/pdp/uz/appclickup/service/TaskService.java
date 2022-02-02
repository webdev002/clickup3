package pdp.uz.appclickup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.appclickup.entity.Task;
import pdp.uz.appclickup.entity.TaskAttachment;
import pdp.uz.appclickup.payload.ApiResponse;
import pdp.uz.appclickup.payload.TaskDto;
import pdp.uz.appclickup.repository.*;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    PriorityRepository priorityRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    TaskAttachmentRepository taskAttachmentRepository;

    public List<Task> getTask() {
        List<Task> taskList = taskRepository.findAll();
        return taskList;
    }

    public ApiResponse addTask(TaskDto taskDto) {
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setParentTask(null);
        task.setDescription(taskDto.getDescription());
        task.setCategory(categoryRepository.getById(taskDto.getCategory()));
        task.setPriority(priorityRepository.getById(taskDto.getPriority()));
        task.setStartedDate(taskDto.getStartedDate());
        task.setDueDate(taskDto.getDueDate());
        task.setStatus(statusRepository.getById(taskDto.getStatus()));
        task.setEstimateTime(taskDto.getEstimateTime());
        taskRepository.save(task);

        //task_attachment
        TaskAttachment taskAttachment = new TaskAttachment(
                task, attachmentRepository.getById(1),true
        );

        return new ApiResponse("Task saqlandi",true);
    }

    public ApiResponse editTask(Integer id, TaskDto taskDto) {
        boolean exists = taskRepository.existsByNameAndIdNot(taskDto.getName(), id);
        if (exists){
            return new ApiResponse("Bunday task mavjud",false);
        }
        Task task = taskRepository.getById(id);
        task.setName(taskDto.getName());
        task.setParentTask(null);
        task.setDescription(taskDto.getDescription());
        task.setCategory(categoryRepository.getById(taskDto.getCategory()));
        task.setPriority(priorityRepository.getById(taskDto.getPriority()));
        task.setStartedDate(taskDto.getStartedDate());
        task.setDueDate(taskDto.getDueDate());
        task.setStatus(statusRepository.getById(taskDto.getStatus()));
        task.setEstimateTime(taskDto.getEstimateTime());
        taskRepository.save(task);
        return new ApiResponse("Task tahrirlandi",true);
    }

    public ApiResponse deleteTask(Integer id) {
        taskRepository.deleteById(id);
        return new ApiResponse("Task ochirildi",true);
    }

    public ApiResponse deleteTaskAttachment(Integer id) {
        taskAttachmentRepository.deleteById(id);
        return new ApiResponse("TaskAttachment ochirildi",true);
    }
}

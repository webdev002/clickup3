package pdp.uz.appclickup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pdp.uz.appclickup.entity.Status;
import pdp.uz.appclickup.entity.User;
import pdp.uz.appclickup.entity.enums.StatusType;
import pdp.uz.appclickup.payload.ApiResponse;
import pdp.uz.appclickup.payload.StatusDTO;
import pdp.uz.appclickup.repository.CategoryRepository;
import pdp.uz.appclickup.repository.ProjectRepository;
import pdp.uz.appclickup.repository.SpaceRepository;
import pdp.uz.appclickup.repository.StatusRepository;

import java.util.List;

@Service
public class StatusService {
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProjectRepository projectRepository;


    public List<Status> getStatus() {
        List<Status> statusList = statusRepository.findAll();
        return statusList;
    }

    public ApiResponse addStatus(StatusDTO statusDTO, User user) {
        boolean exists = statusRepository.existsByNameAndUserId(statusDTO.getName(), user.getId());
        if (exists){
            return new ApiResponse("Bu userda bunday nomlik status mavjud",false);
        }
        Status status = new Status();
        status.setName(statusDTO.getName());
        status.setColor(statusDTO.getColor());
        status.setStatusType(StatusType.OPEN);
        status.setCategory(categoryRepository.getById(statusDTO.getCategory()));
        status.setProject(projectRepository.getById(statusDTO.getCategory()));
        status.setUser(user);
        statusRepository.save(status);
        return new ApiResponse("Status saqlandi",true);
    }

    public ApiResponse editStatus(Integer id, StatusDTO statusDTO, User user) {
        boolean exists = statusRepository.existsByNameAndIdNot(statusDTO.getName(), id);
        if (exists){
            return new ApiResponse("Bunday status mavjud",false);
        }
        Status status = statusRepository.getById(id);
        status.setName(statusDTO.getName());
        status.setColor(statusDTO.getColor());
        status.setStatusType(StatusType.OPEN);
        status.setCategory(categoryRepository.getById(statusDTO.getCategory()));
        status.setProject(projectRepository.getById(statusDTO.getCategory()));
        status.setUser(user);
        statusRepository.save(status);
        return new ApiResponse("Status tahrirlandi",true);
    }

    public ApiResponse deleteStatus(Integer id) {
        statusRepository.deleteById(id);
        return new ApiResponse("Status o'chirildi",true);
    }
}

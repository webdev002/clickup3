package pdp.uz.appclickup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.appclickup.entity.Priority;
import pdp.uz.appclickup.payload.ApiResponse;
import pdp.uz.appclickup.payload.PriorityDTO;
import pdp.uz.appclickup.repository.IconRepository;
import pdp.uz.appclickup.repository.PriorityRepository;

@Service
public class PriorityService {
    @Autowired
    PriorityRepository priorityRepository;

    @Autowired
    IconRepository iconRepository;

    public ApiResponse addPriority(PriorityDTO priorityDTO) {
        Priority priority = new Priority();
        priority.setName(priorityDTO.getName());
        priority.setIcons(iconRepository.getById(priorityDTO.getIcons()));
        priorityRepository.save(priority);
        return new ApiResponse("Priority Saqlandi",true);
    }
}

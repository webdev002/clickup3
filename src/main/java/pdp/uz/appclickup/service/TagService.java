package pdp.uz.appclickup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.appclickup.entity.Tag;
import pdp.uz.appclickup.entity.TaskTag;
import pdp.uz.appclickup.payload.ApiResponse;
import pdp.uz.appclickup.payload.TagDTO;
import pdp.uz.appclickup.repository.TagRepository;
import pdp.uz.appclickup.repository.TaskRepository;
import pdp.uz.appclickup.repository.TaskTagRepository;
import pdp.uz.appclickup.repository.WorkSpaceRepository;

@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;
    @Autowired
    WorkSpaceRepository workSpaceRepository;
    @Autowired
    TaskTagRepository taskTagRepository;
    @Autowired
    TaskRepository taskRepository;
    public ApiResponse addTag(TagDTO tagDTO) {
        Tag tag = tagRepository.save(new Tag(tagDTO.getName(), tagDTO.getColor(), workSpaceRepository.getById(tagDTO.getWorkSpace())));
        taskTagRepository.save(new TaskTag(taskRepository.getById(tagDTO.getId()),tag));
        return new ApiResponse("Tag saqlandi",true);
    }

    public ApiResponse editTag(Integer id, TagDTO tagDTO) {
        Tag tag = tagRepository.getById(id);
        tag.setName(tagDTO.getName());
        tag.setColor(tagDTO.getColor());
        tag.setWorkSpace(workSpaceRepository.getById(tagDTO.getWorkSpace()));
        tagRepository.save(tag);
        return new ApiResponse("Tahrirlandi",true);
    }

    public ApiResponse deleteTag(Integer id) {
        tagRepository.deleteById(id);
        return new ApiResponse("Tag ochirildi",true);
    }

    public ApiResponse deleteTaskTag(Integer id) {
        taskTagRepository.deleteById(id);
        return new ApiResponse("TaskTag ochirildi",true);
    }
}

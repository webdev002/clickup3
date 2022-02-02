package pdp.uz.appclickup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.appclickup.entity.Category;
import pdp.uz.appclickup.entity.CategoryUser;
import pdp.uz.appclickup.entity.User;
import pdp.uz.appclickup.entity.enums.TaskPermission;
import pdp.uz.appclickup.payload.ApiResponse;
import pdp.uz.appclickup.payload.CategoryDTO;
import pdp.uz.appclickup.repository.CategoryRepository;
import pdp.uz.appclickup.repository.ProjectRepository;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProjectRepository projectRepository;

    public ApiResponse addCategory(CategoryDTO categoryDTO, User user) {
        //category
        Category category = new Category();
        category.setName(categoryDTO.getName());
        category.setColor(categoryDTO.getColor());
        category.setAccessType(categoryDTO.getAccessType());
        category.setProject(projectRepository.getById(categoryDTO.getProject()));
        category.setArchived(categoryDTO.getArchived());
        categoryRepository.save(category);

        //category_user
        CategoryUser categoryUser = new CategoryUser(
                category,user, TaskPermission.TASK_USER
        );

        return new ApiResponse("Category va CategoryUser saqlandi!",true);


    }

    public ApiResponse editCategory(Integer id, CategoryDTO categoryDTO) {
        boolean existsByNameAndIdNot = categoryRepository.existsByNameAndIdNot(categoryDTO.getName(), id);
        if (existsByNameAndIdNot){
            return new ApiResponse("Bunday category mavjud",false);
        }
        Category category = categoryRepository.getById(id);
        category.setName(categoryDTO.getName());
        category.setColor(categoryDTO.getColor());
        category.setAccessType(categoryDTO.getAccessType());
        category.setProject(projectRepository.getById(categoryDTO.getProject()));
        category.setArchived(categoryDTO.getArchived());
        categoryRepository.save(category);
        return new ApiResponse("Category tahrirlandi",true);
    }

    public ApiResponse deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
        return new ApiResponse("Category o'chirildi",true);
    }
}

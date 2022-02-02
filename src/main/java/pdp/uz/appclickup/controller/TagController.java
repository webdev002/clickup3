package pdp.uz.appclickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.appclickup.payload.ApiResponse;
import pdp.uz.appclickup.payload.TagDTO;
import pdp.uz.appclickup.service.TagService;

@RestController
@RequestMapping("/api/tag")
public class TagController {
    @Autowired
    TagService tagService;

    @PostMapping
    public HttpEntity<?> addTag(@RequestBody TagDTO tagDTO){
        ApiResponse apiResponse = tagService.addTag(tagDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editTag(@PathVariable Integer id,@RequestBody TagDTO tagDTO){
        ApiResponse apiResponse = tagService.editTag(id, tagDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteTag(@PathVariable Integer id){
        ApiResponse apiResponse = tagService.deleteTag(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @DeleteMapping("/taskTag/{id}")
    public HttpEntity<?> deleteTaskTag(@PathVariable Integer id){
        ApiResponse apiResponse = tagService.deleteTaskTag(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}

package pdp.uz.appclickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pdp.uz.appclickup.payload.ApiResponse;
import pdp.uz.appclickup.payload.PriorityDTO;
import pdp.uz.appclickup.service.PriorityService;

@RestController
@RequestMapping("/api/priority")
public class PriorityController {
    @Autowired
    PriorityService priorityService;

    @PostMapping
    public HttpEntity<?> addPriority(@RequestBody PriorityDTO priorityDTO){
        ApiResponse apiResponse = priorityService.addPriority(priorityDTO);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}

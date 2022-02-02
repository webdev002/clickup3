package pdp.uz.appclickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.appclickup.entity.Status;
import pdp.uz.appclickup.entity.User;
import pdp.uz.appclickup.payload.ApiResponse;
import pdp.uz.appclickup.payload.StatusDTO;
import pdp.uz.appclickup.security.CurrentUser;
import pdp.uz.appclickup.service.StatusService;

import java.util.List;

@RestController
@RequestMapping("/api/status")
public class StatusController {
    @Autowired
    StatusService statusService;

    @GetMapping
    public HttpEntity<?> getStatus(){
        List<Status> status = statusService.getStatus();
        return ResponseEntity.ok(status);
    }

    @PostMapping
    public HttpEntity<?> addStatus(@RequestBody StatusDTO statusDTO, @CurrentUser User user){
        ApiResponse apiResponse = statusService.addStatus(statusDTO, user);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editStatus(@PathVariable Integer id,@RequestBody StatusDTO statusDTO,@CurrentUser User user){
        ApiResponse apiResponse = statusService.editStatus(id, statusDTO, user);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteStatus(@PathVariable Integer id){
        ApiResponse apiResponse = statusService.deleteStatus(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}

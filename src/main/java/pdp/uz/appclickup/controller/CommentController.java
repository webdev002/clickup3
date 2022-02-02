package pdp.uz.appclickup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.appclickup.entity.User;
import pdp.uz.appclickup.payload.ApiResponse;
import pdp.uz.appclickup.payload.CommentDto;
import pdp.uz.appclickup.security.CurrentUser;
import pdp.uz.appclickup.service.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping
    public HttpEntity<?> addComment(@RequestBody CommentDto commentDto, @CurrentUser User user){
        ApiResponse apiResponse = commentService.addComment(commentDto, user);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> editComment(@PathVariable Integer id,@RequestBody CommentDto commentDto,@CurrentUser User user){
        ApiResponse apiResponse = commentService.editComment(id, commentDto, user);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteComment(@PathVariable Integer id){
        ApiResponse apiResponse = commentService.deleteComment(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }
}

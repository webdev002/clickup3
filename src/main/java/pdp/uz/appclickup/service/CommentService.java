package pdp.uz.appclickup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.appclickup.entity.Comment;
import pdp.uz.appclickup.entity.User;
import pdp.uz.appclickup.payload.ApiResponse;
import pdp.uz.appclickup.payload.CommentDto;
import pdp.uz.appclickup.repository.CommentRepository;
import pdp.uz.appclickup.repository.TaskRepository;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    TaskRepository taskRepository;

    public ApiResponse addComment(CommentDto commentDto, User user) {
        commentRepository.save(new Comment(commentDto.getName(),commentDto.getText(), taskRepository.getById(commentDto.getTask()),user));
        return new ApiResponse("Comment saqlandi",true);
    }

    public ApiResponse editComment(Integer id, CommentDto commentDto, User user) {
        Comment comment = commentRepository.getById(id);
        comment.setName(commentDto.getName());
        comment.setText(commentDto.getText());
        comment.setTask(taskRepository.getById(commentDto.getTask()));
        comment.setUser(user);
        commentRepository.save(comment);
        return new ApiResponse("Comment Tahrirlandi",true);
    }

    public ApiResponse deleteComment(Integer id) {
        commentRepository.deleteById(id);
        return new ApiResponse("Comment O'chirildi",true);
    }
}

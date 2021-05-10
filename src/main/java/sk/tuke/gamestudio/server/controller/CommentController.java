package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.service.CommentService;


@Controller
@RequestMapping("/comment")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class CommentController {

    @Autowired
    protected CommentService commentService;

    @RequestMapping
    public String Comment(){

        return "comment";
    }
}

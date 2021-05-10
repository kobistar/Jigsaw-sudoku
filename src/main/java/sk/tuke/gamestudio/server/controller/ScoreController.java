package sk.tuke.gamestudio.server.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.service.ScoreService;

@Controller
@RequestMapping("/score")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class ScoreController {

    @Autowired
    protected ScoreService scoreService;

    @RequestMapping
    public String Score(){

        return "score";
    }
}

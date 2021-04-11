package sk.tuke.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import sk.tuke.gamestudio.game.jigsaw.consoleui.ConsoleUi;
import sk.tuke.gamestudio.game.jigsaw.core.GameField;
import sk.tuke.gamestudio.service.*;

@SpringBootApplication
@Configuration

@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,
        pattern = "sk.tuke.gamestudio.server.*"))

public class SpringClient {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);
    }

    @Bean
    public CommandLineRunner runner(ConsoleUi ui) { return args -> ui.play(); }

    @Bean
    public ConsoleUi consoleUI(GameField gameField) { return new ConsoleUi(gameField); }

    @Bean
    public GameField gameField() { return new GameField(); }

    @Bean
    public ScoreService scoreService() {
        return new ScoreServiceRestClient();
        //return new ScoreServiceJPA();
        //return new ScoreServiceJDBC();
    }

    @Bean
    public RatingService ratingService(){
        return new RatingServiceRestClient();
        //return new RatingServiceJPA();
        //return new RatingServiceJDBC();
    }

    @Bean
    public CommentService commentService(){
        return new CommentServiceRestClient();
        //return new CommentServiceJPA();
        //return new CommentServiceJDBC();
    }

}

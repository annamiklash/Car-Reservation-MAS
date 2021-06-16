package pjatk.mas.MAS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MasApplication {

    public static void main(String[] args) {

//        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(MasApplication.class).headless(false)
//                .run(args);
//
//        SwingUtilities.invokeLater(() -> {
//            ctx.getBean(MainWindowController.class).showGUI();
//        });

		SpringApplication.run(MasApplication.class, args);
    }


}

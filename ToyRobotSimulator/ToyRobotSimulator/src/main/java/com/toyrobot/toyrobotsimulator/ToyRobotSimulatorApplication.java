package com.toyrobot.toyrobotsimulator;

//import com.toyrobot.toyrobotsimulator.controller.RobotController;
import com.toyrobot.toyrobotsimulator.model.Robots;
import com.toyrobot.toyrobotsimulator.service.RobotService;
import com.toyrobot.toyrobotsimulator.service.RobotServiceImplements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = "com.toyrobot.toyrobotsimulator")
//@ComponentScan({"com.toyrobot.toyrobotsimulator.controller"})
public class ToyRobotSimulatorApplication {

	public static void main(String[] args) {

		SpringApplication.run(ToyRobotSimulatorApplication.class, args);

	}

}

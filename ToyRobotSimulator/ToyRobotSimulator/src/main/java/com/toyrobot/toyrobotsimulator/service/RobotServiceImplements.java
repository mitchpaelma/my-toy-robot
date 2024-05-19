package com.toyrobot.toyrobotsimulator.service;

import com.toyrobot.toyrobotsimulator.model.Robots;
import com.toyrobot.toyrobotsimulator.repository.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RobotServiceImplements implements  RobotService{

    @Autowired
    private RobotRepository robotRepository;

    private Robots robot;
    private static final int HEIGHT = 5;
    private static final int WIDTH = 5;

    private List<Robots> listOfRobots = new ArrayList<>();




    public Robots placeRobot(Integer positionX, Integer positionY, String facing, Integer id) {

        robot = new Robots(positionX, positionY, facing, id);

        listOfRobots.add(robot);


        for (Robots robots : listOfRobots) {

            if (!robot.getId().equals(robots.getId())
                    && robot.getX().equals(robots.getX())
                    && robot.getY().equals(robots.getY())) {

                    throw new RuntimeException("WARNING : Cannot place robot here.Existing robot was placed here [" + robots.getX() + "," + robots.getY() + "]");
                }

            }

            if (checkRobotPlacing()) {
                throw new RuntimeException("Cannot place robot here . . . [" + robot.getX() + "," + robot.getY() + "]" + " it will cause the robot to fall . . ");
            }

        if(robot.getY() == null || robot.getX() == null) {
            throw new RuntimeException("WARNING : Invalid coordinates provided. . . .");

        }else {

            if (robot.getDirection().isBlank() || robot.getDirection() == null ||
                    (!robot.getDirection().equalsIgnoreCase("east") && !robot.getDirection().equalsIgnoreCase("north")
                            && !robot.getDirection().equalsIgnoreCase("south") && !robot.getDirection().equalsIgnoreCase("west"))) {
                throw new IllegalArgumentException("WARNING : Invalid directions provided . . .");
            }
        }


        return robotRepository.save(robot);

    }

    public Robots moveRobot(Integer id) {
        if (robot == null) {
            throw new RuntimeException("Robot needs to be placed before executing other commands. . .");
        }else if(robot.getId().equals(id)){

            switch (robot.getDirection().toLowerCase()) {
                case "north":
                    robot.setY(robot.getY() + 1);
                    System.out.println("Moving north");
                    listOfRobots.add(robot);

                    if (checkRobotPlacing()) {
                        throw new RuntimeException("WARNING : Cannot move robot here [" + robot.getX() + "," + robot.getY() + "] this will cause the robot to fall");
                    }

                    for (Robots robots : listOfRobots) {
                        if (!robots.getId().equals(robot.getId()) && robots.getX().equals(robot.getX()) && robots.getY().equals(robot.getY())) {
                            throw new RuntimeException("WARNING : CANNOT MOVE ROBOT HERE EXISTING ROBOT WAS PLACED HERE");
                        }
                    }

                    break;
                case "east":
                    robot.setX(robot.getX() + 1);
                    listOfRobots.add(robot);
                    System.out.println("Moving east");
                    if (checkRobotPlacing()) {
                        throw new RuntimeException("WARNING : Cannot move robot here [" + robot.getX() + "," + robot.getY() + "] this will cause the robot to fall");

                    }

                    for (Robots robots : listOfRobots) {
                        if (!robots.getId().equals(robot.getId()) && robots.getX().equals(robot.getX()) && robots.getY().equals(robot.getY())) {
                            throw new RuntimeException("WARNING : CANNOT MOVE ROBOT HERE EXISTING ROBOT WAS PLACED HERE");
                        }
                    }

                    break;
                case "south":
                    robot.setY(robot.getY() - 1);
                    listOfRobots.add(robot);
                    System.out.println("Moving south");

                    if (checkRobotPlacing()) {
                        throw new RuntimeException("WARNING : Cannot move robot here [" + robot.getX() + "," + robot.getY() + "] this will cause the robot to fall");

                    }

                    for (Robots robots : listOfRobots) {
                        if (!robots.getId().equals(robot.getId()) && robots.getX().equals(robot.getX()) && robots.getY().equals(robot.getY())) {
                            throw new RuntimeException("WARNING : CANNOT MOVE ROBOT HERE EXISTING ROBOT WAS PLACED HERE");
                        }
                    }

                    break;
                case "west":
                    robot.setX(robot.getX() - 1);
                    listOfRobots.add(robot);
                    System.out.println("Moving west");
                    if (checkRobotPlacing()) {
                        throw new RuntimeException("WARNING : Cannot move robot here [" + robot.getX() + "," + robot.getY() + "] this will cause the robot to fall");

                    }

                    for (Robots robots : listOfRobots) {
                        if (!robots.getId().equals(robot.getId()) && robots.getX().equals(robot.getX()) && robots.getY().equals(robot.getY())) {
                            throw new RuntimeException("WARNING : CANNOT MOVE ROBOT HERE EXISTING ROBOT WAS PLACED HERE");
                        }
                    }

                    break;
            }
        }
        return robotRepository.save(robot);
    }

    public Robots rotateRobotLeft(Integer id) {
        if (robot == null) {
            throw new RuntimeException("Robot needs to be placed before executing other commands. . .");
        }else if(robot.getId().equals(id)) {

            switch (robot.getDirection().toLowerCase()) {
                case "north":
                    robot.setDirection("west");
                    System.out.println("Facing WEST");

                    break;
                case "east":
                    robot.setDirection("north");
                    System.out.println("Facing NORTH");

                    break;
                case "south":
                    robot.setDirection("east");
                    System.out.println("Facing EAST ");

                    break;
                case "west":
                    robot.setDirection("south");
                    System.out.println("Facing SOUTH");

                    break;
            }
        }
        System.out.println("robot.getDirection " + robot.getDirection());

        return robotRepository.save(robot);
    }

    public Robots rotateRobotRight(Integer id) {
            if (robot == null) {
                throw new RuntimeException("Robot needs to be placed before executing other commands. . .");
            } else if (robot.getId().equals(id)) {

                switch (robot.getDirection().toLowerCase()) {
                    case "north" :
                        robot.setDirection("east");
                        System.out.println("Facing EAST ");
                        break;
                    case "east":
                        robot.setDirection("south");
                        System.out.println("Facing SOUTH");
                        break;
                    case "south":
                        robot.setDirection("west");
                        System.out.println("Facing WEST");
                        break;
                    case "west":
                        robot.setDirection("north");
                        System.out.println("Facing NORTH");
                        break;
                }
            }
        return robotRepository.save(robot);
    }

    public Robots getRobot() {
        return robot;
    }

    public Boolean checkRobotPlacing(){

        if(robot.getX() < 0 || robot.getY() < 0 || robot.getX() >= WIDTH || robot.getX() >= HEIGHT || robot.getY() >= WIDTH || robot.getY() >= HEIGHT){
            return true;
        }
        return false;
    }



    @Override
    public List<Robots> getListofRobots(){

        return robotRepository.findAll();
    }

    @Override
    public Robots saveRobots(Robots robot) {

        return robotRepository.save(robot);
    }

    @Override
    public Robots getSingleRobots(Integer id) {
        Optional<Robots> robotsOptional = robotRepository.findById(id);
        if(robotsOptional.isPresent()){

            return robotsOptional.get();

        }
        throw new RuntimeException("Robot "+ id + " doesnt exist. Please register a new robot or provide the id of the existing robot." );
    }


}

package com.toyrobot.toyrobotsimulator.controller;

import com.toyrobot.toyrobotsimulator.model.Robots;
import com.toyrobot.toyrobotsimulator.service.RobotService;
import com.toyrobot.toyrobotsimulator.service.RobotServiceImplements;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class RobotController {

    @Autowired
    public RobotService rService;

    @Autowired
    public RobotServiceImplements robotServiceImplements;


    @GetMapping("/robot-list")
    public ResponseEntity<List<Robots>> getRobots(){
        List<Robots> robots = rService.getListofRobots();
        return ResponseEntity.ok(robots);
    }

    @GetMapping("/toyrobot/{id}")
    public ResponseEntity<String> getRobotId(@PathVariable Integer id){
        Robots robots = new Robots(id);
        try {
             robots = rService.getSingleRobots(id);
            return ResponseEntity.ok("x : " + robots.getX() + "," + " y : " + robots.getY() + "," + " direction : " + robots.getDirection());

        }catch(Exception e){
            return new ResponseEntity<>("Robot id " + robots.getId() +" does not exist.Please register a new robot or provide the id of the existing robot." , HttpStatus.BAD_REQUEST );
        }

    }

    @PostMapping("/toyrobot")
    public ResponseEntity<String> placingRobot(@RequestBody String jSoncommand){

        Integer x = null;
        Integer y = null;
        String direction = null;
        Integer id= null;
        String status =null;
        String message = null;

        JSONObject jsonObject = new JSONObject(jSoncommand);

        String commands = jsonObject.getString("command");

        Robots robots = new Robots();

        switch (commands.toLowerCase()){
            case "register":
                    rService.saveRobots(robots);
                    return ResponseEntity.ok("id : " + String.valueOf(robots.getId()));
            case "place":
                try {
                    id = jsonObject.getInt("id");
                    robotServiceImplements.getSingleRobots(id);
                } catch (EmptyResultDataAccessException e) {
                    return new ResponseEntity<>("WARNING : Cannot place robot. Robot id does not exist", HttpStatus.BAD_REQUEST);
                }
                    try {
                        x = jsonObject.getInt("x");
                        y = jsonObject.getInt("y");
                        direction = jsonObject.getString("direction");
                        id = jsonObject.getInt("id");
                        robotServiceImplements.placeRobot(x, y, direction, id);
                    } catch (JSONException jsonException) {
                        return new ResponseEntity<>("WARNING : Invalid parameters.", HttpStatus.BAD_REQUEST);

                }
                 break;

            case "move":
                id = jsonObject.getInt("id");
                robots.setId(id);
                robotServiceImplements.moveRobot(robots.getId());

                break;
            case "left":
                id = jsonObject.getInt("id");
                robots = new Robots(id);
                robotServiceImplements.rotateRobotLeft(robots.getId());
                break;
            case "right":
                id = jsonObject.getInt("id");
                robots = new Robots(id);
                robotServiceImplements.rotateRobotRight(robots.getId());

                break;

            default:
                return new ResponseEntity<>("WARNING : INVALID COMMANDS. . .", HttpStatus.BAD_REQUEST);

        }

        return new ResponseEntity<>(HttpStatus.OK);

    }




    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public String errorHandlerRuntime(RuntimeException ex){

        return ex.getMessage();
    }


}

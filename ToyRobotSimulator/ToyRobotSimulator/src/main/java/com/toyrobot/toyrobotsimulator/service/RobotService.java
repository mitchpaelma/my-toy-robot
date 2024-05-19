package com.toyrobot.toyrobotsimulator.service;

import com.toyrobot.toyrobotsimulator.model.Robots;

import java.util.List;

public interface RobotService {

    List<Robots> getListofRobots();

    Robots saveRobots(Robots robot);

    Robots getSingleRobots(Integer id);

}

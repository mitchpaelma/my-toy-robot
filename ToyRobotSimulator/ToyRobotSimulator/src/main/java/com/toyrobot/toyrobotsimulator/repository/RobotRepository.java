package com.toyrobot.toyrobotsimulator.repository;

import com.toyrobot.toyrobotsimulator.model.Robots;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RobotRepository extends JpaRepository<Robots, Integer>{


}

package com.sample.timeline_service.controller;

import java.util.UUID;

import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.timeline_service.model.Timeline;
import com.sample.timeline_service.service.TimelineService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping(path="/timeline") //url starts with /user after app path
public class TimelineController {

    // Services
    @Autowired
    TimelineService timelineService;

    // Others
    Logger logger = LoggerFactory.getLogger(TimelineController.class);

    TimelineController() {}


    @GetMapping("/{id}")    
    public @ResponseBody Timeline getTimeline(@PathVariable String id) throws BadRequestException {
        UUID userId = UUID.fromString(id);
        if(!userId.toString().equals(id)){
            logger.error("invalid UUID");
            throw new BadRequestException("path /user/update/{id} API updateUser - invalid input");
        }

        return timelineService.getTimeline(userId);
    }

}

package com.sample.timeline_service.controller.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sample.timeline_service.controller.client.model.FollowersModel;

import java.util.UUID;

@FeignClient(name = "user-service", url = "${client.user_service.url}")
public interface UserClient {
    @GetMapping("/relationship/{userId}/followers")
    @Nullable
    Iterable<FollowersModel> getFollowers(@PathVariable("userId") UUID userId);
}
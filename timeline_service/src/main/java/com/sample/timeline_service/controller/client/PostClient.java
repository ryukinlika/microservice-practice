package com.sample.timeline_service.controller.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sample.timeline_service.controller.client.model.PostModel;

import java.util.UUID;

@FeignClient(name = "post-service", url = "${client.post_service.url}")
public interface PostClient {
    @GetMapping("/posts/user/{userId}")
    @Nullable
    Iterable<PostModel> getPostsByUserId(@PathVariable("userId") UUID userId);
}
package com.api.trendiez.controller;

import com.api.trendiez.config.JwtUtil;
import com.api.trendiez.models.Chat;
import com.api.trendiez.models.Friend;
import com.api.trendiez.models.User;
import com.api.trendiez.services.FriendService;
import com.api.trendiez.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/friends")
public class FriendController {
    @Autowired
    private UserService userService;

    @Autowired
    private FriendService friendService;

    JwtUtil jwtUtil =new JwtUtil();

    @GetMapping("/")
    public ResponseEntity<List<String>> getFriends(@RequestHeader("Authorization") String token, @RequestParam(required = false) String search) throws Exception {
        String userId = jwtUtil.extractUserId(token.substring(7)); // Remove "Bearer " from token

        if (search == null) {
            return ResponseEntity.badRequest().body(Collections.singletonList("Search query required"));
        }

        List<User> users = userService.findUsersByLikeUsername(search);
        users.removeIf(user -> user.getId().equals(userId)); // Remove the current user from results

        List<Friend> friends = new ArrayList<>();
        for (User user : users) {
            Friend friend = friendService.findFriendByUsers(userId, user.getId());

            if (friend != null) {
                friends.add(friend);
            } else {
                friends.add(friendService.createFriend(userId, user));
            }
        }

        return ResponseEntity.ok(friends);
    }
}

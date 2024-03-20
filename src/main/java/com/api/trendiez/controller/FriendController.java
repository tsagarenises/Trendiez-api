package com.api.trendiez.controller;

import com.api.trendiez.config.JwtUtil;
import com.api.trendiez.models.Friend;
import com.api.trendiez.models.User;
import com.api.trendiez.services.FriendService;
import com.api.trendiez.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/friends")
public class FriendController {
    @Autowired
    private FriendService friendService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/search")
    public ResponseEntity<List<Friend>> searchFriends(@RequestHeader String search, User user) {
        if (search.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(friendService.searchFriends(search, user));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Friend>> getFriend(@PathVariable String userId, User user) {
        List<Friend> friends = friendService.getFriend(userId, user);
        return friends != null ? ResponseEntity.ok(friends) : ResponseEntity.notFound().build();
    }

    @GetMapping("/count/{userId}")
    public ResponseEntity<Long> getFriendCount(@PathVariable String userId) {
        return ResponseEntity.ok(friendService.getFriendCount(userId));
    }

    @GetMapping("/subscribers/{userId}")
    public ResponseEntity<List<Friend>> getSubscribers(@PathVariable String userId, @RequestHeader("Authorization") String token) {
        String uid = jwtUtil.extractUserId(token.substring(7));
        return ResponseEntity.ok(friendService.getSubscribers(uid));
    }
//
//    @GetMapping("/subscribed")
//    public ResponseEntity<List<Friend>> getSubscribedTo(User user) {
//        return ResponseEntity.ok(friendService.getSubscribedTo(user));
//    }
//
//    @GetMapping("/requests")
//    public ResponseEntity<List<Friend>> getFriendRequests(User user) {
//        return ResponseEntity.ok(friendService.getFriendRequests(user));
//    }
//
//    @GetMapping("/suggestions")
//    public ResponseEntity<List<Friend>> getFriendSuggestions(User user) {
//        return ResponseEntity.ok(friendService.getFriendSuggestions(user));
//    }
//
//    @PostMapping("/")
//    public ResponseEntity<Friend> sendFriendRequest(@RequestBody Long recipientId, User user) {
//        return ResponseEntity.ok(friendService.sendFriendRequest(user.getId(), recipientId));
//    }
//
//    @PostMapping("/accept")
//    public ResponseEntity<Friend> acceptFriendRequest(@RequestBody Long friendId) {
//        return ResponseEntity.ok(friendService.acceptFriendRequest(friendId));
//    }
//
//    @DeleteMapping("/reject")
//    public ResponseEntity<Void> rejectFriendRequest(@RequestBody Long friendId) {
//        friendService.rejectFriendRequest(friendId);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PostMapping("/contacts")
//    public ResponseEntity<List<User>> findUsersByPhone(@RequestBody List<String> phoneNumbers) {
//        return ResponseEntity.ok(userService.findUsersByPhone(phoneNumbers));
//    }

}

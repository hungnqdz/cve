package com.activemq.chat.chat_activemq.controller;

import com.activemq.chat.chat_activemq.model.Message;
import com.activemq.chat.chat_activemq.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @GetMapping("/chat")
    public String showChat(Model model, HttpServletRequest request) {
        String username = getCookieValue(request, "username");
        model.addAttribute("username", username);
        model.addAttribute("messages", chatService.getMessages());
        return "chat";
    }

    @GetMapping("/")
    public String home(Model model) {
        return "redirect:/chat";
    }

    @PostMapping("/chat/send")
    public String sendMessage(@RequestParam(required = false) String sender,
                              @RequestParam String content,
                              HttpServletRequest request) {
        String username = sender != null ? sender : getCookieValue(request, "username");
        if (username != null) {
            chatService.sendMessage(new Message(username, content));
        }
        return "redirect:/chat";
    }

    @PostMapping("/chat/set-name")
    public String setName(@RequestParam String username, HttpServletResponse response) {
        Cookie cookie = new Cookie("username", username);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/chat";
    }


    private String getCookieValue(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
}
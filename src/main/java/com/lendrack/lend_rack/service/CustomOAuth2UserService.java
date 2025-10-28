package com.lendrack.lend_rack.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.lendrack.lend_rack.persistance.entity.User;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("===============================");
        System.out.println("Hittt this-----");
        System.out.println("===============================");
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        // 🌐 Extract provider info (e.g. "google")
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // 📦 Extract attributes from provider
        Map<String, Object> attributes = oAuth2User.getAttributes();

        // 🧠 Google user info mapping
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        String picture = (String) attributes.get("picture");

        // 💾 Save or update user in H2
        User user = userService.findByEmail(email).orElseGet(User::new);
        user.setName(name);
        user.setEmail(email);
        user.setPicture(picture);
        user.setProvider(registrationId);
        user.setProviderId((String) attributes.get("sub"));
        userService.saveUser(user);

        return new DefaultOAuth2User(
                oAuth2User.getAuthorities(),
                attributes,
                "sub" // Google's unique user key
        );
    }
}
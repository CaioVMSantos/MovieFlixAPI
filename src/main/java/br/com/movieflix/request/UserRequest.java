package br.com.movieflix.request;

import lombok.Builder;

@Builder
public record UserRequest(String name, String email, String password) {
}

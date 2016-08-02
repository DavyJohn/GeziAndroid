package com.geziwulian.netlibrary.model.login;

/**
 * Created by yyx on 16/3/6.
 */
public class Token {

    public String token;

    public Token(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                '}';
    }
}

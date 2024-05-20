package com.lld.ecommercedemo.Utils;

import com.lld.ecommercedemo.dtos.TokenDtos.Token;
import com.lld.ecommercedemo.dtos.TokenDtos.ValidateTokenReqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AuthUtils {
    private WebClient webClient;

    @Autowired
    public AuthUtils(WebClient webClient) {
        this.webClient = webClient;
    }

    public boolean validate_token(String value){
        ValidateTokenReqDTO reqDTO = new ValidateTokenReqDTO();
        reqDTO.setValue(value);
        Token token = null;
        try{
            token = this.webClient.post().uri("http://localhost:8082/user/validate_token")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromValue(reqDTO))
                    .retrieve()
                    .bodyToMono(Token.class)
                    .block();
        }
        catch (Exception e){
             return false;
        }

        return token != null;
    }
}

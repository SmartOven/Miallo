package ru.panteleevya.backend.props;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Slf4j
public class PropsManager {
    public static void getProps(String oauthToken, String secretId) {
        String env = System.getenv("environment");
        if (env.equalsIgnoreCase("testing") || env.equalsIgnoreCase("local")) {
            log.info("Environment is {}. Using variables from environment.", env);
            return; // using variables from environment
        }
        log.info("Environment is {}. Using variables from secret.", env);
        downloadAndSet(oauthToken, secretId); // using variables from secret
    }

    private static void downloadAndSet(String oauthToken, String secretId) {
        String iamToken = getIamToken(oauthToken);
        List<Props> props = getSecretProps(iamToken, secretId);
        for (Props prop : props) {
            System.setProperty(prop.key(), prop.textValue());
        }
        log.info("Downloaded properties: {}.", props.stream().map(Props::key).toList());
    }

    private static String getIamToken(String oauthToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{\"yandexPassportOauthToken\":\"" + oauthToken + "\"}";

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://iam.api.cloud.yandex.net/iam/v1/tokens",
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        String responseBody = responseEntity.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> map;
        try {
            map = objectMapper.readValue(responseBody, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return map.get("iamToken");
    }

    private static List<Props> getSecretProps(String iamToken, String secretId) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + iamToken);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                "https://payload.lockbox.api.cloud.yandex.net/lockbox/v1/secrets/" + secretId + "/payload",
                HttpMethod.GET,
                requestEntity,
                String.class
        );

        String responseBody = responseEntity.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Secret secret = objectMapper.readValue(responseBody, new TypeReference<>() {
            });
            return secret.entries();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}

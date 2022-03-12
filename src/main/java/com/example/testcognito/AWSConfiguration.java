package com.example.testcognito;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfiguration {

    @Bean
    public AWSCognitoIdentityProvider createCognitoClient(@Value("${application.aws.access-key}") String accessKey,
                                                          @Value("${application.aws.secret-key}") String secretKey,
                                                          @Value("${application.aws.region}") String region) {
        AWSCredentials cred = new BasicAWSCredentials(accessKey, secretKey);
        AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(cred);
        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(credProvider)
                .withRegion(region)
                .build();
    }
}


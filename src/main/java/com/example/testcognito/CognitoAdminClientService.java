package com.example.testcognito;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CognitoAdminClientService {
    private final Logger log = LoggerFactory.getLogger(CognitoAdminClientService.class);

    @Value("${application.aws.cognito.user-pool-id}")
    private String userPoolId;

    private final AWSCognitoIdentityProvider identityProvider;

    public CognitoAdminClientService(AWSCognitoIdentityProvider identityProvider) {
        this.identityProvider = identityProvider;
    }

    public  void adminCreateUser(String email, String name){
        AttributeType emailAttr = new AttributeType().withName("email").withValue(email);
        AttributeType nameAttr = new AttributeType().withName("name").withValue(name);
        AttributeType emailVerifiedAttr =
                new AttributeType().withName("email_verified").withValue("true");

        AdminCreateUserRequest userRequest =
                new AdminCreateUserRequest().withUserPoolId(userPoolId).withUsername(email)
                        //.withTemporaryPassword("Pmu123456!")
                        .withDesiredDeliveryMediums(DeliveryMediumType.EMAIL)
                        .withUserAttributes(emailAttr, emailVerifiedAttr, nameAttr);

        AdminCreateUserResult createUserResult = identityProvider.adminCreateUser(userRequest);

        log.info("User {} is created. Status: {}", createUserResult.getUser().getUsername(), createUserResult.getUser().getUserStatus());
    }


}

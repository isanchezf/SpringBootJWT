package com.example.jwt.demojwt.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.jwt.demojwt.contracts.IApplicationRep;
import com.example.jwt.demojwt.entities.Application;

import org.springframework.stereotype.Component;

import lombok.val;

@Component("beanApplicationRep")
public class ApplicationRep implements IApplicationRep {

    private static List<Application> application;

    public ApplicationRep() {
        val appTestOne = new Application();
        val appTestTwo = new Application();

        appTestOne.setApplicationCode(UUID.fromString("26f845c5-a338-4d24-a4c9-6276a3b99653"));
        appTestOne.setApplicationName("TestOne");
        appTestOne.setPrivateKey("MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJ941mU447oE4ltmCzvRGh/zKX+cngnAjD9PjGeitthtQvxeoju/4vnGXyZxdYjIuyKIT+W6IT0YAbBwSM1tgYlNufZ9ExfnGYALtbrNsIdNRXJMzW/CMQsw3lz9AZMuAu8FEnIg405Ac9kr8H/YATK+mFGDeZVuJ4ayQ9YJfz3xAgMBAAECgYAbpvRmMOssAlTNzG/+O0/wYlW7zwiIYF7xS3XpMonKFYgcArzW53sQHJm7LMlYopXwcvqHtzK0SUlhstas6GQhiKXxA/4QAYD5CDIY/xKWRc9G4K7aqqnts/hy6MxR/1ef0R3hufHPxn7DChNuV09UL/h16b0oFN60JmWKmntRwQJBANdZyCOhp5Cj7t3FDjbp14BjLB9RTK9E4S9ttSfqjo0zdSrtB6o+c5FOs6AcwNSDkbMo75QztgwgxT4TglrYbL0CQQC9kuAAQYA06qbektf+xXwoq4/6WqGNjDCKcpK4AISXOOwvFK5/74QVF2pe5asgvRPE9IV6bCDBqNbI8X+QmhtFAkAICmsloXCPPv+5OhVYyYxpV8qa9L8nQCwkSDVeYyylawlx693AZoqMH2MnlEtC5BK5nMqtPu0KMOMMeVABslkFAkAjZCczhQb1WhVbGhj+9Elwok7X01GzxkdNoYQom9glDzhwtbC6K7IB6gQuwTvIeeQV8fx1VXhsDyRCEWvaSBrtAkBPwgK1xwjFL8gbTuc5+dZZZ+kfLhA8u9l8ZFVoQCGNgmWCRD+U8CXd1HXb18EhWp7mzH84DWxNEczftRrxJ25F");
        appTestOne.setPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCfeNZlOOO6BOJbZgs70Rof8yl/nJ4JwIw/T4xnorbYbUL8XqI7v+L5xl8mcXWIyLsiiE/luiE9GAGwcEjNbYGJTbn2fRMX5xmAC7W6zbCHTUVyTM1vwjELMN5c/QGTLgLvBRJyIONOQHPZK/B/2AEyvphRg3mVbieGskPWCX898QIDAQAB");
        
        appTestTwo.setApplicationCode(UUID.fromString("01ef19a5-a78f-43e8-b396-1c35c37479aa"));
        appTestTwo.setApplicationName("TestTwo");
        appTestTwo.setPrivateKey("MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKsrc/GNkkfTTOY3ym2dkxsypmf1zcSADicPQmpzjCIODp0jBBJGkvqNQR19UXV6TwVnFvJNXVUhu0uXNjTZEWfcdtjDUtTYsDU7kPbOsqN+nt33IZlGZCgEie+ufMuXleVIAVTRx1LQJELLvmZFH1Lmy0DdNn2720/ukohQ0w4rAgMBAAECgYA5fQec2jqGCw6px5UiSakNmm5PPjwUwhl+vt5EmHg5O9Vmq0sDxFY69lQnTl6cgS6nFt+bE6bEH+ci1S9sgncYiR8uluWwuaW8MyDvSF/0Y8Odnh9fp7+H+waJ4JzIlOXmB90o7ZiuSFXmzlkn04EGCKqniMd6MT5N8v+BRhZJaQJBANzoEgsYlZzAARMbzzXzVb77t9Gzak74t6BXZxa+f20zoCQIvItbT+Yd4eCAfC8Tw7M7/Ain3MvaY9u3hj3d6wUCQQDGXKnf/TbbMKZ/4gYBCSAjCCriBRSuFj+YQuYcuyG6uZrNYSHy32NI4avCMrZPlU4qv0/0qEY77wVAEd3BsDtvAkAVJP19JGhFrbcsng/SaS0+75cjslmtn27sIRGUAr3ttPBSIqfxatcV/qw9Me8xDmJACLDSH0cqGAhQ4pst1s0FAkEAiYsJU8JdvkEOwo/0P9WXDhOEwLJrM4dD041XyEZNzg8S8yROCP2y939bvM2zMp3B1MxRYcEo6jgDuPhqfvp3SwJBAMSjqnYS+Rx1FaqeiSbRPQ/MgaaCl2aDkkU5vp65CkIliRdEBHbWnk7gLvmwFEctqXvtfPrqLvA/SKtsvucdQ6o=");
        appTestTwo.setPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCrK3PxjZJH00zmN8ptnZMbMqZn9c3EgA4nD0Jqc4wiDg6dIwQSRpL6jUEdfVF1ek8FZxbyTV1VIbtLlzY02RFn3HbYw1LU2LA1O5D2zrKjfp7d9yGZRmQoBInvrnzLl5XlSAFU0cdS0CRCy75mRR9S5stA3TZ9u9tP7pKIUNMOKwIDAQAB");

        application = new ArrayList<Application>();
        application.add(appTestOne);
        application.add(appTestTwo);
    }

    public UUID createApplication(final Application app) {
        application.add(app);
        return app.getApplicationCode();
    }
    
    public List<Application> getAll(){
        return application;
    }

    public Application getByCode(UUID appCode) {
        return application.stream().filter(a -> a.getApplicationCode().equals(appCode)).findFirst()
                .orElse(new Application());
    }
}
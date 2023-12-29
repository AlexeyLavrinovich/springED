package com.aliakseila.springED.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Bean
    public Config hazelCastConfig() {
        Config config = new Config();
        config.getNetworkConfig().setPort(5900)
                .setPortAutoIncrement(false);

        config.addMapConfig(
                new MapConfig().setName("profiles")
                        .setBackupCount(2)
                        .setTimeToLiveSeconds(300));
        return config;
    }

    @Bean
    public HazelcastInstance hazelcastInstance(){
        return Hazelcast.newHazelcastInstance(hazelCastConfig());
    }

}

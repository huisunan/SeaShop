package com.rsdtm.supermarket.upload.config;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConfigurationProperties(prefix = "tx.cos")
@Data
public class TxCosConfig {
    private String secretId;
    private String secretKey;
    private String regionName;
    private String bucketName;

    @Bean
    COSCredentials credentials(){
        return new BasicCOSCredentials(secretId, secretKey);
    }

    @Bean
    COSClient client(){
        Region region = new Region(regionName);
        ClientConfig clientConfig = new ClientConfig(region);
        return new COSClient(credentials(), clientConfig);
    }
}

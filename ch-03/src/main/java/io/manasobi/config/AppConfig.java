package io.manasobi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Slf4j
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@EnableJpaAuditing
@EntityScan(
    basePackageClasses = {Jsr310JpaConverters.class},
    basePackages = {"io.manasobi.messaging.domain"}
)
public class AppConfig {

}
package com.example.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // JPA에 대해서 감시자 활성화 --> 추가적인 공부 필요
public class JPAConfig {


}

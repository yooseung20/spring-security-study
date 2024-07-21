package com.sia.security.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	UserDetailsService userDetailsService(DataSource dataSource) {
		// 기본적인 쿼리로 DB에서 사용자를 찾을 수 있다.
		// JdbcUserDetailsManager 에서 사용하는 user, authorities  테이블을 DB에 만들어줘야 한다.
		return new JdbcUserDetailsManager(dataSource);
	}

	// @Bean
	// public UserDetailsService userDetailsService() {
	// 	UserDetails sia = User.withUsername("sia.yoo")
	// 		.password("12345")
	// 		.build();
	//
	// 	UserDetails danny = User.withUsername("danny.kim")
	// 		.password("54321")
	// 		.build();
	//
	// 	UserDetails zoey = User.withUsername("zoey.yoo")
	// 		.password("34567")
	// 		.build();
	//
	// 	List<UserDetails> users = List.of(sia, danny, zoey);
	//
	// 	return new InMemoryUserDetailsService(users);
	// }

	@Bean
	public PasswordEncoder passwordEncoder() {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("noop", NoOpPasswordEncoder.getInstance());
		encoders.put("bcrypt", new BCryptPasswordEncoder());
		encoders.put("scrypt", new SCryptPasswordEncoder(2,2,2,2,2));

		return new DelegatingPasswordEncoder("noop", encoders);

	}


	// @Bean
	// public PasswordEncoder passwordEncoder() {
	// 	// 비밀번호에 암호화를 적용하지 않고 평문 그대로 사용하는 PasswordEncoder
	// 	// deprecated, 개발, 테스트 환경에서만 사용하고 운영환경에서는 사용해서는 안됨
	// 	return NoOpPasswordEncoder.getInstance();
	// }

	// @Bean
	// public UserDetailsService userDetailsService() {
	// 	InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
	// 	UserDetails user = User.builder().username("sia").password("password").build();
	// 	inMemoryUserDetailsManager.createUser(user);
	//
	// 	return inMemoryUserDetailsManager;
	// }

	// @Bean
	// public UserDetailsService userDetailsService() {
	// 	UserDetails sia = User.withUsername("sia.yoo")
	// 		.password("12345")
	// 		.build();
	//
	// 	UserDetails danny = User.withUsername("danny.kim")
	// 		.password("54321")
	// 		.build();
	//
	// 	UserDetails zoey = User.withUsername("zoey.yoo")
	// 		.password("34567")
	// 		.build();
	//
	// 	List<UserDetails> users = List.of(sia, danny, zoey);
	//
	// 	return new InMemoryUserDetailsService(users);
	// }

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity
			.authorizeHttpRequests((auth) ->
				auth.requestMatchers("/permit-pass/**").permitAll() // '/permit-pass/**' 경로는 인증 없이 사용가능
					.anyRequest().authenticated()) //그외에 모든 요청은 인증 필요
			.httpBasic(Customizer.withDefaults())
			.build();
	}
}

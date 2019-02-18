package core.security.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * 全局访问控制
 * @version 2019-02-17
 */
public class GlobalAccessConfiguration {
	/**
	 * 默认配置，/，/index，/login允许所有人访问，登录页为/login，注销返回页面为/index
	 * @param http 需要配置访问控制的HttpSecurity对象
	 * @throws Exception
	 */
	public void getDefaultConfig(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/","/index").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		.loginPage("/login").permitAll()
		.and()
		.logout()
		.logoutSuccessUrl("/index");
	}
}

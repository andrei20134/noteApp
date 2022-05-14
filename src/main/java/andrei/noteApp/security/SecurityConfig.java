package andrei.noteApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.sql.DataSource;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    static final String SIGNING_KEY = "kKSMJ92Mknk38njs9HJ8KNALiuc938FH";
    static final int ENCODING_STRENGTH = 256;
    static final String SECURITY_REALM = "Task Manager";

    private static final String LOGIN_PROCESSING_URL = "/login";
    private static final String LOGIN_FAILURE_URL = "/login?error";
    private static final String LOGIN_URL = "/login";
    private static final String LOGOUT_SUCCESS_URL = "/login";

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService userDetailsService;



    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder).and()
                .authenticationProvider(authenticationProvider());
    }



    /**
     * Require login to access internal pages and configure login form.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Vaadin handles CSRF internally
        http.csrf().disable()

                // Register our CustomRequestCache, which saves unauthorized access attempts, so the user is redirected after login.
                .requestCache().requestCache(new CustomRequestCache())

                // Restrict access to our application.
                .and().authorizeRequests()

                // Allow all Vaadin internal requests.
                .requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()

                // Allow all requests by logged-in users.
                .anyRequest().authenticated()

                // Configure the login page.
                .and().formLogin()
                .loginPage(LOGIN_URL).permitAll()
                .loginProcessingUrl(LOGIN_PROCESSING_URL)
                .failureUrl(LOGIN_FAILURE_URL)


                //.and().antMatcher("/register").anonymous()

                // Configure logout
                .and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL);
    }


    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        return super.userDetailsService();

//        UserDetails user =  User.withUsername("user")
//                .password("{noop}userpass")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;

    }
        /**
         * Allows access to static resources, bypassing Spring Security.
         */
        @Override
        public void configure (WebSecurity web){
            web.ignoring().antMatchers(
                    // Client-side JS
                    "/VAADIN/**",

                    // the standard favicon URI
                    "/favicon.ico",

                    // the robots exclusion standard
                    "/robots.txt",

                    // web application manifest
                    "/manifest.webmanifest",
                    "/sw.js",
                    "/offline.html",

                    // icons and images
                    "/icons/**",
                    "/images/**",
                    "/styles/**",

                    // (development mode) H2 debugging console
                    "/h2-console/**");
        }
    }

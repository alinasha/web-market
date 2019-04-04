package com.vsu.webmarket.data;


import com.vsu.webmarket.ApplicationContextProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.vsu.webmarket.data")
public class DataAccessApplication {
    public static void main(String[] args) {
        ApplicationContext context = ApplicationContextProvider.getApplicationContext();
        //insert your code here
    }
}

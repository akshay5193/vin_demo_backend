package com.example.demo;

import com.example.demo.service.VehicleVinService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
class DemoApplicationTests {

    @InjectMocks
    private VehicleVinService vinService;


    @Test
    void serviceMethod() {
    }

}

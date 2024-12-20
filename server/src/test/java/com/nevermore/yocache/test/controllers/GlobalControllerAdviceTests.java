package com.nevermore.yocache.test.controllers;

import com.nevermore.yocache.advice.GlobalControllerAdvice;
import com.nevermore.yocache.controllers.ObjectsController;
import com.nevermore.yocache.storage.StorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author nevermore
 * @since 0.0.1
 */
@WebMvcTest(controllers = {GlobalControllerAdvice.class, ObjectsController.class})
public class GlobalControllerAdviceTests {

    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private StorageService storageService;

    @Test
    public void testInternalServerError() throws Exception {
        when(storageService.getObject(anyString())).thenThrow(new RuntimeException());
        mockMvc.perform(get("/test/a/b")).andExpect(status().isInternalServerError());
    }

    @Test
    public void testIllegalArgument() throws Exception {
        when(storageService.getObject(anyString())).thenThrow(new IllegalArgumentException());
        mockMvc.perform(get("/test/a/b")).andExpect(status().isBadRequest());
    }
}

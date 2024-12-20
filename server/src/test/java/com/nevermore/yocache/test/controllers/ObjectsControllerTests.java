package com.nevermore.yocache.test.controllers;

import com.nevermore.yocache.controllers.ObjectsController;
import com.nevermore.yocache.storage.StorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.AdditionalMatchers.not;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author nevermore
 * @since 0.0.1
 */
@WebMvcTest(ObjectsController.class)
public class ObjectsControllerTests {

    @MockitoBean
    private StorageService storageService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGet() throws Exception {
        when(storageService.getObject(not(eq("/test")))).thenReturn(null);
        when(storageService.getObject(eq("/test/a/b"))).thenReturn(new byte[]{0});
        mockMvc.perform(get("/testNotFound")).andExpect(status().isNotFound());
        mockMvc.perform(get("/test/a/b")).andExpect(status().isOk());
    }

    @Test
    public void testPut() throws Exception {
        mockMvc.perform(post("/test/a").content(new byte[]{0})).andExpect(status().isOk());
    }
}

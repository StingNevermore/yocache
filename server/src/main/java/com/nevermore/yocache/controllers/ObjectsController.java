package com.nevermore.yocache.controllers;

import com.nevermore.yocache.storage.StorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * @author nevermore
 * @since 0.0.1
 */
@RestController
public class ObjectsController {

    @Autowired
    private StorageService storageService;

    @ResponseBody
    @GetMapping(value = "/**")
    public ResponseEntity<byte[]> getObject(HttpServletRequest request) {
        String key = request.getRequestURI();
        byte[] object = storageService.getObject(key);
        if (object == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(CONTENT_TYPE, IMAGE_JPEG_VALUE)
                .body(object);
    }

    @ResponseBody
    @RequestMapping(value = "/**", method = {PUT, POST})
    public ResponseEntity<String> putObject(@RequestBody byte[] body, HttpServletRequest request) {
        String key = request.getRequestURI();
        storageService.saveObject(key, body);
        return ResponseEntity.ok().body("Success");
    }
}

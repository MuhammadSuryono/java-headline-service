package silampari.headline.koran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import silampari.headline.koran.service.PackageNewsService;

@RestController
public class NewsController {

    @Autowired
    private PackageNewsService packageNewsService;

    @ResponseBody
    @GetMapping(value = "/getPackage", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> responsePackage(){
        return packageNewsService.getAllPackage();
    }



    @ResponseBody
    @GetMapping(value = "/getNews", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> responseNews(){
        return packageNewsService.getNews();
    }


}

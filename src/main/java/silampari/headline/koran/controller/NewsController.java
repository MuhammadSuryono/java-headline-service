package silampari.headline.koran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import silampari.headline.koran.domain.dto.SubscriptionDto;
import silampari.headline.koran.service.PackageNewsService;

import javax.validation.Valid;

@RestController
public class NewsController {

    @Autowired
    private PackageNewsService packageNewsService;

    @ResponseBody
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/koran/paket", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> responsePacket(){
        return packageNewsService.getAllPackage();
    }


    @ResponseBody
    @PostMapping(value = "/add/subscriptions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> subscriptions (@Valid @RequestBody SubscriptionDto subscriptionsDto){
        return packageNewsService.subscription(subscriptionsDto);
    }
}

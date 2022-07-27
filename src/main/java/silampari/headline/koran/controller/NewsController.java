package silampari.headline.koran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import silampari.headline.koran.domain.dto.ResponseDto;
import silampari.headline.koran.service.PackageNewsService;

@RestController
public class NewsController {

    @Autowired
    private PackageNewsService packageNewsService;

    @ResponseBody
    @GetMapping("getPackage")
    private ResponseEntity<Object> responseDto(){
        return packageNewsService.getAllPackage();
    }
}

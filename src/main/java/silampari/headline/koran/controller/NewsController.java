package silampari.headline.koran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import silampari.headline.koran.service.PackageNewsService;

@RestController
public class NewsController {

    @Autowired
    private PackageNewsService packageNewsService;

    @ResponseBody
    @GetMapping(value = "/koran/paket", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> responsePacket(){
        return packageNewsService.getAllPackage();
    }

    // TODO:
    // Tambahkan parameter filtering startDate and endDate
    @ResponseBody
    @GetMapping(value = "/koran/pdf", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> responseNewsPaper(){
        return packageNewsService.getNews();
    }

    // TODO:
    // Untuk special edition, nanti saya tambahkan satu kolom
    // Ini nonong yang kerjakan
    @ResponseBody
    @GetMapping(value = "/koran/pdf/special-edition", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> responsePdfNewsSpecialEdition(
            @RequestParam("limit") Integer limit,
            @RequestParam("sort") String sort,
            @RequestParam("page") Integer page){

        if (sort == null) sort = "desc";
        if (page == null) page = 1;
        if (limit == null) limit = 12;

        return packageNewsService.pdfNewsSpecialEdition(limit, page, sort);
    }


}

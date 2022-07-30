package silampari.headline.koran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import silampari.headline.koran.domain.dto.KoranPdfRequest;
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
    private ResponseEntity<Object> responseNewsPaper(@RequestBody KoranPdfRequest pdfRequest) throws  Exception{
        return packageNewsService.getNews(pdfRequest);
    }

    // TODO:
    // Untuk special edition, nanti saya tambahkan satu kolom
    // Ini nonong yang kerjakan
//    @ResponseBody
//    @GetMapping(value = "/koran/pdf/special-edition", produces = MediaType.APPLICATION_JSON_VALUE)
//    private ResponseEntity<Object> responsePdfNewsSpecialEdition(){
////        return packageNewsService.getNews();
//    }


}

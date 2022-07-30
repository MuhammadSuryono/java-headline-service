package silampari.headline.koran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
    // special edition set isSpecial = 1 isSpecial = 0 get non special if isSpecial "" or null get all data pdf
    @ResponseBody
    @GetMapping(value = "/koran/pdf", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> responseNewsPaper(@RequestBody KoranPdfRequest pdfRequest) throws  Exception{
        return packageNewsService.getNews(pdfRequest);
    }

    @ResponseBody
    @GetMapping(value = "/koran/pdf/special-edition", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> responsePdfNewsSpecialEdition(
            @RequestParam(name = "limit", required = false) Integer limit,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "page", required = false) Integer page){

        if (sort == null) sort = "desc";
        if (page == null) page = 0;
        else page = page - 1;
        if (limit == null) limit = 12;

        return packageNewsService.pdfNewsSpecialEdition(limit, page, sort);
    }


}

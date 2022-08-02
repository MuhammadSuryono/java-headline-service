package silampari.headline.koran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import silampari.headline.koran.domain.dto.KoranPdfRequest;
import silampari.headline.koran.service.PackageNewsService;
import silampari.headline.koran.util.DateUtil;

import java.util.Date;

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

    // TODO:
    // Tambahkan parameter filtering startDate and endDate
    // special edition set isSpecial = 1 isSpecial = 0 get non special if isSpecial "" or null get all data pdf
    @ResponseBody
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/koran/pdf", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> responseNewsPaper(@RequestBody KoranPdfRequest pdfRequest) throws  Exception{
        return packageNewsService.getNews(pdfRequest);
    }

    @ResponseBody
    @GetMapping(value = "/koran/pdf/special-edition", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> responsePdfNewsSpecialEdition(
            @RequestParam(name = "limit", required = false) Integer limit,
            @RequestParam(name = "sort", required = false) String sort,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(name = "endDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){

        if (sort == null) sort = "desc";
        if (page == null) page = 0;
        else page = page - 1;
        if (limit == null) limit = 12;

        if (startDate != null && endDate == null) endDate = startDate;
        if (endDate != null) endDate = DateUtil.addDays(endDate, 1);

        return packageNewsService.pdfNewsSpecialEdition(limit, page, sort, startDate, endDate);
    }

}

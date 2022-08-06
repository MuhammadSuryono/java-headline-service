package silampari.headline.koran.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import silampari.headline.koran.domain.dto.KoranPdfRequest;
import silampari.headline.koran.service.PdfNewsPaperService;

@RestController
public class PdfNewsPaperController {

    @Autowired
    private PdfNewsPaperService pdfNewsPaperService;

    @ResponseBody
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping(value = "/koran/pdf", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> responseNewsPaper(@RequestBody KoranPdfRequest pdfRequest) throws  Exception{
        return pdfNewsPaperService.getNews(pdfRequest);
    }

    @ResponseBody
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping(value = "/koran/pdf/latest", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> koranPdfLatest() throws Exception {
        return pdfNewsPaperService.getLastPdfNewsPaper();
    }
}

package silampari.headline.koran.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import silampari.headline.koran.Repository.PackageNewsRepository;
import silampari.headline.koran.Repository.PdfNewsRepository;
import silampari.headline.koran.domain.dao.PacketNews;
import silampari.headline.koran.domain.dao.PdfNews;
import silampari.headline.koran.domain.dto.KoranPdfRequest;
import silampari.headline.koran.domain.dto.KoranPdfResponse;
import silampari.headline.koran.domain.dto.PdfNewsDto;
import silampari.headline.koran.exception.CommonException;
import silampari.headline.koran.util.ParsingDate;
import silampari.headline.koran.util.ResponseUtil;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PackageNewsService {

    @Autowired
    private PackageNewsRepository packageNewsRepository;

    @Autowired
    private PdfNewsRepository pdfNewsRepository;

    @Autowired
    private ParsingDate parsingDate;

    public ResponseEntity<Object> getAllPackage() {
        ResponseEntity<Object> response = null;
        List<PacketNews> getPackege = packageNewsRepository.findAll();
        if (Objects.isNull(getPackege)) {
            throw new CommonException("Error While Trying to get data because data null");
        }
        response = ResponseUtil.buildResponse(
                "SUCCESS",
                "SUCCESS GETTING DATA",
                (Serializable) getPackege,
                HttpStatus.OK);
        return response;
    }

    public ResponseEntity<Object> getNews(KoranPdfRequest pdfRequest) throws ParseException {
        ResponseEntity<Object> response = null;
        List<PdfNewsDto> resNewsDtos = new ArrayList<>();
        List<PdfNews> newPdf = pdfNewsRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(PdfNews::getDateEdision))
                .collect(Collectors.toList());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        if (Objects.isNull(newPdf)) {
            throw new CommonException("Error While Trying to get data because data null");
        }
        if (!ObjectUtils.isEmpty(pdfRequest.getDate())) {
            newPdf.stream().forEach(x -> {
                try {
                    x.setDateEdision(dateFormat.parse(x.getDateEdision().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
            var sort = this.parsingDate.minusOne(pdfRequest.getDate());
            newPdf = newPdf.stream().filter(x -> sort.before(x.getDateEdision())).collect(Collectors.toList());
        }
        if (ObjectUtils.isEmpty(pdfRequest.getSorting()) || pdfRequest.getSorting().toLowerCase().equals("desc")) {
            newPdf = newPdf.stream().sorted(Comparator.comparing(PdfNews::getDateEdision).reversed()).collect(Collectors.toList());
        }
        if (!ObjectUtils.isEmpty(pdfRequest.getIsSpecial())){
            newPdf = newPdf.stream().filter(x->x.getSpecialEdition().equals(pdfRequest.getIsSpecial())).collect(Collectors.toList());
        }
        List<PdfNews> finalPdf = newPdf;
        finalPdf.stream().forEach(x -> {
            PdfNewsDto newsDto = new PdfNewsDto();
            newsDto.setId(x.getId());
            newsDto.setDescription(x.getDescription());
            newsDto.setPdf(x.getPdf());
            newsDto.setThumbnail(x.getThumbnail());
            newsDto.setTitle(x.getTitle());
            newsDto.setDateEdition(dateFormat.format(x.getDateEdision()));
            resNewsDtos.add(newsDto);
        });
        PagedListHolder newPage = new PagedListHolder(finalPdf);
        newPage.setPageSize(12);
        newPage.setPage(pdfRequest.getPage());
        KoranPdfResponse pdfResponse = KoranPdfResponse.builder()
                .totalElement(newPage.getNrOfElements())
                .totalPage(newPage.getPageCount())
                .pageNumber(newPage.getPage())
                .numberOfElement(finalPdf.size())
                .pdfNews(resNewsDtos)
                .build();
        response = ResponseUtil.buildResponse(
                "SUCCESS",
                "SUCCESS GETTING DATA",
                (Serializable) pdfResponse,
                HttpStatus.OK);
        return response;
    }

    /**
     *
     * @param limit
     * @param page
     * @param sort
     * @return
     */
    public ResponseEntity<Object> pdfNewsSpecialEdition(Integer limit, Integer page, String sort) {
        sort = sort.toLowerCase(Locale.ROOT);

        List<PdfNews> pdfSpecialEdition;
        Pageable pdfPagination = PageRequest.of(page, limit);
        if (sort.equals("asc")) pdfSpecialEdition = pdfNewsRepository.findAllBySpecialEditionOrderByDateEdisionAsc(1, pdfPagination);
        else pdfSpecialEdition = pdfNewsRepository.findAllBySpecialEditionOrderByDateEdisionDesc(1, pdfPagination);

        int totalData = pdfNewsRepository.findAllBySpecialEdition(1).size();

        return ResponseUtil.buildResponse(
                "SUCCESS",
                "SUCCESS GETTING DATA",
                (Serializable) ResponseUtil.buildPaginationResponse(
                        (Serializable) pdfSpecialEdition,
                        pdfSpecialEdition.size(),
                        totalData,
                        pdfPagination),
                HttpStatus.OK
        );
    }
}

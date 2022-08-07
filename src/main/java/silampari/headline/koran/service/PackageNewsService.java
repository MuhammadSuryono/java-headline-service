package silampari.headline.koran.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import silampari.headline.koran.Repository.PackageNewsRepository;
import silampari.headline.koran.Repository.PdfNewsRepository;
import silampari.headline.koran.Repository.SubscriptionsRepository;
import silampari.headline.koran.constant.PaymentStatus;
import silampari.headline.koran.domain.dao.PacketNews;
import silampari.headline.koran.domain.dao.PdfNews;
import silampari.headline.koran.domain.dao.Subscriptions;
import silampari.headline.koran.domain.dto.KoranPdfRequest;
import silampari.headline.koran.domain.dto.KoranPdfResponse;
import silampari.headline.koran.domain.dto.PdfNewsDto;
import silampari.headline.koran.domain.dto.SubscriptionDto;
import silampari.headline.koran.util.ParsingDate;
import silampari.headline.koran.util.ResponseUtil;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PackageNewsService {

    @Autowired
    private PackageNewsRepository packageNewsRepository;

    @Autowired
    private PdfNewsRepository pdfNewsRepository;

    @Autowired
    private SubscriptionsRepository subscriptionRepository;

    @Autowired
    private ParsingDate parsingDate;


    public ResponseEntity<Object> getAllPackage() {
        ResponseEntity<Object> response = null;
        List<PacketNews> getPackege = packageNewsRepository.findAll();
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
        newPdf.stream().forEach(x -> {
            try {
                x.setDateEdision(dateFormat.parse(x.getDateEdision().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        if (!ObjectUtils.isEmpty(pdfRequest.getStartDate())) {
            var sortStart = this.parsingDate.minusOne(pdfRequest.getStartDate());
            newPdf = newPdf.stream().filter(x -> sortStart.before(x.getDateEdision())).collect(Collectors.toList());
        }
        if (!ObjectUtils.isEmpty(pdfRequest.getEndDate())) {
            var sortEnd = this.parsingDate.plusOne(pdfRequest.getEndDate());
            newPdf = newPdf.stream().filter(x -> x.getDateEdision().before(sortEnd)).collect(Collectors.toList());
        }
        if (ObjectUtils.isEmpty(pdfRequest.getSorting()) || pdfRequest.getSorting().toLowerCase().equals("desc")) {
            newPdf = newPdf.stream().sorted(Comparator.comparing(PdfNews::getDateEdision).reversed()).collect(Collectors.toList());
        }
        if (!ObjectUtils.isEmpty(pdfRequest.getIsSpecial())) {
            newPdf = newPdf.stream().filter(x -> x.getSpecialEdition().equals(pdfRequest.getIsSpecial())).collect(Collectors.toList());
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
        PagedListHolder newPage = new PagedListHolder(resNewsDtos);
        newPage.setPageSize(pdfRequest.getLimit());
        newPage.setPage(pdfRequest.getPage());
        KoranPdfResponse pdfResponse = KoranPdfResponse.builder()
                .totalElement(newPage.getNrOfElements())
                .totalPage(newPage.getPageCount())
                .pageNumber(newPage.getPage())
                .numberOfElement(finalPdf.size())
                .pdfNews(newPage.getPageList())
                .build();
        response = ResponseUtil.buildResponse(
                "SUCCESS",
                "SUCCESS GETTING DATA",
                (Serializable) pdfResponse,
                HttpStatus.OK);
        return response;
    }


    public ResponseEntity<Object> subscription(SubscriptionDto subscriptionsDto) {
        ResponseEntity<Object> response;
        ModelMapper modelMapper = new ModelMapper();
        try {
            var packetNews = packageNewsRepository.findById(subscriptionsDto.getPacketId()).get();
            if (subscriptionsDto.getTotalPayment() < Integer.parseInt(packetNews.getPrice()) ||
            subscriptionsDto.getTotalPayment() > Integer.parseInt(packetNews.getPrice())) {
                return ResponseUtil.buildResponse(
                        "FAILED",
                        "INPUTAN PAYMENT TIDAK SESUAI",
                        null,
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }

            Subscriptions subscriptions = modelMapper.map(subscriptionsDto, Subscriptions.class);
            subscriptions.setIsVerification(Boolean.FALSE);
            subscriptions.setStatus(PaymentStatus.UNPAID.getValue());
            subscriptions.setPacketName(packetNews.getPacketName());
            subscriptions.setCreatedAt(new Date());
            subscriptions.setPrice(packetNews.getPrice());
            subscriptionRepository.saveAndFlush(subscriptions);
            response = ResponseUtil.buildResponse(
                    "SUCCESS",
                    "DATA SAVED",
                    null,
                    HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
        return response;
    }
}

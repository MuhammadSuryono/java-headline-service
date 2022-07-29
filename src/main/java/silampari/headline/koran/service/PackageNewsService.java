package silampari.headline.koran.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import silampari.headline.koran.Repository.PackageNewsRepository;
import silampari.headline.koran.Repository.PdfNewsRepository;
import silampari.headline.koran.domain.dao.PacketNews;
import silampari.headline.koran.domain.dao.PdfNews;
import silampari.headline.koran.exception.CommonException;
import silampari.headline.koran.util.ResponseUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Service
public class PackageNewsService {

    @Autowired
    private PackageNewsRepository packageNewsRepository;

    @Autowired
    private PdfNewsRepository pdfNewsRepository;

    public ResponseEntity<Object> getAllPackage(){
        ResponseEntity<Object> response = null;
        List<PacketNews> getPackege = packageNewsRepository.findAll();
        if (Objects.isNull(getPackege)){
            throw new CommonException("Error While Trying to get data because data null");
        }
        response = ResponseUtil.buildResponse(
                "SUCCESS",
                "SUCCESS GETTING DATA",
                (Serializable) getPackege,
                HttpStatus.OK);
        return response;
    }

    public ResponseEntity<Object> getNews() {
        ResponseEntity<Object> response = null;
        Pageable pageable = PageRequest.of(1, 12, Sort.Direction.ASC, "id");

        List<PdfNews> pdfNews = pdfNewsRepository.findAll(pageable).getContent();
        if (Objects.isNull(pdfNews)){
            throw new CommonException("Error While Trying to get data because data null");
        }
        response = ResponseUtil.buildResponse(
                "SUCCESS",
                "SUCCESS GETTING DATA",
                (Serializable) pdfNews,
                HttpStatus.OK);

        return response;
    }
}

package silampari.headline.koran.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import silampari.headline.koran.Repository.PackageNewsRepository;
import silampari.headline.koran.domain.dao.PacketNews;
import silampari.headline.koran.domain.dto.ResponseDto;
import silampari.headline.koran.exception.CommonException;
import silampari.headline.koran.util.ResponseUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Service
public class PackageNewsService {

    @Autowired
    private PackageNewsRepository packageNewsRepository;

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
}

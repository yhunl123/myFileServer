package com.service;

import com.VO.FileVO;
import com.VO.MemberVO;
import com.VO.resultVO.ErrorVO;
import com.VO.resultVO.FileResult;
import com.VO.resultVO.LoginResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.repository.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
public class FileService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);

    @Autowired
    private FileRepository fileRepository;

    public FileResult.FileUploadData fileUpload(HttpServletRequest request, HttpServletResponse response, Map params) {
        HttpSession session = request.getSession(true);

        FileResult.FileUploadData resultVO = new FileResult.FileUploadData();

        FileVO fileVO = new FileVO();

        ErrorVO errorVO = new ErrorVO();


        try {




            resultVO.setError(errorVO);
            return resultVO;
        } catch (Exception e) {
            e.printStackTrace();
            resultVO.setSuccess(false);
            errorVO.setCode(-1);
            errorVO.setMessage("Internet Server Error");
            resultVO.setError(errorVO);

            return resultVO;
        }
    }

}

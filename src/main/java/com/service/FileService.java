package com.service;

import com.VO.FileVO;
import com.VO.resultVO.ErrorVO;
import com.VO.resultVO.FileResult;
import com.VO.resultVO.ResultVO;
import com.VO.resultVO.apiResult.FileUpload;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.repository.FileDataRepository;
import com.repository.MemberRepository;
import com.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.NoSuchFileException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class FileService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);
    private final FileUtil fileUtil = new FileUtil();

    private final String filePath = "D:/test";

    @Autowired
    private FileDataRepository fileDataRepository;

    @Autowired
    private MemberRepository memberRepository;

    public FileResult.FileUploadData fileUpload(HttpServletRequest request, HttpServletResponse response, Map params, MultipartHttpServletRequest mRequest) {
        HttpSession session = request.getSession(true);

        FileResult.FileUploadData resultVO = new FileResult.FileUploadData();

        FileVO fileVO = new FileVO();

        ErrorVO errorVO = new ErrorVO();


        try {
            String userToken = request.getHeader("user-token");
            if ("".equals(request.getHeader("user-token"))) {
                resultVO.setSuccess(false);
                errorVO.setCode(110);
                errorVO.setMessage("User not find");
                resultVO.setError(errorVO);
                return resultVO;
            }

            Map<String, Object> userInfo = memberRepository.getMember(userToken);
            if(userInfo.isEmpty()) {
                resultVO.setSuccess(false);
                errorVO.setCode(110);
                errorVO.setMessage("User not find");
                resultVO.setError(errorVO);
                return resultVO;
            }

            MultipartFile upFile = mRequest.getFile("file");
            if (upFile == null) {
                resultVO.setSuccess(false);
                errorVO.setCode(120);
                errorVO.setMessage("Missing file");
                resultVO.setError(errorVO);
                return resultVO;
            } else {
                fileUtil.printFileInfo(upFile);

                String upFileName = UUID.randomUUID() + "_" + upFile.getOriginalFilename();
                String path = filePath + "/" + userInfo.get("memberId").toString() + "/";

                fileVO.setFileOrigName(upFile.getOriginalFilename());
                fileVO.setFileSize(Long.toString(upFile.getSize()));
                fileVO.setFileName(upFileName);
                fileVO.setFilePath(path);

                fileUtil.makeDir(fileVO.getFilePath());

                upFile.transferTo(new File(fileVO.getFilePath(), fileVO.getFileName()));
                fileDataRepository.insertFileData(fileVO);

                FileUpload fileUpload = new FileUpload();

                fileUpload.setFileName(fileVO.getFileName());
                fileUpload.setFileSize(fileVO.getFileSize());

                FileResult.FileUploadDataView data = new FileResult.FileUploadDataView();
                data.setView(fileUpload);

                resultVO.setData(data);
                resultVO.setSuccess(true);
                resultVO.setError(errorVO);
                return resultVO;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultVO.setSuccess(false);
            errorVO.setCode(-1);
            errorVO.setMessage("Internet Server Error");
            resultVO.setError(errorVO);

            return resultVO;
        }
    }

    public ResultVO deleteFile(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params) {
        HttpSession session = request.getSession(true);

        ResultVO resultVO = new ResultVO();
        FileVO fileVO = new FileVO();
        ErrorVO errorVO = new ErrorVO();

        try {
            fileVO.setFileName(params.get("fileName").toString());

            String userToken = request.getHeader("user-token");
            if ("".equals(request.getHeader("user-token"))) {
                resultVO.setSuccess(false);
                errorVO.setCode(110);
                errorVO.setMessage("User not find");
                resultVO.setError(errorVO);
                return resultVO;
            }

            Map<String, Object> userInfo = memberRepository.getMember(userToken);
            if(userInfo.isEmpty()) {
                resultVO.setSuccess(false);
                errorVO.setCode(110);
                errorVO.setMessage("User not find");
                resultVO.setError(errorVO);
                return resultVO;
            }
            Map fileData =  fileDataRepository.searchFileDataByFileName(fileVO.getFileName());

            String delFilePath = fileData.get("filePath").toString() + fileData.get("fileName").toString();
            logger.info("Delete File Path : {}", delFilePath);

            File file = new File(delFilePath);

            file.delete();

            fileDataRepository.deleteByFileName(fileVO.getFileName());

            resultVO.setSuccess(true);
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

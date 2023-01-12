package com.service;

import com.VO.MemberVO;
import com.VO.resultVO.ErrorVO;
import com.VO.resultVO.LoginResult;
import com.VO.resultVO.apiResult.LoginToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.repository.MemberRepository;
import com.util.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Service
public class LoginService {

    @Autowired
    private MemberRepository memberRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ObjectMapper objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE);

    private JWTUtil jwtUtil = new JWTUtil();
    /**
     * Login
     * @param params
     * @return MemberVO
     * */
    public LoginResult.LoginData getLogin(HttpServletRequest request, HttpServletResponse response, Map params) {
        HttpSession session = request.getSession(true);

        LoginResult.LoginData resultVO = new LoginResult.LoginData();

        MemberVO memberVO = new MemberVO();

        ErrorVO errorVO = new ErrorVO();

        try {
            memberVO.setMemberName(params.get("memberName").toString());
            memberVO.setMemberPw(params.get("memberPw").toString());

            Map<String, Object> result = memberRepository.searchMember(memberVO);

            if (!result.isEmpty()) {
                logger.info("result :>>>> {}", result.get("memberName").toString(), ", ", result.get("memberAuth").toString());

                String userToken = jwtUtil.createToken(result);

                logger.info("================================userToken================================");
                logger.info(userToken);
                logger.info("================================userToken================================");

                memberRepository.updateToken(userToken, (Integer) result.get("memberId"));

                LoginToken data = objectMapper.convertValue(result, LoginToken.class);
                data.setUserToken(userToken);
                LoginResult.LoginDataView dataVO = new LoginResult.LoginDataView();
                dataVO.setView(data);
                resultVO.setData(dataVO);
                resultVO.setSuccess(true);
            } else {
                resultVO.setSuccess(false);
                errorVO.setCode(110);
                errorVO.setMessage("User not find");
            }

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

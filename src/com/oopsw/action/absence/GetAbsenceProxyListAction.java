package com.oopsw.action.absence;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.oopsw.action.Action;
import com.oopsw.model.DAO.ApproverDAO;
import com.oopsw.model.VO.AbsenceListVO;
import com.oopsw.model.VO.ApproverListVO;
import com.oopsw.model.VO.GetListVO;

public class GetAbsenceProxyListAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		
		String url = "webpage/absence/getAbsenceList.jsp";
        HttpSession session = request.getSession();
        String approverId = (String) session.getAttribute("employeeId");
        if (approverId == null) {
            approverId = "E25-011";
        }

        int absencePage = 1;
        int proxyPage = 1;

        try {
            if (request.getParameter("absencePage") != null)
                absencePage = Integer.parseInt(request.getParameter("absencePage"));
            if (request.getParameter("proxyPage") != null)
                proxyPage = Integer.parseInt(request.getParameter("proxyPage"));
        } catch (NumberFormatException e) {
            absencePage = proxyPage = 1;
        }

        ApproverDAO dao = new ApproverDAO();

        GetListVO absenceVO = new GetListVO(approverId, null, absencePage);
        GetListVO proxyVO = new GetListVO(approverId, null, proxyPage);

        List<AbsenceListVO> absenceList = dao.getAbsenceList(absenceVO);
        List<AbsenceListVO> proxyList = dao.getProxyList(proxyVO);
        
        int absenceTotalPages = 3;
        int proxyTotalPages = 3;
    
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("absenceList", absenceList);
        resultMap.put("proxyList", proxyList);
        resultMap.put("absenceCurrentPage", absencePage);
        resultMap.put("absenceTotalPages", absenceTotalPages);
        resultMap.put("proxyCurrentPage", proxyPage);
        resultMap.put("proxyTotalPages", proxyTotalPages);
        resultMap.put("success", !absenceList.isEmpty() || !proxyList.isEmpty());

        String json = gson.toJson(resultMap);
        request.setAttribute("result", json);

        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        if (isAjax) {
            return "webpage/absence/absenceResult.jsp";
        }

        request.setAttribute("absenceList", absenceList);
        request.setAttribute("proxyList", proxyList);
        request.setAttribute("absenceCurrentPage", absencePage);
        request.setAttribute("proxyCurrentPage", proxyPage);
        request.setAttribute("absenceTotalPages", absenceTotalPages);
        request.setAttribute("proxyTotalPages", proxyTotalPages);

        return url;
	}

}

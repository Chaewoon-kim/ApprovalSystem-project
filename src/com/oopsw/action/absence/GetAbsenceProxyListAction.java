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

public class GetAbsenceProxyListAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		
		String url = "webpage/absence/getAbsenceList.jsp";
		HttpSession session = request.getSession();
        String approverId = (String) session.getAttribute("employeeId");
        if (approverId == null) {
            approverId = "E25-011"; // 테스트용
        }
        
        int page = 1;
        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                page = 1;
            }
        }
        
        ApproverDAO dao = new ApproverDAO();

        List<AbsenceListVO> absenceList = dao.getAbsenceList(approverId);
        List<AbsenceListVO> proxyList = dao.getProxyList(approverId);
        
        // 총 페이지 수 (임시)
        int totalPages = 3;

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("absenceList", absenceList);
        resultMap.put("proxyList", proxyList);
        resultMap.put("currentPage", page);
        resultMap.put("totalPages", totalPages);
        resultMap.put("success", !absenceList.isEmpty() || !proxyList.isEmpty());

        String json = gson.toJson(resultMap);
        request.setAttribute("result", json);
        
        System.out.println(resultMap.get("absenceList"));
        
        boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

        if (isAjax) {
            return "webpage/absence/absenceProxyTable.jsp"; 
        }

        request.setAttribute("absenceList", absenceList);
        request.setAttribute("proxyList", proxyList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        return url;
    
	}

}

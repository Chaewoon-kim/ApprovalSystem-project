package com.oopsw.action.absence;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.oopsw.action.Action;
import com.oopsw.model.DAO.ApproverDAO;

public class DeleteAbsenceAction implements Action {

	@Override
	public String execute(HttpServletRequest request) throws ServletException, IOException {
		String[] ids = request.getParameterValues("absenceDateNos");
        ApproverDAO dao = new ApproverDAO();

        int successCount = 0;
        int failCount = 0;

        if (ids != null) {
            for (String id : ids) {
                try {
                    int absenceDateNo = Integer.parseInt(id);
                    boolean deleted = dao.deleteAbsence(absenceDateNo);
                    if (deleted) successCount++;
                    else failCount++;
                } catch (NumberFormatException e) {
                    failCount++;
                }
            }
        }

        Map<String, Object> result = new HashMap<>();
        if (successCount > 0) {
            result.put("success", true);
            result.put("message", "삭제 성공 " + successCount + "건");
        } else {
            result.put("success", false);
            result.put("message", "삭제 실패");
        }

        Gson gson = new Gson();
        String json = gson.toJson(result);

        request.setAttribute("result", json);

        return "webpage/absence/absenceResult.jsp"; 
    }
}

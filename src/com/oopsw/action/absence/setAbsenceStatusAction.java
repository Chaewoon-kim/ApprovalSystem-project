package com.oopsw.action.absence;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.model.DAO.ApproverDAO;

/*일단 만들어본 부재 상태 변경 액션,, */
public class setAbsenceStatusAction {
    public String execute(HttpServletRequest request) throws ServletException, IOException {
        ApproverDAO dao = new ApproverDAO();

        boolean activated = dao.setAbsenceStatusToActive();
        boolean ended = dao.setAbsenceStatusToEnd();


        request.setAttribute("activated", activated);
        request.setAttribute("ended", ended);

        return "absenceUpdateStatusResult.jsp";
    }

}


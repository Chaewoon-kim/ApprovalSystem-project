package com.oopsw.action.employee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.EmployeeDAO;
import com.oopsw.model.VO.CommentVO;

public class UpdateCommentAction implements Action {

   @Override
   public String execute(HttpServletRequest request) throws ServletException, IOException {
      int commentNumber = Integer.parseInt(request.getParameter("commentNumber"));
        String commentContents = request.getParameter("commentContents");

        CommentVO comment = new CommentVO();
        comment.setCommentNumber(commentNumber);
        comment.setCommentContents(commentContents);

        EmployeeDAO dao = new EmployeeDAO();
        boolean updateResult = dao.updateComment(comment);

      
        request.setAttribute("result", updateResult);

        
        return "webpage/result.jsp"; 
    }

}

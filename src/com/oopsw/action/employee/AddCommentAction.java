package com.oopsw.action.employee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.EmployeeDAO;
import com.oopsw.model.VO.CommentVO;

public class AddCommentAction implements Action {

   @Override
   public String execute(HttpServletRequest request) throws ServletException, IOException {
      
      int documentNo = Integer.parseInt(request.getParameter("documentNo"));
      String writerId = request.getParameter("writerId");
      String commentContents = request.getParameter("commentContents");
      
      CommentVO comment = new CommentVO();
        comment.setDocumentNumber(documentNo);
        comment.setwriterId(writerId);
        comment.setCommentContents(commentContents);
      
      EmployeeDAO dao = new EmployeeDAO();
      boolean addResult = dao.addComment(comment);
      
      request.setAttribute("result", addResult);
      
      return "webpage/result.jsp";
   }

}

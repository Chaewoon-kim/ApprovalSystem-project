package com.oopsw.action.employee;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.oopsw.action.Action;
import com.oopsw.model.DAO.EmployeeDAO;
import com.oopsw.model.VO.CommentVO;

public class DeleteCommentAction implements Action {

   @Override
   public String execute(HttpServletRequest request) throws ServletException, IOException {
      
      int commentNo = Integer.parseInt(request.getParameter("commentNumber"));
      
      String commentContents = request.getParameter("commentContents");

      CommentVO comment = new CommentVO();
      comment.setCommentNumber(commentNo);
      comment.setCommentContents(commentContents);

      EmployeeDAO dao = new EmployeeDAO();
      boolean deleteResult = dao.deleteComment(commentNo);

      request.setAttribute("result", deleteResult);

      return "webpage/result.jsp";


   }

}

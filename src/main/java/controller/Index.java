package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean countExists=false;//Cookie��count�����݂��邩�H
		 int count=1;//���݂̃J�E���g
		 Cookie cookie=null;//Cookie�ϐ���錾
		 Cookie[] cookies=request.getCookies();//���M����Ă���Cookie���擾(Cookie�����M����Ă��Ȃ�������null)
		 //Cookie�����M����Ă����ꍇ
		 if(cookies !=null){
			 //����̃L�[��Cookie�����邩�ǂ����͈�����ׂ邵���Ȃ�
			 for(Cookie c:cookies){
				 //count�Ƃ���cookie�����邩
				 if(c.getName().equals("count")){
					 //cookie����count�����o����1���₷
					 count=Integer.parseInt(c.getValue()) +1;
					 //�V����count���L�[�ɂ���Cookie�𐶐�����
					 cookie=new Cookie("count",String.valueOf(count));
					 //cookie�̗L��������b�Őݒ�(����90��)
					 cookie.setMaxAge(60*60*24*90);
					 //���X�|���X�w�b�_�[��cookie���l�߂�
					 response.addCookie(cookie);
					 //count�����݂����̂�true
					 countExists=true;
					 //����������T������߂ă��[�v�𔲂���
					 break;
				 }
			 }
		 }
		 //count�Ƃ����N�b�L�[�����M����Ă��Ȃ������ꍇ
		 if(!countExists){
			 //�V����Cookie�𐶐�
			 cookie=new Cookie("count","1");
			 cookie.setMaxAge(60*60*24*90);
			 response.addCookie(cookie);
		 }
		 //���݂̃J�E���g��view�Ŏg����悤�Ƀ��N�G�X�g�X�R�[�v�ɋl�߂�
		 request.setAttribute("count", String.valueOf(count));
		 //�t�H���[�h����
		 RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/lib/index.jsp");
		 rd.forward(request,response);	 
	}
}

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
		boolean countExists=false;//Cookieにcountが存在するか？
		 int count=1;//現在のカウント
		 Cookie cookie=null;//Cookie変数を宣言
		 Cookie[] cookies=request.getCookies();//送信されているCookieを取得(Cookieが送信されていなかったらnull)
		 //Cookieが送信されていた場合
		 if(cookies !=null){
			 //特定のキーのCookieがあるかどうかは一つずつ調べるしかない
			 for(Cookie c:cookies){
				 //countというcookieがあるか
				 if(c.getName().equals("count")){
					 //cookieからcountを取り出して1増やす
					 count=Integer.parseInt(c.getValue()) +1;
					 //新しくcountをキーにしてCookieを生成する
					 cookie=new Cookie("count",String.valueOf(count));
					 //cookieの有効期限を秒で設定(下は90日)
					 cookie.setMaxAge(60*60*24*90);
					 //レスポンスヘッダーにcookieを詰める
					 response.addCookie(cookie);
					 //countが存在したのでtrue
					 countExists=true;
					 //見つかったら探索をやめてループを抜ける
					 break;
				 }
			 }
		 }
		 //countというクッキーが送信されていなかった場合
		 if(!countExists){
			 //新しくCookieを生成
			 cookie=new Cookie("count","1");
			 cookie.setMaxAge(60*60*24*90);
			 response.addCookie(cookie);
		 }
		 //現在のカウントをviewで使えるようにリクエストスコープに詰める
		 request.setAttribute("count", String.valueOf(count));
		 //フォワード処理
		 RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/lib/index.jsp");
		 rd.forward(request,response);	 
	}
}

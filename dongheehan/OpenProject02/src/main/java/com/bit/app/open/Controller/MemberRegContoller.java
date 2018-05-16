package com.bit.app.open.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bit.app.open.member.Model.Member;
import com.bit.app.open.member.Service.MemberRegService;

@Controller
@RequestMapping("/member/memberReg") // jsp form action url 값;
public class MemberRegContoller {

	@Autowired
	private MemberRegService memberRegService;

	/*
	 * 컨트롤러 클래스가 하는 역할이 뭘까? 컨트롤러는 디스패처 서블릿이 받은 핸들러 맵핑과 어댑터를 거쳐 넘어 온다, 클라이언트가 요청해주는
	 * 것을 서비스 클래스와 DAO 클래스에게 보내줘서 처리 후 뷰 이름으로 전달해 주는 역할을 한다. 어댑터가 다시받고 디스패처 서블릿에
	 * 보내준다.
	 */

	// 1. 서비스로 보내줄 메서드 생성 후 연결 (의존주입으로 자동화) 임포트 처리
	// 2. 보내주는 설정 (get,post 방식 설정) 둘다 해줘
	// 3. 서비스랑 DAO에 받은 정보를 처리 후 결과를 VIEW 값으로 보내준다 (VIEWS 의 JSP 파일 이동)
	// -> 처리가 완료 되었다면 처리완료 페이지로 이동/ 처리 되지 않았으면 안됬다고 이동

	// GET 방식으로 받을 메서드를 생성한다. 스트링으로 받네?
	@RequestMapping(method=RequestMethod.GET) // 브라우저에 url을 설정하여 끌어오는 방식?
	public String memberRegForm() {

		return "member/memberRegForm"; // 리턴값으로 member/memberRegForm 주소값이 반환된다. 경로를 찾아 뷰처리 해준다.
	}
	// GET 방식이 끝났으니 POST방식 ㄱㄱ
	@RequestMapping(method=RequestMethod.POST)
	public String memberReg(Member member, Model model, HttpServletRequest request) throws IllegalStateException, IOException {
			System.out.println(member);
		// 실제로 데이터 베이스에 저장할 코딩.
	int resultCnt = memberRegService.memberReg(member, request);
		
	String msg = "정상적으로 처리되었습니다."; // msg 메서드를 만든건가?
	
	if(resultCnt == 0)
		msg =  "가입 처리가 되지 않았습니다. 담당자에게 문의 해주세요.";
	
	// 모델 속성을 msg에 반환한다?
	model.addAttribute("msg", msg);
	
	return "member/memberReg";
	
	}

}

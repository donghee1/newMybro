package com.bit.app.open.member.Service;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.bit.app.open.member.Dao.MemberDao;
import com.bit.app.open.member.Model.Member;

public class MemberRegService {

	@Autowired
	MemberDao dao;
	
	public int memberReg(Member member,HttpServletRequest request)throws IllegalStateException, IOException {
	
	// 서비스가 하는 역할
	// 파일 업로드 처리
	// 업로드 폴더 시스템 물리적 경로 찾기(request 방식으로)
		
		String uploadURI = "/uploadfile/memberphoto";
		String dir = request.getSession().getServletContext().getRealPath(uploadURI);
		
		if( !member.getPhotofile().isEmpty()) {
			
			String fileName = member.getMember_id()+"_"+member.getPhotofile().getOriginalFilename();
			
			member.getPhotofile().transferTo(new File(dir, fileName));
			member.setPhoto(fileName);
		
		}
		
		int resultCnt = dao.insertMember(member);
		
		return resultCnt;
		
	}
			
}
















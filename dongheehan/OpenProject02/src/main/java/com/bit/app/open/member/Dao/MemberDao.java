package com.bit.app.open.member.Dao;



import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.bit.app.open.member.Model.Member;

public class MemberDao {
	private JdbcTemplate jdbcTemplate;

	
	/*Dao 역할
	- db에 데이터 전달 및 접속을 위한 방법
	- 서버 접속을 위한 jdbctemplate 사용
	- sql 메서드를 생성 후 sql 서버 테이블에 만들어진 속성값을 대입한다?
	- 업데이트의 역할
*/	
	// 생성자 방식으로 의존 설정
	public MemberDao(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		
	}
		public int insertMember(Member member) {
			
			String sql = "insert into together (member_id, member_name, password, photo) " + " values (?, ?, ?, ?)";
		
			int resulCnt = jdbcTemplate.update(sql, member.getMember_id(), 
					member.getMember_name(), member.getPassword(), member.getPhoto());
		
			return resulCnt;
		
	}

}

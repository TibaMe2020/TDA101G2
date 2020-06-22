package com.member.model;

import java.util.List;

public interface MemberDAO_interface {
	public void insert(MemberVO memberVO);

	public void update(MemberVO memberVO);

	public void delete(String member_id);

	public MemberVO findByPK(String member_id);

	public MemberVO findByEmail(String email);

	public List<MemberVO> getAll();

	public List<MemberVO> getApplicants();

}

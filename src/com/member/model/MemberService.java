package com.member.model;

import java.util.List;

import com.member.security.HashPassword;

public class MemberService {
	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberDAO();
	}
	
	private static String hashPassword(String password) {
		String algorithm = "SHA-256";
		HashPassword hp = new HashPassword();
		String hashedPassword = hp.generateHash(password, algorithm);
		return hashedPassword;
	}

//	public boolean login(String email, String password) {
//		MemberVO memberVO = null;
//		if ((memberVO = dao.findByEmail(email)) != null) {
//			String hashedPassword = hashPassword(password);
//			String pass = memberVO.getPassword();
//			if (hashedPassword.equals(pass)) {
//				memberVO.setPassword(pass);
//				
//				return true;
//			}
//		}
//		return false;
//	}
	

	
	public MemberVO login(String email, String password) {
		MemberVO memberVO = null;
		try {
			memberVO = dao.findByEmail(email);
			if (memberVO != null && memberVO.getMember_state() < 5 && memberVO.getMember_state() != 0) {
				String hashedPassword = hashPassword(password);
				String pass = memberVO.getPassword();
				if (hashedPassword.equals(pass)) {
					memberVO.setPassword(pass);
					return memberVO;
				}
			}			
			return null;			
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	public MemberVO signUp(MemberVO memberVO) {
		try {
			List<MemberVO> members = dao.getAll();
			for (MemberVO mv : members) {
				if (memberVO.getEmail().equals(mv.getEmail())) {
					memberVO.setEmail("");
					return memberVO;
				};
			}
			String hashedPassword = hashPassword(memberVO.getPassword());
			memberVO.setPassword(hashedPassword);			
			memberVO.setMember_state(0);
			dao.insert(memberVO);
			memberVO = dao.findByEmail(memberVO.getEmail());
			return memberVO;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public MemberVO updateInfo(MemberVO memberVO, String new_password) {
		String input_password = memberVO.getPassword();
		String unchecked = hashPassword(input_password);
		String stored_password = dao.findByEmail(memberVO.getEmail()).getPassword();
		try {
			if("".equals(new_password)) {
			}else if (unchecked.equals(stored_password)) {
				memberVO.setPassword(hashPassword(new_password));
			} else if (!unchecked.equals(stored_password) && input_password.trim().length() != 0) {
				memberVO = dao.findByEmail(memberVO.getEmail());
				memberVO.setPassword("");
			} 
			dao.update(memberVO);
			return memberVO;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public MemberVO changePassword(MemberVO memberVO) {
		try {
			memberVO.setPassword(hashPassword(memberVO.getPassword()));
			dao.update(memberVO);
			return memberVO;			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void updateState(MemberVO memberVO) {
		
	}
	
	public MemberVO updateState(String member_id, Integer member_state) {
		try {
			MemberVO memberVO = dao.findByPK(member_id);
			memberVO.setMember_state(member_state);
			dao.update(memberVO);
			return memberVO;			
		} catch(Exception e) {
			return null;
		}
	}

	public boolean submitApplication(MemberVO memberVO, String confirm) {
		String unchecked = hashPassword(confirm);
		String stored_password = dao.findByEmail(memberVO.getEmail()).getPassword();
		try {
			if(!unchecked.equals(stored_password)) {
				return false;
			} else {
				memberVO.setMember_state(2);
				dao.update(memberVO);
				System.out.println("submit :" + memberVO.getMember_state());
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Integer verify(String member_id) {
		MemberVO memberVO = dao.findByPK(member_id);
		if(memberVO.getDocument_image() != null) {
			memberVO.setMember_state(4);
			dao.update(memberVO);
			return 4;
		} else {
			memberVO.setMember_state(3);
			dao.update(memberVO);
			return 3;
		}
	}
	
	public MemberVO updateBlogInfo(MemberVO memberVO) {
		try {
			
			dao.update(memberVO);
			return memberVO;			
		} catch(Exception e) {
			return null;
		}
	}
	
	public List<MemberVO> getApplicants() {
		List<MemberVO> applicants = dao.getApplicants();
		return applicants;
	}
	
	public List<MemberVO> getAll() {
		List<MemberVO> members = dao.getAll();
		return members;
	}
	
	public MemberVO getOne(String member_id) {
		MemberVO memberVO = dao.findByPK(member_id);
		return memberVO;
	}
	
	public MemberVO forgotPassword(String email) {
		try {
			MemberVO memberVO = dao.findByEmail(email);
			if(memberVO != null) {
				return memberVO;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

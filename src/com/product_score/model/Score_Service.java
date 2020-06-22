package com.product_score.model;

import java.util.List;

public class Score_Service {

	private Score_DAO_interface dao;

	public Score_Service() {

		dao = new Score_DAO();
	}

	public Score_VO addScore(Score_VO score_VO) {
		dao.insert(score_VO);
		return score_VO;
	}
	
	public Score_VO getOneScore(String product_score_id) {
		return dao.findByPrimaryKey(product_score_id);
		
	}

	public List<Score_VO> getAll(){
		return dao.getAll();
	}
	
	public List<Score_VO> getByProductID(String product_id){
		return dao.getByProductID(product_id);
	}
	
	public List<Score_VO> getByMemberId(String member_id) {
		return dao.getByMemberId(member_id);
	}
	
	public Score_VO getByMIdPId(String member_id, String product_id) {
		return dao.getByMbIdPId(member_id, product_id);
	}
	
	
//	public Map<String,String> sortScore(){
//		Map<String, String> map = new HashMap<>();
//		List<Score_VO> all = getAll();
//		for(Score_VO score:all) {
//			String id = score.getProduct_id();
//			OptionalDouble result = getAll().stream()
//					.filter(p -> p.getProduct_id().equals(id))
//					.mapToDouble(p -> new Double(p.getScore()))
//					.average();
//			
//			if(result.isPresent()) {
//				DecimalFormat df = new DecimalFormat("0.0");
//				String avg = df.format(result.getAsDouble());
//				map.put(id, avg);
//			}
//		}
//		Map<String,String> result = map.entrySet().stream()
//				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
//				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
//				(oldValue, newValue) -> oldValue, LinkedHashMap::new));
//		
//
//		System.out.println(result);
//
//		
//		return result;
//	}
//	public static void main(String[] args) {
//		new Score_Service().sortScore();
//	}
}

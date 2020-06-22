package com.notification.model;

import java.util.List;

public interface NotiDAO_interface {

	public void insert(NotiVO notiVO);

	public void update(NotiVO notiVO);

	public void delete(String notification_id);

	public List<NotiVO> findByMembId(String member_id);

	public NotiVO findByPK(String notification_id);
}

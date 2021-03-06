package cn.itcast.bos.service.base.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.bos.dao.base.AreaRepository;
import cn.itcast.bos.domain.base.Area;
import cn.itcast.bos.service.base.AreaService;

@Service
@Transactional
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaRepository areaRepository;

	public void saveBatch(List<Area> areas){
		areaRepository.save(areas);
	}

	

	@Override
	public Page<Area> findByPage(Specification<Area> sepcification, Pageable pageable) {
		// TODO Auto-generated method stub
		return areaRepository.findAll(sepcification, pageable);
	}

}

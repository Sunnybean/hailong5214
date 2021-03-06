package cn.itcast.bos.web.action.transit;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.itcast.bos.domain.transit.TransitInfo;
import cn.itcast.bos.service.transit.TransitInofService;
import cn.itcast.bos.web.action.common.BaseAction;

@Controller
@Scope("prototype")
@ParentPackage("json-default")
@Namespace("/")
public class TransitInfoAction extends BaseAction<TransitInfo>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6394864485847834922L;
	@Autowired
	private TransitInofService transitInfoService;
	
	private String wayBillIds;
	
	public void setWayBillIds(String wayBillIds) {
		this.wayBillIds = wayBillIds;
	}

	@Action(value="transit_create" ,results={@Result(name="success",type="json")})
	public String create(){
		Map<String ,Object> result = new HashMap<>();
		try{
		transitInfoService.createTransits(wayBillIds);
		result.put("success", true);
		result.put("msg", "开始中转配送成功");
		
		}catch(Exception e){
			result.put("success", false);
			result.put("msg", "开始中转配送失败");
		}
		ActionContext.getContext().getValueStack().push(result);
		return SUCCESS;
	}
	@Action(value="transit_pageQuery", results={@Result(name="success",type="json")})
	public String pageQuery(){
		//分页查询
		Pageable  pageable = new PageRequest(page -1 ,rows);
		//调用service 查询分页的数据
		Page<TransitInfo> pageData = transitInfoService.findByPage(pageable);
		//压入值栈返回
		pushPageDataToValueStack(pageData);
		return SUCCESS;
	}
	
	
	
	
	
	
}

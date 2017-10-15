package ${package}.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

<#if packageName??>
	<#assign _packageName="${packageName}."/>
<#else>
	<#assign _packageName=""/>
</#if>

import ${package}.entity.${_packageName}${className};
import ${package}.service.${_packageName}${className}Service;
import ${package}.util.PageUtils;
import ${package}.util.Query;
import ${package}.util.PageResultBean;
import ${package}.util.ResultBean;

/**
 * ${comments}
 * 
 * @author ${author}
 * @email ${email}
 * @date ${datetime}
 */
@RestController
@RequestMapping("/admin_api/${pathName}")
public class ${className}Rest {
	@Autowired
	private ${className}Service ${classname}Service;
	
	/**
	 * 列表
	 */
	@PostMapping("/list")
	public PageResultBean list(@RequestBody Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<${className}> ${classname}List = ${classname}Service.queryList(query);
		int total = ${classname}Service.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(${classname}List, total, query.getLimit(), query.getPage());

		PageResultBean pageBean = new PageResultBean();
		pageBean.setCode(PageResultBean.SUCCESS);
		pageBean.setData(pageUtil.getList());
		pageBean.setCurrPage(pageUtil.getCurrPage());
		pageBean.setPageSize(pageUtil.getPageSize());
		pageBean.setTotalCount(pageUtil.getTotalCount());
		pageBean.setTotalPage(pageUtil.getTotalPage());
		return pageBean;
	}
	
	
	/**
	 * 信息
	 */
	@GetMapping("/info/{${pk.attrname}}")
	public ResultBean info(@PathVariable("${pk.attrname}") ${pk.attrType} ${pk.attrname}){
		${className} ${classname} = ${classname}Service.queryObject(${pk.attrname});

		ResultBean resultBean = new ResultBean();
		resultBean.setCode(ResultBean.SUCCESS);
		resultBean.setData(${classname});
		return resultBean;
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	public ResultBean save(@RequestBody ${className} ${classname}){
		${classname}Service.save(${classname});

		ResultBean resultBean = new ResultBean();
		resultBean.setCode(ResultBean.SUCCESS);
		resultBean.setMsg("保存成功");
		return resultBean;
	}
	
	/**
	 * 修改
	 */
	@PutMapping("/update")
	public ResultBean update(@RequestBody ${className} ${classname}){
		${classname}Service.update(${classname});

		ResultBean resultBean = new ResultBean();
		resultBean.setCode(ResultBean.SUCCESS);
		resultBean.setMsg("修改成功");
		return resultBean;
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/delete")
	public ResultBean delete(@RequestBody ${pk.attrType}[] ${pk.attrname}s){
		${classname}Service.deleteBatch(${pk.attrname}s);

		ResultBean resultBean = new ResultBean();
		resultBean.setCode(ResultBean.SUCCESS);
		resultBean.setMsg("删除成功");
		return resultBean;
	}
	
}

package ${package}.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

<#if packageName??>
	<#assign _packageName="${packageName}."/>
<#else>
	<#assign _packageName=""/>
</#if>

import ${package}.mapper.${_packageName}${className}Mapper;
import ${package}.entity.${_packageName}${className};
import ${package}.service.${_packageName}${className}Service;



@Service("${classname}Service")
public class ${className}ServiceImpl implements ${className}Service {
	@Autowired
	private ${className}Mapper ${classname}Mapper;
	
	@Override
	public ${className} queryObject(${pk.attrType} ${pk.attrname}){
		return ${classname}Mapper.queryObject(${pk.attrname});
	}
	
	@Override
	public List<${className}> queryList(Map<String, Object> map){
		return ${classname}Mapper.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return ${classname}Mapper.queryTotal(map);
	}
	
	@Override
	public void save(${className} ${classname}){
		${classname}Mapper.save(${classname});
	}
	
	@Override
	public void update(${className} ${classname}){
		${classname}Mapper.update(${classname});
	}
	
	@Override
	public void delete(${pk.attrType} ${pk.attrname}){
		${classname}Mapper.delete(${pk.attrname});
	}
	
	@Override
	public void deleteBatch(${pk.attrType}[] ${pk.attrname}s){
		${classname}Mapper.deleteBatch(${pk.attrname}s);
	}
	
}

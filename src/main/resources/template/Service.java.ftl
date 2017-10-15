package ${package}.service;

<#if packageName??>
	<#assign _packageName="${packageName}."/>
<#else>
	<#assign _packageName=""/>
</#if>

import ${package}.entity.${_packageName}${className};

import java.util.List;
import java.util.Map;

/**
 * ${comments}
 * 
 * @author ${author}
 * @email ${email}
 * @date ${datetime}
 */
public interface ${className}Service {
	
	${className} queryObject(${pk.attrType} ${pk.attrname});
	
	List<${className}> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(${className} ${classname});
	
	void update(${className} ${classname});
	
	void delete(${pk.attrType} ${pk.attrname});
	
	void deleteBatch(${pk.attrType}[] ${pk.attrname}s);
}

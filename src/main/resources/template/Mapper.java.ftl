package ${package}.mapper;

<#if packageName??>
 <#assign _packageName="${packageName}."/>
<#else>
 <#assign _packageName=""/>
</#if>

import ${package}.entity.${_packageName}${className};
import ${package}.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;

/**
 * ${comments}
 * 
 * @author ${author}
 * @email ${email}
 * @date ${datetime}
 */
@Mapper
public interface ${className}Mapper extends BaseMapper<${className}> {
	
}

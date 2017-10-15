package ${package}.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

<#if hasBigDecimal??>
import java.math.BigDecimal;
</#if>


/**
 * ${comments}
 * 
 * @author ${author}
 * @email ${email}
 * @date ${datetime}
 */
@Data
public class ${className} implements Serializable {
	private static final long serialVersionUID = 1L;

<#list columns as _column>
	//${_column.comments}
	private ${_column.attrType} ${_column.attrname};
</#list>
}

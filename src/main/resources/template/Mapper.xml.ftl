<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<#if packageName??>
    <#assign _packageName="${packageName}."/>
<#else>
    <#assign _packageName=""/>
</#if>
<mapper namespace="${package}.mapper.${_packageName}${className}Mapper">

    <!-- 可根据自己的需求，是否要使用 -->
    <resultMap type="${package}.entity.${_packageName}${className}" id="${classname}Map">
    <#list columns as _column>
        <result property="${_column.attrname}" column="${_column.columnName}"/>
    </#list>
    </resultMap>

    <!-- 用于select查询公用抽取的列 -->
    <sql id="${classname}Select">
        <![CDATA[
        <#list columns as _column>
            A.`${_column.columnName}`<#if (_column_has_next)>,</#if>
        </#list>
        ]]>
    </sql>
    <!-- 用于insert插入公用抽取的列 -->
    <sql id="${classname}Insert">
        <![CDATA[
        <#list columns as _column>
            <#if (_column.columnName != pk.columnName || pk.extra != 'auto_increment') >
                `${_column.columnName}`<#if (_column_has_next)>,</#if>
            </#if>
        </#list>
        ]]>
    </sql>

    <select id="queryObject" resultMap="${classname}Map">
        select <include refid="${classname}Select"/> from ${tableName} A where A.${pk.columnName} = ${r"#"}{value}
    </select>

    <select id="queryList" resultMap="${classname}Map">
        select <include refid="${classname}Select"/> from ${tableName} A
        <choose>
            <when test="sidx != null and sidx.trim() != ''">
                order by A.${r"$"}{sidx} A.${r"$"}{order}
            </when>
            <otherwise>
                order by A.${pk.columnName} desc
            </otherwise>
        </choose>
        <if test="offset != null and limit != null">
            limit ${r"#"}{offset}, ${r"#"}{limit}
        </if>
    </select>

    <select id="queryTotal" resultType="int">
        select count(*) from ${tableName}
    </select>

    <insert id="save" parameterType="${package}.entity.${_packageName}${className}" <#if (pk.extra=='auto_increment')>
            useGeneratedKeys="true" keyProperty="${pk.attrname}"</#if>>
        insert into ${tableName}
        (
        <include refid="${classname}Insert"/>
        )
        values
        (
        <#list columns as _column>
            <#if (_column.columnName != pk.columnName || pk.extra != 'auto_increment')>
            ${r"#{"}${_column.attrname}${r"}"}<#if (_column_has_next)>,</#if>
            </#if>
        </#list>
        )
    </insert>

    <update id="update" parameterType="${package}.entity.${_packageName}${className}">
        update ${tableName}
        <set>
        <#list columns as _column>
            <#if (_column.columnName != pk.columnName)>
                <if test="${_column.attrname} != null">`${_column.columnName}` = ${r"#{"}${_column.attrname}${r"}"}<#if (_column_has_next)>, </#if>
                </if>
            </#if>
        </#list>
        </set>
        where ${pk.columnName} = ${r"#"}{${pk.attrname}}
    </update>

    <delete id="delete">
        delete from ${tableName} where ${pk.columnName} = ${r"#"}{value}
    </delete>

    <delete id="deleteBatch">
        delete from ${tableName} where ${pk.columnName} in
        <foreach item="${pk.attrname}" collection="array" open="(" separator="," close=")">
        ${r"#"}{${pk.attrname}}
        </foreach>
    </delete>

</mapper>
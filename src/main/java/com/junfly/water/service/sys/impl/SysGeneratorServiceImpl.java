package com.junfly.water.service.sys.impl;

import com.junfly.water.entity.vo.gen.GenCodeVO;
import com.junfly.water.mapper.sys.SysGeneratorMapper;
import com.junfly.water.service.sys.SysGeneratorService;
import com.junfly.water.utils.GenUtils;
import freemarker.template.Configuration;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

@Service("sysGeneratorService")
public class SysGeneratorServiceImpl implements SysGeneratorService {
	@Autowired
	private SysGeneratorMapper sysGeneratorDao;

	@Autowired
	private Configuration configuration;

	@Override
	public List<Map<String, Object>> queryList(Map<String, Object> map) {
		return sysGeneratorDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return sysGeneratorDao.queryTotal(map);
	}

	@Override
	public Map<String, String> queryTable(String tableName) {
		return sysGeneratorDao.queryTable(tableName);
	}

	@Override
	public List<Map<String, String>> queryColumns(String tableName) {
		return sysGeneratorDao.queryColumns(tableName);
	}

	@Override
	public byte[] generatorCode(String[] tableNames, String packageName) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);

		for(String tableName : tableNames){
			//查询表信息
			Map<String, String> table = queryTable(tableName);
			//查询列信息
			List<Map<String, String>> columns = queryColumns(tableName);
			//生成代码
			GenCodeVO genCodeVO = new GenCodeVO();
			genCodeVO.setPackageName(packageName);
			GenUtils.generatorCode(table, columns, zip, configuration, genCodeVO);
		}
		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}

}

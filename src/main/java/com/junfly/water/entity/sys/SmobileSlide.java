package com.junfly.water.entity.sys;

import com.junfly.water.entity.BaseRestEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SmobileSlide extends BaseRestEntity{
	private static final long serialVersionUID = 5454155825314635342L;
	
	public static final String table="s_mobile_slide";

    /**
     * sid db_column: s_id 
     */	
	private Long sid;
    /**
     * imgUrl db_column: img_url 
     */	
	private String imgUrl;
    /**
     * url db_column: url 
     */	
	private String url;
    /**
     * sn db_column: sn 
     */	
	private Long sn;
	//columns END

	public SmobileSlide(){
	}

	public SmobileSlide(
		Long sid
	)
	{
		this.sid = sid;
	}
	public SmobileSlide(
		Long sid,
			String imgUrl,
			String url,
			Long sn
	)
	{
		this.sid = sid;
		this.imgUrl = imgUrl;
		this.url = url;
		this.sn = sn;
	}

	public void setSid(Long value) {
		this.sid = value;
	}
	public Long getSid() {
		return this.sid;
	}
	public void setImgUrl(String value) {
		this.imgUrl = value;
	}
	public String getImgUrl() {
		return this.imgUrl;
	}
	public void setUrl(String value) {
		this.url = value;
	}
	public String getUrl() {
		return this.url;
	}
	public void setSn(Long value) {
		this.sn = value;
	}
	public Long getSn() {
		return this.sn;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
			.append("Sid",getSid())
			.append("ImgUrl",getImgUrl())
			.append("Url",getUrl())
			.append("Sn",getSn())
			.toString();
	}
	
}

